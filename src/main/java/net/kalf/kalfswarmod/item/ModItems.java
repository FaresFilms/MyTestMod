package net.kalf.kalfswarmod.item;

import net.kalf.kalfswarmod.KalfsWarMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KalfsWarMod.MODID);

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SMALL_BULLET = ITEMS.register("small_bullet",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE = ITEMS.register("grenade",
            () -> new GrenadeItem(new Item.Properties()));

    public static final RegistryObject<Item> BULLET_ENTITY = ITEMS.register("bullet_entity",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AK47 = ITEMS.register("ak_47",
            () -> new AK47(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
