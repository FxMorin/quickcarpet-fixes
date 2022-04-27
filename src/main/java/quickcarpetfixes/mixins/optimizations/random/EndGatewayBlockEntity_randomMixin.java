package quickcarpetfixes.mixins.optimizations.random;

import net.minecraft.block.entity.EndGatewayBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quickcarpetfixes.QCFSettings;
import quickcarpetfixes.helpers.XoroshiroCustomRandom;

import java.util.Random;

@Mixin(value = EndGatewayBlockEntity.class, priority = 1010)
public class EndGatewayBlockEntity_randomMixin {


    @Redirect(
            method = "createPortal(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;" +
                    "Lnet/minecraft/world/gen/feature/EndGatewayFeatureConfig;)V",
            require = 0,
            at = @At(
                    value = "NEW",
                    target = "java/util/Random"
            )
    )
    private static Random customRandom() {
        return QCFSettings.optimizedRandom ? new XoroshiroCustomRandom() : new Random();
    }
}
