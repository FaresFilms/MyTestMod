package net.kalf.kalfswarmod.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class AK47 extends Item{
    public AK47(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return net.minecraft.world.item.UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }

    // 1. Stop the player from breaking blocks when holding the gun
    @Override
    public boolean canAttackBlock(net.minecraft.world.level.block.state.BlockState pState, net.minecraft.world.level.Level pLevel, net.minecraft.core.BlockPos pPos, net.minecraft.world.entity.player.Player pPlayer) {
        // Returning false tells Minecraft: "This item cannot mine blocks."
        return false;
    }

    // 2. Stop the arm from swinging!
    @Override
    public boolean onEntitySwing(net.minecraft.world.item.ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
        // Returning true tells Forge: "I have already handled the custom swing animation, don't play the vanilla one."
        // (Since we don't actually play an animation, the arm just stays perfectly still!)
        return true;
    }
}
