package dev.mineland.smoothpickup;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SmoothPickup {
    public static final String MOD_ID = "smoothpickup";
    public static final Logger logger = LoggerFactory.getLogger(MOD_ID);

    public static SmoothPickupConfig activeConfig = new SmoothPickupConfig();
    public static void init() {
        activeConfig.loadSettings();
        logger.info("Smooth pickup initialized");
    }


    public static SmoothPickupConfig getActiveConfig() {
        return activeConfig;
    }
}
