package quickcarpetfixes.mixins.blockUpdates;

import net.minecraft.block.Block;
import net.minecraft.block.ObserverBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import quickcarpetfixes.QCFSettings;

@Mixin(ObserverBlock.class)
public class ObserverBlock_missingUpdateMixin {


    @ModifyArg(
            method = "onBlockAdded(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;" +
                    "Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;" +
                            "setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"
            ),
            index = 2
    )
    public int observerUpdate(int val) {
        return QCFSettings.observerUpdateFix ? val | Block.NOTIFY_NEIGHBORS : val;
    }
}
