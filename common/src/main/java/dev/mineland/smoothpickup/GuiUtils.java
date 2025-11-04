package dev.mineland.smoothpickup;

import net.minecraft.client.Minecraft;

public class GuiUtils {
    public static float getRenderX() {
        return (float) (Minecraft.getInstance().mouseHandler.xpos() / Minecraft.getInstance().getWindow().getGuiScale());
    }

    public static float getRenderY() {
        return (float) (Minecraft.getInstance().mouseHandler.ypos() / Minecraft.getInstance().getWindow().getGuiScale());
    }
}
