package quickcarpetfixes.mixins.optimizations.random;

import net.minecraft.loot.context.LootContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quickcarpetfixes.QCFSettings;
import quickcarpetfixes.helpers.XoroshiroCustomRandom;

import java.util.Random;

@Mixin(value = LootContext.Builder.class, priority = 1010)
public class LootContext$Builder_randomMixin {


    @Redirect(
            method = "random(J)Lnet/minecraft/loot/context/LootContext$Builder;",
            require = 0,
            at = @At(
                    value = "NEW",
                    target = "java/util/Random"
            )
    )
    private static Random customRandom1(long seed) {
        return QCFSettings.optimizedRandom ? new XoroshiroCustomRandom(seed) : new Random(seed);
    }


    @Redirect(
            method = "random(JLjava/util/Random;)Lnet/minecraft/loot/context/LootContext$Builder;",
            require = 0,
            at = @At(
                    value = "NEW",
                    target = "java/util/Random"
            )
    )
    private static Random customRandom2(long seed) {
        return QCFSettings.optimizedRandom ? new XoroshiroCustomRandom(seed) : new Random(seed);
    }


    @Redirect(
            method = "build(Lnet/minecraft/loot/context/LootContextType;)Lnet/minecraft/loot/context/LootContext;",
            require = 0,
            at = @At(
                    value = "NEW",
                    target = "java/util/Random"
            )
    )
    private static Random customRandom3() {
        return QCFSettings.optimizedRandom ? new XoroshiroCustomRandom() : new Random();
    }
}
