package dev.mineland.smoothpickup.neoforge;

import dev.mineland.smoothpickup.screens.SmoothPickupConfigScreen;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.jetbrains.annotations.NotNull;

public class ConfigScreenNeoPoint implements IConfigScreenFactory {

    public ConfigScreenNeoPoint() {}

    @Override
    public @NotNull Screen createScreen(@NotNull ModContainer modContainer, @NotNull Screen parent) {
        return new SmoothPickupConfigScreen(parent);
    }
}
