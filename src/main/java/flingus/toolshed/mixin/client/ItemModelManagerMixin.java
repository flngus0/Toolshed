package flingus.toolshed.mixin.client;

import flingus.toolshed.Toolshed;
import flingus.toolshed.ToolshedConfig;
import flingus.toolshed.index.ModDataComponentTypes;
import flingus.toolshed.index.ModTags;
import flingus.toolshed.registry.ToolSkin;
import flingus.toolshed.util.ToolshedUtil;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.HeldItemContext;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemModelManager.class)
public class ItemModelManagerMixin {
    @ModifyVariable(method = "update", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private Identifier toolshed$changeSkinModel(Identifier identifier, ItemRenderState renderState, ItemStack stack, ItemDisplayContext displayContext, @Nullable World world, @Nullable HeldItemContext heldItemContext, int seed) {
        if (identifier != null) {
            RegistryEntry<ToolSkin> skin = stack.get(ModDataComponentTypes.SKIN_COMPONENT);

            if (skin != null && ToolshedUtil.shouldRender(stack.getItem())) {
                return Toolshed.id("tool_skin/" + skin.value().id().getPath() + "/" + Registries.ITEM.getId(stack.getItem()).getPath());
            }
        }

        return identifier;
    }
}