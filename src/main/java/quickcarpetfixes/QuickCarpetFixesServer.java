package quickcarpetfixes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.border.WorldBorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickcarpet.api.module.QuickCarpetModule;
import quickcarpetfixes.helpers.UpdateScheduler;

public class QuickCarpetFixesServer implements QuickCarpetModule, ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(QuickCarpetFixesServer.class);

    private static final String MOD_ID = "quickcarpet-fixes";
    private static final String MOD_NAME;
    private static final Version MOD_VERSION;

    static {
        ModMetadata metadata = FabricLoader.getInstance().getModContainer(MOD_ID)
                .orElseThrow(RuntimeException::new).getMetadata();
        MOD_NAME = metadata.getName();
        MOD_VERSION = metadata.getVersion();
    }

    @Override
    public void onInitialize() {}

    @Override
    public void onWorldLoaded(ServerWorld world) {
        QCFSettings.updateScheduler.put(world, new UpdateScheduler(world));
    }

    @Override
    public void tick(MinecraftServer server) {
        if (QCFSettings.scheduleWorldBorderReset) {
            QCFSettings.scheduleWorldBorderReset = false;
            WorldBorder worldBorder = server.getOverworld().getWorldBorder();
            worldBorder.setSize(worldBorder.getSize()); //Forces the worldborder to update its voxelShape cache
        }
    }

    @Override
    public String getName() {
        return MOD_NAME;
    }

    @Override
    public String getVersion() {
        return MOD_VERSION.getFriendlyString();
    }

    @Override
    public String getId() {
        return MOD_ID;
    }
}
