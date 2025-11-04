package dev.mineland.smoothpickup.fabric;

import dev.mineland.smoothpickup.Loader;
import dev.mineland.smoothpickup.SmoothPickup;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

public final class SmoothPickupFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        setupLoader();
        SmoothPickup.init();
    }

    private void setupLoader() {
        Loader.setLoader(new Loader.LoaderUtils() {
            @Override
            public boolean isModLoaded(String modId) {
                return FabricLoader.getInstance().isModLoaded(modId);
            }

            @Override
            public boolean isFabric() {
                return true;
            }

            @Override
            public boolean isNeoForge() {
                return false;
            }

            @Override
            public String getMinecraftVersion() {
                return Minecraft.getInstance().getLaunchedVersion();
            }
        });
    }
}
