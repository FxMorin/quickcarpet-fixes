package quickcarpetfixes.mixins.gameEventFixes.stepEvent;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import quickcarpetfixes.patches.ServerPlayerEntityEmitStep;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntity_stepEventMixin implements ServerPlayerEntityEmitStep {

    private boolean shouldStep = false;

    @Override
    public boolean shouldStep() {
        boolean ss = shouldStep;
        shouldStep = false;
        return ss;
    }

    @Override
    public void setShouldStep() {
        this.shouldStep = true;
    }
}
