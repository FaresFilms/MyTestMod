package net.kalf.kalfswarmod.event;

import net.kalf.kalfswarmod.KalfsWarMod;
import net.kalf.kalfswarmod.item.BaseGunItem;
import net.kalf.kalfswarmod.item.ModItems;
import net.kalf.kalfswarmod.network.ModMessages;
import net.kalf.kalfswarmod.network.packet.ShootGunC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// 1. THE RADIO TOWER
@Mod.EventBusSubscriber(modid = KalfsWarMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onFovModify(ComputeFovModifierEvent event) {
        Player player = event.getPlayer();

        if (player.isUsingItem() && player.getUseItem().getItem() instanceof BaseGunItem) {
            event.setNewFovModifier(event.getFovModifier()*0.2f); // ADD CUSTOM FOV PER GUN

        }
    }
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (mc.options.keyAttack.isDown()) {

            if (mc.player.getMainHandItem().getItem() instanceof BaseGunItem gun) {

                if (!mc.player.getCooldowns().isOnCooldown(mc.player.getMainHandItem().getItem())) {

                    ModMessages.sendToServer(new ShootGunC2SPacket());

                    mc.player.getCooldowns().addCooldown(gun, gun.getFireRate());
                }
            }
        }
    }

}