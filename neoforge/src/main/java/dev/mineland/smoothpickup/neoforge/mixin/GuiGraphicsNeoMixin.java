package dev.mineland.smoothpickup.neoforge.mixin;

import dev.mineland.smoothpickup.InventoryHotswapMethods;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiGraphics.class)
public class GuiGraphicsNeoMixin {
    @Inject(method = "renderTooltip(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/item/ItemStack;)V", at = @At("HEAD"))
    public void renderTooltipHeadNeo(Font arg, List<ClientTooltipComponent> list, int m, int n, ClientTooltipPositioner arg2, ResourceLocation arg3, ItemStack tooltipStack, CallbackInfo ci) {
        InventoryHotswapMethods.renderGuiTooltipHead((GuiGraphics) (Object) this, arg, list, m, n, arg2, arg3, ci);
    }

    @Inject(method = "renderTooltip(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/item/ItemStack;)V", at = @At("TAIL"))
    public void renderTooltipTailNeo(Font arg, List<ClientTooltipComponent> list, int m, int n, ClientTooltipPositioner arg2, ResourceLocation arg3, ItemStack tooltipStack, CallbackInfo ci) {
        InventoryHotswapMethods.renderGuiTooltipTail((GuiGraphics) (Object) this, arg, list, m, n, arg2, arg3, ci);
    }

}
