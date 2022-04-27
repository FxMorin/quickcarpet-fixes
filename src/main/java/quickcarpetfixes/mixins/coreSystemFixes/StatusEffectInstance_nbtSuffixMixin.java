package quickcarpetfixes.mixins.coreSystemFixes;

import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import quickcarpetfixes.QCFSettings;

@Mixin(StatusEffectInstance.class)
public class StatusEffectInstance_nbtSuffixMixin {


    @ModifyArg(
            method = "fromNbt(Lnet/minecraft/entity/effect/StatusEffect;Lnet/minecraft/nbt/NbtCompound;)" +
                    "Lnet/minecraft/entity/effect/StatusEffectInstance;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/nbt/NbtCompound;contains(Ljava/lang/String;I)Z"
            ),
            index = 1
    )
    private static int incorrectNbtCheck(int value) {
        if (value == 1) return QCFSettings.incorrectNbtChecks ? 99 : 1;
        return value;
    }
}
