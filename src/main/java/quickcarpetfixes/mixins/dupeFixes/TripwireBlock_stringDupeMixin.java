package quickcarpetfixes.mixins.dupeFixes;

import net.minecraft.block.TripwireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import quickcarpetfixes.QCFSettings;

@Mixin(TripwireBlock.class)
public class TripwireBlock_stringDupeMixin {


    @ModifyArg(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/TripwireHookBlock;update(Lnet/minecraft/world/World;" +
                            "Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;" +
                            "ZZILnet/minecraft/block/BlockState;)V"
            ),
            index = 5
    )
    private int alwaysNegativeOne(int i) {
        return QCFSettings.stringDupeFix ? -1 : i;
    }
}
