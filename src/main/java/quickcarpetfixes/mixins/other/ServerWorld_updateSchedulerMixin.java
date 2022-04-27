package quickcarpetfixes.mixins.other;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quickcarpetfixes.QCFSettings;
import quickcarpetfixes.helpers.UpdateScheduler;

import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public class ServerWorld_updateSchedulerMixin {

    /**
     * This ticks the updateScheduler
     */


    World self = (World)(Object)this;


    @Inject(
            method = "tick(Ljava/util/function/BooleanSupplier;)V",
            at = @At("HEAD")
    )
    public void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        QCFSettings.updateScheduler.computeIfAbsent(self, (a) -> new UpdateScheduler((ServerWorld)a)).tick();
    }
}
