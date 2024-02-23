package satisfy.dragonflame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import satisfy.dragonflame.client.DragonflameClient;
import satisfy.dragonflame.config.DragonflameConfig;
import satisfy.dragonflame.registry.*;

public class Dragonflame {

	public static final String MOD_ID = "dragonflame";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static void init() {
		DragonflameClient.preInitClient();
		DragonflameConfig.DEFAULT.getConfig();
		//ParticleRegistry.init();
		ObjectRegistry.init();
		BlockEntityRegistry.init();
		ScreenhandlerTypeRegistry.init();
		TabRegistry.init();
		EntityRegistry.init();
		MobEffectRegistry.init();
		EnchantmentRegistry.init();
		PlacerTypesRegistry.init();
	}
}