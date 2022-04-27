package quickcarpetfixes.mixins.coreSystemFixes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quickcarpetfixes.QCFSettings;
import quickcarpetfixes.helpers.Utils;

@Mixin(Explosion.class)
public class Explosion_worldBorderMixin {


    @Redirect(
            method = "collectBlocksAndDamageEntities()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;isInBuildLimit(Lnet/minecraft/util/math/BlockPos;)Z"
            )
    )
    public boolean collectBlocksAndDamageEntities(World instance, BlockPos pos) {
        if (QCFSettings.explosionsBypassWorldBorderFix) return Utils.isInModifiableLimit(instance, pos);
        return instance.isInBuildLimit(pos);
    }
}
