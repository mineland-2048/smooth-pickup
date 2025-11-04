package dev.mineland.smoothpickup.neoforge;

import dev.mineland.smoothpickup.Loader;
import dev.mineland.smoothpickup.SmoothPickup;
import net.minecraft.client.Minecraft;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;

@Mod(SmoothPickup.MOD_ID)
public final class SmoothPickupNeoForge {
    public SmoothPickupNeoForge(ModContainer modContainer) {

        // Run our common setup.
        setupLoader();
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, new ConfigScreenNeoPoint(){});
        SmoothPickup.init();

    }


    private void setupLoader() {
        Loader.setLoader(new Loader.LoaderUtils() {
            @Override
            public boolean isModLoaded(String modId) {
                return ModList.get().isLoaded(modId);
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
