package quickcarpetfixes.mixins.goalFixes;

import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.ai.goal.StepAndDestroyBlockGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quickcarpetfixes.QCFSettings;

@Mixin(StepAndDestroyBlockGoal.class)
public abstract class StepAndDestroyBlockGoal_originMixin extends MoveToTargetPosGoal {

    public StepAndDestroyBlockGoal_originMixin(PathAwareEntity mob, double speed, int range) {
        super(mob, speed, range);
    }

    @Inject(
            method = "hasAvailableTarget()Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onHasAvailableTarget(CallbackInfoReturnable<Boolean> cir) {
        if (QCFSettings.stepAndDestroyBlockGoalUsesOriginFix) {
            cir.setReturnValue(
                    this.targetPos != null &&
                    this.mob.isInWalkTargetRange(this.targetPos) &&
                    this.isTargetPos(this.mob.world, this.targetPos) ||
                    this.findTargetPos()
            );
        }
    }
}
