package quickcarpetfixes.mixins.reIntroduced;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quickcarpetfixes.QCFSettings;
import quickcarpetfixes.QuickCarpetFixesServer;

import java.util.function.Function;

@Mixin(PlayerManager.class)
public abstract class PlayerManager_LlamaRidingDupeMixin {

    /**
     * Reimplements the dupe method where player1 can look into a Llama's inventory. Then player2 gets on the llama
     * and disconnects. Player1 can then take the items out of the llama's inventory.
     * Then when player2 logs back in, the items will still be in the llama's inventory. Duping it.
     */


    @Redirect(
            method = "onPlayerConnect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/EntityType;loadEntityWithPassengers(" +
                            "Lnet/minecraft/nbt/NbtCompound;Lnet/minecraft/world/World;" +
                            "Ljava/util/function/Function;)Lnet/minecraft/entity/Entity;"
            )
    )
    private @Nullable Entity llamaReplaceOnConnect(NbtCompound nbt, World world,
                                                   Function<Entity, Entity> entityProcessor){
        if (QCFSettings.reIntroduceDonkeyRidingDupe) {
            EntityType.loadEntityWithPassengers(nbt, world, (vehicle) -> {
                Entity before = ((ServerWorld)world).getEntity(vehicle.getUuid());
                if (before != null) {
                    before.readNbt(vehicle.writeNbt(new NbtCompound()));
                    return before;
                }
                return !((ServerWorld)world).tryLoadEntity(vehicle) ? null : vehicle;
            });
        }
        return EntityType.loadEntityWithPassengers(nbt, world, entityProcessor);
    }


    @Redirect(
            method = "remove",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayerEntity;hasVehicle()Z"
            )
    )
    private boolean llamaDupeOnRemove(ServerPlayerEntity serverPlayerEntity){
        if (serverPlayerEntity.hasVehicle()) {
            if(!QCFSettings.reIntroduceDonkeyRidingDupe) return true;
            Entity entity = serverPlayerEntity.getRootVehicle();
            if (entity.hasPlayerRider()) {
                QuickCarpetFixesServer.LOGGER.debug("[reIntroduceDonkeyRidingDupe] Removing player mount");
                serverPlayerEntity.stopRiding();
                entity.streamPassengersAndSelf().forEach((entityx) -> {
                    entityx.setRemoved(Entity.RemovalReason.UNLOADED_WITH_PLAYER);
                });
            }
        }
        return false;
    }
}
