package quickcarpetfixes.mixins.advanced;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import quickcarpetfixes.QCFSettings;

@Mixin(value = MinecraftServer.class, priority = 900)
public class MinecraftServer_autosaveDelayMixin {


    // QuickCarpet has a conflict with this
    @ModifyConstant(
            method = "tick(Ljava/util/function/BooleanSupplier;)V",
            constant = @Constant(intValue = 6000),
            require = 0
    )
    public int tickAutoSave(int autoSaveDelay) {
        return QCFSettings.delayBetweenAutoSaves;
    }

    
    @ModifyConstant(
            method = "canExecute(Lnet/minecraft/server/ServerTask;)Z",
            constant = @Constant(intValue = 3) //statusUpdateDelay
    )
    public int shouldRunWithLatency(int maxTickLatency) {
        return QCFSettings.maxTickLatency;
    }

    
    @ModifyConstant(
            method = "tick(Ljava/util/function/BooleanSupplier;)V",
            constant = @Constant(longValue = 5000000000L)
    )
    public long customStatusUpdateDelay(long maxTickLatency) {
        return QCFSettings.statusUpdateDelay;
    }
}
