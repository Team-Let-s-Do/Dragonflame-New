package satisfy.dragonflame.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class DragonflameExpectPlatformImpl {
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
