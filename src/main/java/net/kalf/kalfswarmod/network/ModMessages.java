package net.kalf.kalfswarmod.network;

import net.kalf.kalfswarmod.KalfsWarMod;
import net.kalf.kalfswarmod.network.packet.ShootGunC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetID = 0;
    private static int id() {
        return packetID++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(ResourceLocation.fromNamespaceAndPath(KalfsWarMod.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(ShootGunC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ShootGunC2SPacket::new)
                .encoder(ShootGunC2SPacket::toBytes)
                .consumerMainThread(ShootGunC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }
}
