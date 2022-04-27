package quickcarpetfixes.mixins.blockFixes;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import quickcarpetfixes.QCFSettings;

@Mixin(AbstractButtonBlock.class)
public abstract class AbstractButtonBlock_collisionOnPlaceMixin extends AbstractBlock {

    public AbstractButtonBlock_collisionOnPlaceMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    protected abstract void tryPowerWithProjectiles(BlockState state, World world, BlockPos pos);


    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (QCFSettings.projectileNotDetectedOnPlaceFix && !oldState.isOf(state.getBlock()))
            this.tryPowerWithProjectiles(state,world,pos);
    }
}
