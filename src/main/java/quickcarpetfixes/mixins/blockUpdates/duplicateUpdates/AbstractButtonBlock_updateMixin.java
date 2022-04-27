package quickcarpetfixes.mixins.blockUpdates.duplicateUpdates;

import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quickcarpetfixes.QCFSettings;

@Mixin(AbstractButtonBlock.class)
public abstract class AbstractButtonBlock_updateMixin extends WallMountedBlock {

    AbstractButtonBlock self = (AbstractButtonBlock)(Object)this;

    protected AbstractButtonBlock_updateMixin(Settings settings) {
        super(settings);
    }


    @ModifyArg(
            method = "tryPowerWithProjectiles",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;" +
                            "setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"
            ),
            index = 2
    )
    private int modifyProjectileUpdate(int val) {
        return QCFSettings.duplicateBlockUpdatesFix ? val & ~Block.NOTIFY_NEIGHBORS : val;
    }


    @ModifyArg(
            method = "powerOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;" +
                            "setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"
            ),
            index = 2
    )
    private int modifyPowerUpdate(int val) {
        return QCFSettings.duplicateBlockUpdatesFix ? val & ~Block.NOTIFY_NEIGHBORS : val;
    }


    @ModifyArg(
            method = "scheduledTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/ServerWorld;" +
                            "setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"
            ),
            index = 2
    )
    private int modifyScheduledUpdate(int val) {
        return QCFSettings.duplicateBlockUpdatesFix ? val & ~Block.NOTIFY_NEIGHBORS : val;
    }


    @Inject(
            method = "updateNeighbors(Lnet/minecraft/block/BlockState;" +
                    "Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void updateNeighborsBetter(BlockState state, World world, BlockPos pos, CallbackInfo ci) {
        if (QCFSettings.duplicateBlockUpdatesFix) {
            world.updateNeighborsAlways(pos, self);
            Direction dir = getDirection(state);
            world.updateNeighborsExcept(pos.offset(dir.getOpposite()), self, dir);
            ci.cancel();
        }
    }
}
