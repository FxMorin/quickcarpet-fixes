package quickcarpetfixes.mixins.entityFixes;

import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quickcarpetfixes.QCFSettings;

@Mixin(HopperMinecartEntity.class)
public class HopperMinecartEntity_slowMixin {


    @Redirect(
            method = "tick()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/BlockPos;equals(Ljava/lang/Object;)Z"
            )
    )
    private boolean notEqual(BlockPos blockPos, Object o) {
        return !QCFSettings.hopperMinecartSlowerAtOriginFix && blockPos.equals(o);
    }
}
