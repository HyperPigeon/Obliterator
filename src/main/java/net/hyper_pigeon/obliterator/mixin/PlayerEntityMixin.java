package net.hyper_pigeon.obliterator.mixin;

import net.hyper_pigeon.obliterator.Obliterator;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "canHarvest", at = @At("RETURN"), cancellable = true)
    public void destroyBlockWhenUsingObliterate(BlockState state, CallbackInfoReturnable<Boolean> cir){
        int i = EnchantmentHelper.getEquipmentLevel(Obliterator.OBLITERATE, this);
        if(i > 0){
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "getBlockBreakingSpeed",at = @At("RETURN"), cancellable = true)
    public void addObliterateSpeed(BlockState block, CallbackInfoReturnable<Float> cir){
        float f = cir.getReturnValueF();
        if (f > 1.0F) {
            int i = EnchantmentHelper.getEquipmentLevel(Obliterator.OBLITERATE, this);
            ItemStack itemStack = this.getMainHandStack();
            if (i > 0 && !itemStack.isEmpty()) {
                f *= 15;
            }
        }

        cir.setReturnValue(f);
    }


}
