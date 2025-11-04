package dev.mineland.smoothpickup.fabric.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.mineland.smoothpickup.screens.SmoothPickupConfigScreen;

public class SmoothPickupModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return SmoothPickupConfigScreen::new;
    }
}
