package dev.mineland.smoothpickup.mixin;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.mineland.item_interactions_mod.GuiRendererHelper;
import dev.mineland.item_interactions_mod.itemcarriedalgs.AnimTemplate;
import dev.mineland.smoothpickup.modcompat.ItemInteractionsHotswap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.renderer.item.TrackingItemStackRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GuiRendererHelper.class)
public class ItemInteractionsMixin {


    @Inject(
            method = "renderItem(Lnet/minecraft/client/gui/render/state/GuiRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;ILnet/minecraft/client/Minecraft;III)V",
            at = @At(value = "INVOKE_ASSIGN", target = "Ldev/mineland/item_interactions_mod/itemcarriedalgs/AnimTemplate;makePose(IIIDDZLnet/minecraft/client/gui/GuiGraphics;)Lcom/mojang/blaze3d/vertex/PoseStack;"),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            require = 0
    )
    private static void modifyPose(GuiRenderState guiRenderState, ItemStack itemStack, Level level, LivingEntity livingEntity, int k, Minecraft minecraft, int initialX, int initialY, int initialZ, CallbackInfo ci, TrackingItemStackRenderState scratchItemStackRenderState, int x, int y, AnimTemplate anim, GuiGraphics guiGraphics, PoseStack newPose) {
        ItemInteractionsHotswap.modifyPose(
                guiRenderState, itemStack, level, livingEntity, k, minecraft, initialX, initialY, initialZ, ci, scratchItemStackRenderState, x, y, anim, guiGraphics, newPose
        );
    }
}
