package rb13.addon.jade.unhide;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JadeUnhide implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("jade-unhide");
	@Override
	public void onInitializeClient() {
		LOGGER.info("Jade Addon Loading...");
	}
}