package dev.mineland.smoothpickup.neoforge;

import dev.mineland.smoothpickup.SmoothPickup;
import net.neoforged.fml.common.Mod;

@Mod(SmoothPickup.MOD_ID)
public final class SmoothPickupNeoForge {
    public SmoothPickupNeoForge() {
        // Run our common setup.
        SmoothPickup.init();
    }
}
