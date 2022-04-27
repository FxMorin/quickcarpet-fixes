package quickcarpetfixes.mixins.blockEntityFixes;

import net.minecraft.block.entity.SculkSensorBlockEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quickcarpetfixes.QCFSettings;

@Mixin(SculkSensorBlockEntity.class)
public class SculkSensorBlockEntity_roundMixin {


    @Inject(
            method = "getPower(II)I",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void getPower(int distance, int range, CallbackInfoReturnable<Integer> cir) {
        double d = (double)distance / (double)range;
        cir.setReturnValue(QCFSettings.sculkSensorPrecisionLossFix ?
                MathHelper.floor(d*-14.99d+15.99d) :
                Math.max(1,15-MathHelper.floor(((double)distance / (double)range)*15.0D))
        );
    }
}
