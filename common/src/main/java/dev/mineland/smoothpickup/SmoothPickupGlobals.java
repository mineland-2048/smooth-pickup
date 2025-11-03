package dev.mineland.smoothpickup;

import net.minecraft.client.Minecraft;

public class SmoothPickupGlobals {
    public static boolean carriedItemIsRendering = false;
    public static boolean tooltipIsRendering = false;
    public static double mouseX;
    public static double mouseY;

//    public static float renderX;
//    public static float renderY;

    public static float getRenderX() {
        return (float) (Minecraft.getInstance().mouseHandler.xpos() / Minecraft.getInstance().getWindow().getGuiScale());
    }

    public static float getRenderY() {
        return (float) (Minecraft.getInstance().mouseHandler.ypos() / Minecraft.getInstance().getWindow().getGuiScale());
    }
}
