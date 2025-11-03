package dev.mineland.smoothpickup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.gui.screens.inventory.tooltip.MenuTooltipPositioner;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2ic;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static dev.mineland.smoothpickup.SmoothPickupGlobals.*;
import static dev.mineland.smoothpickup.SmoothPickup.getActiveConfig;

public class InventoryHotswapMethods {
    public static void renderCarriedItemHead(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        if (!getActiveConfig().isFreeGridEnabled()) return;

        guiGraphics.pose().pushMatrix();
        guiGraphics.pose().translate((getRenderX()-i), (getRenderY()-j));
        carriedItemIsRendering = true;
    }

    public static void renderCarriedItemTail(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        if (!getActiveConfig().isFreeGridEnabled()) return;
        guiGraphics.pose().popMatrix();
        carriedItemIsRendering = false;
    }

    public static void renderTooltipHead(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        tooltipIsRendering = true;
    }

    public static void renderTooltipTail(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        tooltipIsRendering = false;
    }

    public static void renderGuiTooltipHead(GuiGraphics guiGraphics, Font font, List<ClientTooltipComponent> list, int i, int j, ClientTooltipPositioner clientTooltipPositioner, @Nullable ResourceLocation resourceLocation, CallbackInfo ci) {
        if (!getActiveConfig().isFreeGridEnabled()) return;
//        guiGraphics.drawString(font, clientTooltipPositioner.getClass().toString(), 0, 0, 0xFFFFFFFF);
        float newPosX = getRenderX() - i;
        float newPosY = getRenderY() - j;
        if (clientTooltipPositioner.getClass() == MenuTooltipPositioner.class) {
            int minX = 0;
            int minY = list.size() == 1 ? -2 : 0;

            // get the sizes. Directly from the guiGraphics.
            for (ClientTooltipComponent clientTooltipComponent : list) {
                int m = clientTooltipComponent.getWidth(font);
                if (m > minX) {
                    minX = m;
                }

                minY += clientTooltipComponent.getHeight(font);
            }

            Vector2ic positionVector2i = clientTooltipPositioner.positionTooltip(
                    Minecraft.getInstance().getWindow().getGuiScaledWidth(),
                    Minecraft.getInstance().getWindow().getGuiScaledHeight(),
                    i, j,
                    minX, minY);

            Vector2ic nextPositionVector2i = clientTooltipPositioner.positionTooltip(
                    Minecraft.getInstance().getWindow().getGuiScaledWidth(),
                    Minecraft.getInstance().getWindow().getGuiScaledHeight(),
                    i, j+1,
                    minX, minY);

            newPosY *= (nextPositionVector2i.y() - positionVector2i.y());

        }

        guiGraphics.pose().pushMatrix();
        guiGraphics.pose().translate(newPosX, newPosY);

    }

    public static void renderGuiTooltipTail(GuiGraphics guiGraphics, Font font, List<ClientTooltipComponent> list, int i, int j, ClientTooltipPositioner clientTooltipPositioner, @Nullable ResourceLocation resourceLocation, CallbackInfo ci) {
        if (!getActiveConfig().isFreeGridEnabled()) return;
        guiGraphics.pose().popMatrix();
    }
}
