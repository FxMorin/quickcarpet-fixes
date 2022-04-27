package quickcarpetfixes.mixins.entityFixes;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.fluid.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quickcarpetfixes.QCFSettings;

@Mixin(ItemFrameEntity.class)
public abstract class ItemFrameEntity_explosionWaterMixin {

    /**
     * Item frames usually break when near an explosion. Although apparently if they are in water they shouldn't break.
     * So we modify the item frames damage check so that if it takes explosion damage, it first checks if its in water
     * and if so, negates the damage.
     * TODO: That is not at all what this bug report should be fixing xD
     */


    ItemFrameEntity self = (ItemFrameEntity)(Object)this;


    @Inject(
            method = "damage",
            at = @At("HEAD"),
            cancellable = true
    )
    public void isInvulnerableOrWater(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (QCFSettings.explosionBreaksItemFrameInWaterFix && source.isExplosive() &&
                self.world.getFluidState(self.getBlockPos()).getFluid().matchesType(Fluids.WATER))
            cir.setReturnValue(true);
    }
}
