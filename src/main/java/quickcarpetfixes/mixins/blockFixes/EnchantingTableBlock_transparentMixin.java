package quickcarpetfixes.mixins.blockFixes;

import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quickcarpetfixes.QCFSettings;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableBlock_transparentMixin {


    @Redirect(
            method = "canAccessBookshelf(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;" +
                    "Lnet/minecraft/util/math/BlockPos;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;isAir(Lnet/minecraft/util/math/BlockPos;)Z"
            ),
            require = 0
    )
    private static boolean isTranslucent(World world, BlockPos pos) {
        return QCFSettings.transparentBlocksNegateEnchantingFix ?
                !world.getBlockState(pos).isFullCube(world,pos) : world.isAir(pos);
    }
}
