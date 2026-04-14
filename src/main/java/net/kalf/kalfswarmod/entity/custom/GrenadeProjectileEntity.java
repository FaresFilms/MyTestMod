package net.kalf.kalfswarmod.entity.custom;

import net.kalf.kalfswarmod.entity.ModEntities;
import net.kalf.kalfswarmod.item.ModItems;
import net.kalf.kalfswarmod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class GrenadeProjectileEntity extends ThrowableItemProjectile {

    public GrenadeProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public GrenadeProjectileEntity(Level pLevel) {
        super(ModEntities.GRENADE_PROJECTILE.get(), pLevel);
    }

    public GrenadeProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.GRENADE_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.GRENADE.get();
    }

    @Override
    protected void onHit(HitResult pResult) {
        if (!this.level().isClientSide()) {
            int radius = 4;
            BlockPos center = this.blockPosition();

            AABB damageBox = new AABB(this.blockPosition()).inflate(radius);
            List<LivingEntity> victims = this.level().getEntitiesOfClass(LivingEntity.class, damageBox);

            for (LivingEntity victim : victims) {
                double distance = this.distanceTo(victim);
                if (distance <= radius) {
                    float damage_amount = (float) (1.0 - (distance / radius)) * 20.0f;
                    victim.hurt(this.damageSources().thrown(this, this.getOwner()), damage_amount);

                    double dx = victim.getX() - this.getX();
                    double dy = victim.getY() - this.getY();
                    double dz = victim.getZ() - this.getZ();

                    net.minecraft.world.phys.Vec3 pushDirection = new net.minecraft.world.phys.Vec3(dx, dy, dz).normalize();

                    double knockbackPower = (1.0 - (distance / radius)) * 1.5;

                    victim.push(pushDirection.x * knockbackPower, pushDirection.y * knockbackPower, pushDirection.z * knockbackPower);

                }
            }

            for (int x = -radius; x <= radius; x++){
                for (int y = -radius; y <= radius; y++){
                    for (int z = -radius; z <= radius; z++) {

                        BlockPos targetPos = center.offset(x, y, z);

                        float randomNoise = this.level().random.nextFloat()*2.0f;
                        float adjustedRadius = radius - randomNoise;

                        if (targetPos.distSqr(center) <= adjustedRadius*adjustedRadius){
                            this.level().setBlock(targetPos, Blocks.AIR.defaultBlockState(), 2 | 16);
                        }

                    }
                }
            }
            this.level().playSound(null, center.getX(), center.getY(), center.getZ(),
                    ModSounds.SMALL_EXPLOSION.get(), SoundSource.BLOCKS, 4.0f, 1.0f);
            this.discard();
        }
        super.onHit(pResult);
    }
}
