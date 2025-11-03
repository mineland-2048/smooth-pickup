package dev.mineland.smoothpickup.mixin;

import dev.mineland.smoothpickup.InventoryHotswapMethods;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerMixin {


    @Inject(method = "renderTooltip", at = @At("HEAD"))
    private void renderTooltipHead(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        InventoryHotswapMethods.renderTooltipHead(guiGraphics, i, j, ci);
    }
    @Inject(method = "renderTooltip", at = @At("TAIL"))
    private void renderTooltipTail(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        InventoryHotswapMethods.renderTooltipTail(guiGraphics, i, j, ci);
    }

    @Inject(method = "renderCarriedItem", at = @At("HEAD"))
    private void renderCarriedItemHead(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        InventoryHotswapMethods.renderCarriedItemHead(guiGraphics, i, j, ci);
    }

    @Inject(method = "renderCarriedItem", at = @At("TAIL"))
    private void renderCarriedItemTail(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        InventoryHotswapMethods.renderCarriedItemTail(guiGraphics, i, j, ci);
    }




}
