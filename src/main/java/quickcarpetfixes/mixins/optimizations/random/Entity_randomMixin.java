package quickcarpetfixes.mixins.optimizations.random;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quickcarpetfixes.QCFSettings;
import quickcarpetfixes.helpers.XoroshiroCustomRandom;

import java.util.Random;

@Mixin(value = Entity.class, priority = 1010)
public class Entity_randomMixin {

    private static Random rand = null; //Shared random instance for all entities


    @Redirect(
            method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;)V",
            require = 0,
            at = @At(
                    value = "NEW",
                    target = "java/util/Random"
            )
    )
    private Random CustomRandom() {
        if (!QCFSettings.optimizedRandom) {
            if (QCFSettings.entityRandomCrackingFix) return rand == null ? rand = new Random() : rand;
            return new Random();
        }
        if (QCFSettings.entityRandomCrackingFix) return rand == null ? rand = new XoroshiroCustomRandom() : rand;
        return new XoroshiroCustomRandom();
    }
}

