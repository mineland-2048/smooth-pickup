package dev.mineland.smoothpickup.mixin;

import dev.mineland.smoothpickup.InventoryHotswapMethods;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {
    @Inject(method = "renderTooltip", at = @At("HEAD"))
    public void renderTooltipHead(Font font, List<ClientTooltipComponent> list, int i, int j, ClientTooltipPositioner clientTooltipPositioner, @Nullable ResourceLocation resourceLocation, CallbackInfo ci) {
        InventoryHotswapMethods.renderGuiTooltipHead((GuiGraphics) (Object) this, font, list, i, j, clientTooltipPositioner, resourceLocation, ci);
    }

    @Inject(method = "renderTooltip", at = @At("TAIL"))
    public void renderTooltipTail(Font font, List<ClientTooltipComponent> list, int i, int j, ClientTooltipPositioner clientTooltipPositioner, @Nullable ResourceLocation resourceLocation, CallbackInfo ci) {
        InventoryHotswapMethods.renderGuiTooltipTail((GuiGraphics) (Object) this, font, list, i, j, clientTooltipPositioner, resourceLocation, ci);
    }
}
