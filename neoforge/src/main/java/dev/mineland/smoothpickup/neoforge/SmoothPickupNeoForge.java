package dev.mineland.smoothpickup.neoforge;

import dev.mineland.smoothpickup.SmoothPickup;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(SmoothPickup.MOD_ID)
public final class SmoothPickupNeoForge {
    public SmoothPickupNeoForge(ModContainer modContainer) {
        // Run our common setup.
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, new ConfigScreenNeoPoint(){});
        SmoothPickup.init();

    }



}
