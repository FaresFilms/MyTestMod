package net.kalf.kalfswarmod.entity;

import net.kalf.kalfswarmod.KalfsWarMod;
import net.kalf.kalfswarmod.entity.custom.GrenadeProjectileEntity;
import net.kalf.kalfswarmod.entity.custom.SmallBulletProjectileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, KalfsWarMod.MODID);

    public static final RegistryObject<EntityType<GrenadeProjectileEntity>> GRENADE_PROJECTILE =
            ENTITY_TYPES.register("grenade_projectile", () -> EntityType.Builder.<GrenadeProjectileEntity>of(GrenadeProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("grenade_projectile"));

    public static final RegistryObject<EntityType<SmallBulletProjectileEntity>> SMALL_BULLET_PROJECTILE =
            ENTITY_TYPES.register("small_bullet_projectile", () -> EntityType.Builder.<SmallBulletProjectileEntity>of(SmallBulletProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("small_bullet_projectile"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
