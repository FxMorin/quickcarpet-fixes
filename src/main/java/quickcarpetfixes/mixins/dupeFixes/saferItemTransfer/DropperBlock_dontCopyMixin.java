package quickcarpetfixes.mixins.dupeFixes.saferItemTransfer;

import net.minecraft.block.DropperBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quickcarpetfixes.QCFSettings;

@Mixin(DropperBlock.class)
public class DropperBlock_dontCopyMixin {

    private static ItemStack stack = ItemStack.EMPTY;


    @Redirect(
            method = "dispense(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)V",
            slice = @Slice(
                    from = @At("HEAD"),
                    to = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/item/ItemStack;isEmpty()Z",
                            ordinal = 1
                    )
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;copy()Lnet/minecraft/item/ItemStack;",
                    ordinal = 0
            )
    )
    protected ItemStack shouldCopyFirst(ItemStack itemStack) {
        stack = itemStack.copy();
        return QCFSettings.saferItemTransfers ? itemStack : stack;
    }


    @Redirect(
            method = "dispense(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)V",
            slice = @Slice(
                    from = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/item/ItemStack;isEmpty()Z",
                            ordinal = 1
                    )
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;copy()Lnet/minecraft/item/ItemStack;"
            )
    )
    protected ItemStack shouldCopy(ItemStack itemStack) {
        return QCFSettings.saferItemTransfers ? stack : itemStack.copy();
    }


    @Inject(
            method = "dispense(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)V",
            at = @At("TAIL")
    )
    protected void removeInstance(ServerWorld world, BlockPos pos, CallbackInfo ci) {
        stack = ItemStack.EMPTY;
    }
}
