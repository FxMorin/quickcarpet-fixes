package quickcarpetfixes.mixins.coreSystemFixes;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.UpgradeData;
import net.minecraft.world.gen.chunk.BlendingData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quickcarpetfixes.QCFSettings;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Mixin(Chunk.class)
public class Chunk_BEHashMixin {

    @Mutable
    @Final
    @Shadow
    protected Map<BlockPos, BlockEntity> blockEntities;

    @Mutable
    @Shadow
    @Final
    protected Map<BlockPos, NbtCompound> blockEntityNbts;


    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void reloadNewHashMap(ChunkPos pos, UpgradeData upgradeData, HeightLimitView heightLimitView,
                                  Registry<Biome> biome, long inhabitedTime, ChunkSection[] sectionArrayInitializer,
                                  BlendingData blendingData, CallbackInfo ci) {
        if (QCFSettings.reloadUpdateOrderFix) {
            blockEntityNbts = new LinkedHashMap<>();
            blockEntities = new LinkedHashMap<>();
        }
    }


    @Inject(
            method = "getBlockEntityPositions()Ljava/util/Set;",
            at = @At("HEAD"),
            cancellable = true
    )
    public void getBlockEntityPositions(CallbackInfoReturnable<Set<BlockPos>> cir) {
        if (QCFSettings.reloadUpdateOrderFix) { //Use a Linked Hash Set instead of just a HashSet
            Set<BlockPos> set = new LinkedHashSet<>(this.blockEntityNbts.keySet());
            set.addAll(this.blockEntities.keySet());
            cir.setReturnValue(set);
        }
    }
}
