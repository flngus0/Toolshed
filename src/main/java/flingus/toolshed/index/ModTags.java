package flingus.toolshed.index;

import flingus.toolshed.Toolshed;
import flingus.toolshed.registry.ToolSkin;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {
    public static final TagKey<Item> MACES = TagKey.of(RegistryKeys.ITEM, Toolshed.id("maces"));
    public static final TagKey<Item> TRIDENTS = TagKey.of(RegistryKeys.ITEM, Toolshed.id("tridents"));
    public static final TagKey<ToolSkin> LONG_SWORDS = TagKey.of(ToolSkins.REGISTRY, Toolshed.id("long_swords"));
}
