package quickcarpetfixes.mixins.entityFixes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quickcarpetfixes.patches.EntityUsedTotem;

@Mixin(LivingEntity.class)
public class LivingEntity_totemMixin implements EntityUsedTotem {

    public boolean usedTotem = false;

    @Override
    public boolean hasUsedTotem() {
        return usedTotem;
    }


    @Inject(
            method = "tick()V",
            at = @At("HEAD")
    )
    public void tickStart(CallbackInfo ci) {
        usedTotem = false;
    }


    @Inject(
            method = "tryUseTotem(Lnet/minecraft/entity/damage/DamageSource;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;setHealth(F)V"
            )
    )
    private void hasUsedATotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        usedTotem = true;
    }
}
