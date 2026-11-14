package flingus.toolshed.mixin.client.compat.backslot;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import flingus.toolshed.Toolshed;
import flingus.toolshed.index.ModDataComponentTypes;
import flingus.toolshed.index.ModTags;
import flingus.toolshed.index.ToolSkins;
import flingus.toolshed.registry.ToolSkin;
import flingus.toolshed.util.ToolshedUtil;
import net.medecoole.backslot.registry.BackSlotResourceCache;
import net.medecoole.backslot.registry.BackslotFeatureRenderer;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(BackslotFeatureRenderer.class)
public abstract class ThankYouMedeCooleMixin {
    @ModifyExpressionValue(
            method = "submit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/Identifier;tryParse(Ljava/lang/String;)Lnet/minecraft/util/Identifier;"
            )
    )
    private Identifier toolshed$submit(Identifier original, @Local ItemStack stack) {
        RegistryEntry<ToolSkin> skin = stack.get(ModDataComponentTypes.SKIN_COMPONENT);

        if (skin == null || stack.isIn(ItemTags.SPEARS)) return original;
        if (!skin.value().handheld().contains(stack.getItem())) return original;
        if (!ToolshedUtil.shouldRender(stack.getItem())) return original;

        return ToolshedUtil.isIn(skin.value().id(), ModTags.LONG_SWORDS) ? Toolshed.id("long_swords") : Toolshed.id("large_handheld");
    }
}