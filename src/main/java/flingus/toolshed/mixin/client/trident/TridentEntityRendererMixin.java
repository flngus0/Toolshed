package flingus.toolshed.mixin.client.trident;

import flingus.toolshed.Toolshed;
import flingus.toolshed.index.ModDataComponentTypes;
import flingus.toolshed.util.TridentEntityRenderStateAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.TridentEntityRenderer;
import net.minecraft.client.render.entity.state.TridentEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(TridentEntityRenderer.class)
public abstract class TridentEntityRendererMixin {
    // w combat amenities for most of that 2d trident stuff!!!

    @Inject(method = "updateRenderState(Lnet/minecraft/entity/projectile/TridentEntity;Lnet/minecraft/client/render/entity/state/TridentEntityRenderState;F)V", at = @At("HEAD"))
    public void toolshed$updateRenderState(TridentEntity tridentEntity, TridentEntityRenderState tridentEntityRenderState, float f, CallbackInfo ci) {
        if (tridentEntityRenderState instanceof TridentEntityRenderStateAccessor access) {
            access.toolshed$setLook(tridentEntity.getRotationVec(0));
            access.toolshed$setTridentStack(tridentEntity.getWeaponStack().copy());
        }
    }

    @Inject(method = "render(Lnet/minecraft/client/render/entity/state/TridentEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/render/state/CameraRenderState;)V", at = @At("HEAD"), cancellable = true)
    public void toolshed$renderAsItem(TridentEntityRenderState tridentEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState, CallbackInfo ci) {
        if (tridentEntityRenderState instanceof TridentEntityRenderStateAccessor access) {
            ItemStack trident = access.toolshed$getTridentStack();

            matrixStack.push();

            float multiplier = 0.3f;
            matrixStack.translate(access.toolshed$getLook().multiply(multiplier, multiplier, -multiplier));

            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(tridentEntityRenderState.yaw - 90.0f));
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(tridentEntityRenderState.pitch - 45.0f));

            matrixStack.scale(1.35f, 1.35f, 1);

            if (tridentEntityRenderState.enchanted) {
                trident.set(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true);
            }

            ItemRenderState itemRenderState = new ItemRenderState();

            MinecraftClient.getInstance().getItemModelManager().update(itemRenderState, trident, ItemDisplayContext.NONE, MinecraftClient.getInstance().world, null, 1);
            itemRenderState.render(matrixStack, orderedRenderCommandQueue, tridentEntityRenderState.light, OverlayTexture.DEFAULT_UV, tridentEntityRenderState.outlineColor);

            matrixStack.pop();
            ci.cancel();
        }
    }
}