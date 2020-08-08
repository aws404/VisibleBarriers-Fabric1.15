package com.aws404.visiblebarriers.mixin;

import com.aws404.visiblebarriers.VisibleBarriers;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStandEntityRenderer.class)
public abstract class ArmorStandEntityRendererMixin {

    private static final Identifier MARKER_SKIN = new Identifier("textures/entity/armorstand/marker.png");
    private static final Identifier INVISIBLE_SKIN = new Identifier("textures/entity/armorstand/invisible.png");

    @Inject(method = "getRenderLayer", at = @At("HEAD"), cancellable = true)
    private void getRenderLayer(ArmorStandEntity armorStandEntity, boolean bl, boolean bl2, CallbackInfoReturnable<RenderLayer> i) {
        if (VisibleBarriers.isShowingStands()) {
            if (armorStandEntity.isMarker())
                i.setReturnValue(RenderLayer.getCutoutNoCull(MARKER_SKIN, false));
            else if (armorStandEntity.isInvisible())
                i.setReturnValue(RenderLayer.getCutoutNoCull(INVISIBLE_SKIN, false));
        }
    }
}