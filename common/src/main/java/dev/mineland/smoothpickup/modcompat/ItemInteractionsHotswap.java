package dev.mineland.smoothpickup.modcompat;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.mineland.item_interactions_mod.GlobalDirt;
import dev.mineland.item_interactions_mod.GuiRendererHelper;
import dev.mineland.item_interactions_mod.ItemInteractionsMod;
import dev.mineland.item_interactions_mod.itemcarriedalgs.AnimTemplate;
import dev.mineland.smoothpickup.GuiUtils;
import dev.mineland.smoothpickup.SmoothPickup;
import dev.mineland.smoothpickup.SmoothPickupGlobals;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.renderer.item.TrackingItemStackRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class ItemInteractionsHotswap {

//    I should probably make this native compat, but that would require an update to item interactions :waa
    public static void modifyPose(GuiRenderState guiRenderState, ItemStack itemStack, Level level, LivingEntity livingEntity, int k, Minecraft minecraft, int initialX, int initialY, int initialZ, CallbackInfo ci, TrackingItemStackRenderState scratchItemStackRenderState, int x, int y, AnimTemplate anim, GuiGraphics guiGraphics, PoseStack newPose) {
        if (!SmoothPickup.activeConfig.isFreeGridEnabled()) return;

        if (!SmoothPickupGlobals.carriedItemIsRendering) return;

        float transX = (GuiUtils.getRenderX() - initialX - 8) / 16;
        float transY = (GuiUtils.getRenderY() - initialY - 8) / 16;

        newPose.pushPose();
        newPose.translate(transX, -transY, 0);

    }
}
