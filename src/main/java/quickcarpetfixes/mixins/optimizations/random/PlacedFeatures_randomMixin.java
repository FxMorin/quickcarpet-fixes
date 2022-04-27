package quickcarpetfixes.mixins.optimizations.random;

import net.minecraft.world.gen.feature.PlacedFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quickcarpetfixes.QCFSettings;
import quickcarpetfixes.helpers.XoroshiroCustomRandom;

import java.util.Random;

@Mixin(value = PlacedFeatures.class, priority = 1010)
public class PlacedFeatures_randomMixin {


    @Redirect(
            method = "*",
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
