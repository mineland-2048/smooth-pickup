package dev.mineland.smoothpickup.mixin;

import dev.mineland.smoothpickup.SmoothPickupGlobals;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {

    @Inject(method = "onMove", at = @At("HEAD"))
    public void onMoveHead(long l, double d, double e, CallbackInfo ci) {
//        SmoothPickupGlobals.renderX
    }
}
