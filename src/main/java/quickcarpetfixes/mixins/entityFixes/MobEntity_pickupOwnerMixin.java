package quickcarpetfixes.mixins.entityFixes;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quickcarpetfixes.QCFSettings;

@Mixin(MobEntity.class)
public class MobEntity_pickupOwnerMixin {


    @Redirect(
            method = "tickMovement()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/ItemEntity;isRemoved()Z"
            )
    )
    public boolean doesItemHaveOwnerTag(ItemEntity instance) {
        return instance.isRemoved() || QCFSettings.mobsIgnoreOwnerOnPickupFix && instance.getOwner() != null;
    }
}
