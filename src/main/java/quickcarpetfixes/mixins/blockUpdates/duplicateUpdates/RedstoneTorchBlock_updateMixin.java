package quickcarpetfixes.mixins.blockUpdates.duplicateUpdates;

import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quickcarpetfixes.QCFSettings;
import quickcarpetfixes.helpers.BlockUpdateUtils;

@Mixin(RedstoneTorchBlock.class)
public abstract class RedstoneTorchBlock_updateMixin extends TorchBlock {

    RedstoneTorchBlock self = (RedstoneTorchBlock)(Object)this;

    protected RedstoneTorchBlock_updateMixin(Settings settings, ParticleEffect particle) {
        super(settings, particle);
    }


    @Inject(
            method = "onStateReplaced",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onStateReplacedBetter(BlockState state, World world, BlockPos pos,
                                      BlockState newState, boolean moved, CallbackInfo ci) {
        if (!moved) { //Was missing: !state.isOf(newState.getBlock()) && state.get(LIT)
            if (QCFSettings.duplicateBlockUpdatesFix) {
                if (!state.isOf(newState.getBlock()) && state.get(RedstoneTorchBlock.LIT)) {
                    BlockUpdateUtils.doExtendedBlockUpdates(
                            world,
                            pos,
                            self,
                            state.get(RedstoneTorchBlock.LIT) & !newState.equals(state),
                            true
                    );
                }
            } else { // Do Settings.uselessSelfBlockUpdateFix here
                boolean doExtraEarlyUpdate = state.get(RedstoneTorchBlock.LIT) & !newState.equals(state);
                BlockUpdateUtils.doExtendedBlockUpdates(world,pos,self,doExtraEarlyUpdate,true);
            }
        }
        ci.cancel();
    }


    @Inject(
            method = "onBlockAdded",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onBlockAddedBetter(BlockState state, World world, BlockPos pos,
                                   BlockState newState, boolean moved, CallbackInfo ci) {
        BlockUpdateUtils.doExtendedBlockUpdates(world,pos,self,false,true);
        ci.cancel();
    }
}
