package net.kalf.kalfswarmod.item;

import net.kalf.kalfswarmod.KalfsWarMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KalfsWarMod.MODID);

    public static final RegistryObject<CreativeModeTab> MY_TAB = CREATIVE_MODE_TABS.register("my_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STEEL_INGOT.get()))
                    .title(Component.translatable("creativetab.my_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.STEEL_INGOT.get());
                        pOutput.accept(ModItems.SMALL_BULLET.get());
                        pOutput.accept(ModItems.GRENADE.get());
                        pOutput.accept(ModItems.AK47.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register((eventBus));
    }
}
