package quickcarpetfixes.mixins.playerFixes;

import net.minecraft.network.packet.s2c.play.EntityStatusEffectS2CPacket;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quickcarpetfixes.QCFSettings;

@Mixin(PlayerManager.class)
public class PlayerManager_endPortalEffectsMixin {


    @Inject(
            method = "respawnPlayer",
            at = @At("RETURN")
    )
    public void respawnPlayer(ServerPlayerEntity player, boolean alive,
                              CallbackInfoReturnable<ServerPlayerEntity> cir) {
        if (alive && QCFSettings.endPortalRemovesEffectsFix) {
            ServerPlayerEntity newPlayer = cir.getReturnValue();
            newPlayer.activeStatusEffects.forEach((key,effect) ->
                    newPlayer.networkHandler.sendPacket(new EntityStatusEffectS2CPacket(newPlayer.getId(), effect))
            );
        }
    }
}
