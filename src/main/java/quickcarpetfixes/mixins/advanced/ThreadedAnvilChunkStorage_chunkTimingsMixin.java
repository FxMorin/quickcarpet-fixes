package quickcarpetfixes.mixins.advanced;

import net.minecraft.server.world.ThreadedAnvilChunkStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import quickcarpetfixes.QCFSettings;

@Mixin(ThreadedAnvilChunkStorage.class)
public class ThreadedAnvilChunkStorage_chunkTimingsMixin {


    @ModifyConstant(
            method = "unloadChunks(Ljava/util/function/BooleanSupplier;)V",
            constant = @Constant(intValue = 20)
    )
    private static int modifyMaxChunksUnloadedPerTick(int original) {
        return QCFSettings.maxChunksSavedPerTick;
    }

    
    @ModifyConstant(
            method = "unloadChunks(Ljava/util/function/BooleanSupplier;)V",
            constant = @Constant(intValue = 200)
    )
    private static int modifyMaxChunksUnloadedPerAutoSave(int original) {
        return QCFSettings.maxChunksSavedPerAutoSave;
    }

    
    @ModifyConstant(
            method = "save(Lnet/minecraft/server/world/ChunkHolder;)Z",
            constant = @Constant(longValue = 10000L)
    )
    private static long modifyChunkSavingCooldown(long original) {
        return QCFSettings.reIntroduceVeryAggressiveSaving ? -1L : (long) QCFSettings.chunkSaveCooldownDelay;
    }

    
    @Redirect(
            method = "save(Lnet/minecraft/server/world/ChunkHolder;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/System;currentTimeMillis()J"
            )
    )
    private long shouldRespectCooldown() {
        return QCFSettings.reIntroduceVeryAggressiveSaving ? Long.MAX_VALUE : System.currentTimeMillis();
    }
}
