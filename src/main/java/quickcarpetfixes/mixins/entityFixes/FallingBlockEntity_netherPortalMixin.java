package quickcarpetfixes.mixins.entityFixes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quickcarpetfixes.QCFSettings;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntity_netherPortalMixin extends Entity {

    /**
     * Since falling blocks override the tick() method, they "forgot" to add the nether portal ticking
     */


    public FallingBlockEntity_netherPortalMixin(EntityType<?> type, World world) {
        super(type, world);
    }


    @Inject(
            method = "tick()V",
            at = @At("HEAD")
    )
    public void tickNetherPortal(CallbackInfo ci) {
        if (QCFSettings.fallingBlocksCantUseNetherPortalsFix) this.tickNetherPortal();
    }
}
