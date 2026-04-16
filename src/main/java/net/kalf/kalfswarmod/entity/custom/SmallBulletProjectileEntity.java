package net.kalf.kalfswarmod.entity.custom;

import net.kalf.kalfswarmod.entity.ModEntities;
import net.kalf.kalfswarmod.item.ModItems;
import net.kalf.kalfswarmod.sound.ModSounds;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class SmallBulletProjectileEntity extends ThrowableItemProjectile {
    public SmallBulletProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    private float bulletDamage;
    private SoundEvent bulletImpactSound;

    public void setDamage(float newDamage) {
        this.bulletDamage = newDamage;
    }

    public void setBulletImpactSound(SoundEvent bulletImpactSound) {
        this.bulletImpactSound = bulletImpactSound;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SMALL_BULLET_ENTITY.get();
    }

    public SmallBulletProjectileEntity(Level pLevel) {
        super(ModEntities.SMALL_BULLET_PROJECTILE.get(), pLevel);
        this.setNoGravity(true);
    }

    public SmallBulletProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.SMALL_BULLET_PROJECTILE.get(), livingEntity, pLevel);
        this.setNoGravity(true);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        net.minecraft.world.entity.Entity hitEntity = pResult.getEntity();

        pResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), this.bulletDamage);
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);

        if (!this.level().isClientSide()) {
            level().playSound(null, this.getX(), this.getY(), this.getZ(),
                    this.bulletImpactSound, SoundSource.PLAYERS, 0.4f, 1.0f);
        }
        this.discard();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.level().addParticle(ParticleTypes.SMOKE, true, this.getX(), this.getY()+0.5d, this.getZ(), 0d, 0d, 0d);
        }
    }
}
