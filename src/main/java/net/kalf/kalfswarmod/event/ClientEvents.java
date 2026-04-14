package net.kalf.kalfswarmod.event;

import net.kalf.kalfswarmod.KalfsWarMod;
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

        if (player.isUsingItem() && player.getUseItem().getItem() == ModItems.AK47.get()) {
            event.setNewFovModifier(event.getFovModifier()*0.2f);

        }
    }
    // THE FULL-AUTO LISTENER (This is what actually shoots!)
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        // Only run this once per tick
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        // Is the player physically holding down the Left Mouse Button right now?
        if (mc.options.keyAttack.isDown()) {

            // Are they holding the AK47?
            if (mc.player.getMainHandItem().getItem() == ModItems.AK47.get()) {

                // Is the gun currently on Cooldown?
                if (!mc.player.getCooldowns().isOnCooldown(ModItems.AK47.get())) {

                    // MAIL THE ENVELOPE!
                    ModMessages.sendToServer(new ShootGunC2SPacket());
                }
            }
        }
    }

}