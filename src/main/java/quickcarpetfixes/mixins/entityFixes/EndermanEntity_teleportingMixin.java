package quickcarpetfixes.mixins.entityFixes;

import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quickcarpetfixes.QCFSettings;

@Mixin(EndermanEntity.class)
public class EndermanEntity_teleportingMixin {

    /**
     * Enderman will sometimes attempt to teleport while they are in a minecart. There is no reason for them to do this
     * So we prevent them from doing so by changing the brightness that they see.
     */


    @Redirect(
            method = "mobTick()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/EndermanEntity;getBrightnessAtEyes()F"
            )
    )
    public float brightnessAndMinecart(EndermanEntity entity) {
        return (QCFSettings.endermanUselessMinecartTeleportingFix && entity.hasVehicle()) ?
                0.0f :
                entity.getBrightnessAtEyes();
    }
}
