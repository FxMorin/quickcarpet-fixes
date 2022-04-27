package quickcarpetfixes.mixins.reIntroduced;

import net.minecraft.block.BlockState;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quickcarpetfixes.QCFSettings;

@Mixin(LightningRodBlock.class)
public class LightningRodBlock_PermanentlyPoweredMixin {

    /**
     * Make it so that the targetBlock does not do any check when added. Just like it used to be, so its movable while
     * keeping its power level. We also need to prevent it from giving updates when removed.
     */


    @Inject(
            method = "onBlockAdded(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;" +
                    "Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onBlockAdded(BlockState state, World world, BlockPos pos,
                             BlockState oldState, boolean notify, CallbackInfo ci) {
        if (QCFSettings.reIntroduceLightningRodPermanentlyPowered) ci.cancel();
    }


    @Inject(
            method = "onStateReplaced(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;" +
                    "Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onStateReplaced(BlockState state, World world, BlockPos pos,
                                BlockState oldState, boolean notify, CallbackInfo ci) {
        if (QCFSettings.reIntroduceLightningRodPermanentlyPowered) ci.cancel();
    }
}
