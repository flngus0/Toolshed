package flingus.toolshed.util;

import flingus.toolshed.ToolshedConfig;
import flingus.toolshed.index.ModTags;
import flingus.toolshed.index.ToolSkins;
import flingus.toolshed.registry.ToolSkin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ToolshedUtil {
    public static boolean shouldRender(Item item) {
        if(item.getDefaultStack().isIn(ItemTags.SWORDS)) return ToolshedConfig.swordSkins;
        if(item.getDefaultStack().isIn(ItemTags.AXES)) return ToolshedConfig.axeSkins;
        if(item.getDefaultStack().isIn(ItemTags.PICKAXES)) return ToolshedConfig.pickaxeSkins;
        if(item.getDefaultStack().isIn(ItemTags.SHOVELS)) return ToolshedConfig.shovelSkins;
        if(item.getDefaultStack().isIn(ItemTags.HOES)) return ToolshedConfig.hoeSkins;
        if(item.getDefaultStack().isIn(ItemTags.SPEARS)) return ToolshedConfig.spearSkins;
        if(item.getDefaultStack().isIn(ModTags.MACES)) return ToolshedConfig.maceSkins;
        if(item.getDefaultStack().isIn(ModTags.TRIDENTS)) return ToolshedConfig.tridentSkins;

        return true;
    }

    public static boolean hasHandheld(Identifier id, Item item) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null || id == null) return false;

        Registry<ToolSkin> registry = client.world.getRegistryManager().getOrThrow(ToolSkins.REGISTRY);

        return registry.getEntry(id)
                .map(RegistryEntry.Reference::value)
                .map(skin -> skin.handheld().contains(item))
                .orElse(false);
    }

    public static boolean isIn(Identifier id, TagKey<ToolSkin> tag) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null || id == null) return false;

        Registry<ToolSkin> registry = client.world.getRegistryManager().getOrThrow(ToolSkins.REGISTRY);

        return registry.getEntry(id)
                .map(entry -> entry.isIn(tag))
                .orElse(false);
    }
}
