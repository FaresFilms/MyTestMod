package net.kalf.kalfswarmod.item;

import net.kalf.kalfswarmod.KalfsWarMod;
import net.kalf.kalfswarmod.sound.ModSounds;
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
    public static final RegistryObject<Item> LARGE_BULLET = ITEMS.register("large_bullet",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE = ITEMS.register("grenade",
            () -> new GrenadeItem(new Item.Properties()));

    public static final RegistryObject<Item> SMALL_BULLET_ENTITY = ITEMS.register("small_bullet_entity",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LARGE_BULLET_ENTITY = ITEMS.register("large_bullet_entity",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AK47 = ITEMS.register("ak_47",
            () -> new BaseGunItem(new Item.Properties().stacksTo(1), 4, SMALL_BULLET.get(), 5, 3, ModSounds.GUN_SHOT_1, ModSounds.SMALL_EXPLOSION));

    public static final RegistryObject<Item> HUNTING_RIFLE = ITEMS.register("hunting_rifle",
            () -> new BaseGunItem(new Item.Properties().stacksTo(1), 40, LARGE_BULLET.get(), 20,  4, ModSounds.GUN_SHOT_1, ModSounds.BULLET_RICOCHET));

    public static final RegistryObject<Item> P90 = ITEMS.register("p90",
            () -> new BaseGunItem(new Item.Properties().stacksTo(1), 2, SMALL_BULLET.get(), 4,  4, ModSounds.GUN_SHOT_1, ModSounds.SMALL_EXPLOSION));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
