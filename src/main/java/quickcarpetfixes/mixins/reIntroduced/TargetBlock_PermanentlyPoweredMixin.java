package quickcarpetfixes.mixins.reIntroduced;

import net.minecraft.block.BlockState;
import net.minecraft.block.TargetBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quickcarpetfixes.QCFSettings;

@Mixin(TargetBlock.class)
public class TargetBlock_PermanentlyPoweredMixin {

    /**
     * Make it so that the targetBlock does not do any checks when placed. Just like it used to do during the snapshot
     * that its ability to redirect redstone was added, so its movable while keeping its power level permanently.
     */


    @Inject(
            method = "onBlockAdded(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;" +
                    "Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onBlockAdded(BlockState state, World world, BlockPos pos,
                             BlockState oldState, boolean notify, CallbackInfo ci) {
        if (QCFSettings.reIntroduceTargetBlockPermanentlyPowered) ci.cancel();
    }
}
