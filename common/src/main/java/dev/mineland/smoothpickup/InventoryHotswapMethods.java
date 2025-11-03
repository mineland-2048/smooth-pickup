package dev.mineland.smoothpickup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static dev.mineland.smoothpickup.SmoothPickupGlobals.*;

public class InventoryHotswapMethods {
    private static final Font font = Minecraft.getInstance().font;
    public static void renderCarriedItemHead(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
//        logger.debug(String.format("%f, %f", mouseX, mouseY));

        guiGraphics.drawString(font, String.format("integerMouse:: %d, %d", i, j), 0, 0, 0xFFFFFFFF);
        guiGraphics.drawString(font, String.format("ActualMouse: %f, %f", mouseX, mouseY), 0, font.lineHeight, 0xFFFFFFFF);

        guiGraphics.pose().pushMatrix();
        guiGraphics.pose().translate((getRenderX()-i), (getRenderY()-j));
//        guiGraphics.pose().translate(-i, -j);
        carriedItemIsRendering = true;
    }
    public static void renderCarriedItemTail(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        guiGraphics.pose().popMatrix();
        carriedItemIsRendering = false;

    }

    public static void renderTooltipHead(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        tooltipIsRendering = true;

//        guiGraphics.pose().pushMatrix();
//        guiGraphics.pose().translate((renderX-i), (renderY-j));
    }

    public static void renderTooltipTail(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        tooltipIsRendering = false;
    }

    public static void renderGuiTooltipHead(GuiGraphics guiGraphics, Font font, List<ClientTooltipComponent> list, int i, int j, ClientTooltipPositioner clientTooltipPositioner, @Nullable ResourceLocation resourceLocation, CallbackInfo ci) {
//        clientTooltipPositioner.positionTooltip(i, j)

        guiGraphics.pose().pushMatrix();
        guiGraphics.pose().translate(getRenderX()-i, getRenderY()-j);

    }

    public static void renderGuiTooltipTail(GuiGraphics guiGraphics, Font font, List<ClientTooltipComponent> list, int i, int j, ClientTooltipPositioner clientTooltipPositioner, @Nullable ResourceLocation resourceLocation, CallbackInfo ci) {
        guiGraphics.pose().popMatrix();
    }
}
