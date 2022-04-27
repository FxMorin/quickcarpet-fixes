package quickcarpetfixes.mixins.coreSystemFixes;

import net.minecraft.entity.boss.dragon.EnderDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import quickcarpetfixes.QCFSettings;

@Mixin(EnderDragonFight.class)
public class EnderDragonFight_twoCrystalMixin {


    @ModifyArg(
            method = "respawnDragon()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/BlockPos;offset(Lnet/minecraft/util/math/Direction;I)" +
                            "Lnet/minecraft/util/math/BlockPos;"
            ),
            index = 1
    )
    private int modifyDist(int dist) {
        return QCFSettings.respawnDragonWithoutAllEndCrystalsFix ? 3 : dist;
    }
}
