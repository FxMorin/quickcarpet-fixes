package quickcarpetfixes.mixins.entityFixes;

import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quickcarpetfixes.QCFSettings;

@Mixin(EndermanEntity.class)
public class EndermanEntity_noAiTeleportMixin {

    @Inject(
            method = "teleportRandomly",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelTeleportNoAI(CallbackInfoReturnable<Boolean> cir) {
        if (QCFSettings.endermanTeleportWithoutAIFix && ((EndermanEntity)(Object)this).isAiDisabled())
            cir.setReturnValue(false);
    }
}
