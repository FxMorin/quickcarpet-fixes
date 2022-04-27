package quickcarpetfixes.mixins.gameEventFixes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quickcarpetfixes.QCFSettings;

@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntity_missingOcclusionMixin extends Entity {

    public ProjectileEntity_missingOcclusionMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract @Nullable Entity getOwner();


    @Inject(
            method = "onCollision(Lnet/minecraft/util/hit/HitResult;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/ProjectileEntity;" +
                            "emitGameEvent(Lnet/minecraft/world/event/GameEvent;Lnet/minecraft/entity/Entity;)V"
            )
    )
    protected void onEmittingGaveEvent(HitResult hitResult, CallbackInfo ci) {
        if (QCFSettings.projectileMissingOcclusionFix && hitResult.getType() == HitResult.Type.BLOCK) {
            if (this.world.getBlockState(new BlockPos(hitResult.getPos())).isIn(BlockTags.OCCLUDES_VIBRATION_SIGNALS))
                return;
        }
        this.emitGameEvent(GameEvent.PROJECTILE_LAND, this.getOwner());
    }


    @Redirect(
            method = "onCollision(Lnet/minecraft/util/hit/HitResult;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/ProjectileEntity;" +
                            "emitGameEvent(Lnet/minecraft/world/event/GameEvent;Lnet/minecraft/entity/Entity;)V"
            )
    )
    protected void cancelEmitGameEvent(ProjectileEntity instance, GameEvent gameEvent, Entity entity) {}
}
