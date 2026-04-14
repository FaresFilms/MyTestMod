package net.kalf.kalfswarmod.sound;

import net.kalf.kalfswarmod.KalfsWarMod;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KalfsWarMod.MODID);

    public static final RegistryObject<SoundEvent> SMALL_EXPLOSION = registerSoundEvents("small_explosion");
    public static final RegistryObject<SoundEvent> MEDIUM_EXPLOSION = registerSoundEvents("medium_explosion");
    public static final RegistryObject<SoundEvent> LARGE_EXPLOSION = registerSoundEvents("large_explosion");
    public static final RegistryObject<SoundEvent> MASSIVE_EXPLOSION = registerSoundEvents("massive_explosion");
    public static final RegistryObject<SoundEvent> GUN_SHOT_1 = registerSoundEvents("gun_shot_1");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(KalfsWarMod.MODID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
