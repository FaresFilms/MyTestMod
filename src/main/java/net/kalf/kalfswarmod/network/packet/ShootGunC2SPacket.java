package net.kalf.kalfswarmod.network.packet;

import net.kalf.kalfswarmod.item.ModItems;
import net.kalf.kalfswarmod.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ShootGunC2SPacket {
    public ShootGunC2SPacket() {

    }

    public ShootGunC2SPacket(FriendlyByteBuf Buf) {

    }

    public void toBytes(FriendlyByteBuf Buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            if (player != null) {
                var level = player.serverLevel();
                if (player.getMainHandItem().getItem() == ModItems.AK47.get()) {
                    boolean hasAmmo = player.getAbilities().instabuild || player.getInventory().contains(new ItemStack(ModItems.SMALL_BULLET.get()));

                    if (hasAmmo) {
                        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                                ModSounds.GUN_SHOT_1.get(), SoundSource.PLAYERS, 4.0f, 1.0f);

                        net.kalf.kalfswarmod.entity.custom.SmallBulletProjectileEntity bullet = new net.kalf.kalfswarmod.entity.custom.SmallBulletProjectileEntity(level, player);

                        bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 4.0f, 0.5f);
                        level.addFreshEntity(bullet);

                        if (!player.getAbilities().instabuild) {
                            int slot = player.getInventory().findSlotMatchingItem(new ItemStack(ModItems.SMALL_BULLET.get()));
                            if (slot != -1) {
                                player.getInventory().getItem(slot).shrink(1);
                            }
                        }
                        player.getCooldowns().addCooldown(ModItems.AK47.get(), 3);
                    }
                }

            };
        });

        return true;
    }
}
