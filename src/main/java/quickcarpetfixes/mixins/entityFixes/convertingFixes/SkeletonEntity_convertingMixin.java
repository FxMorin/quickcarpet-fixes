package quickcarpetfixes.mixins.entityFixes.convertingFixes;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quickcarpetfixes.QCFSettings;

@Mixin(SkeletonEntity.class)
public abstract class SkeletonEntity_convertingMixin extends AbstractSkeletonEntity {

    protected SkeletonEntity_convertingMixin(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }


    @Inject(
            method = "isConverting()Z",
            at = @At("RETURN"),
            cancellable = true
    )
    public void isConverting(CallbackInfoReturnable<Boolean> cir) {
        if (QCFSettings.mobsConvertingWithoutBlocksFix) cir.setReturnValue(cir.getReturnValue() && this.inPowderSnow);
    }
}
