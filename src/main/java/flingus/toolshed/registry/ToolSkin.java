package flingus.toolshed.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;

public record ToolSkin(Identifier id, Ingredient material, List<Item> tools, List<Item> handheld) {
    public static final Codec<ToolSkin> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("id").forGetter(ToolSkin::id),
            Ingredient.CODEC.fieldOf("material").forGetter(ToolSkin::material),
            Registries.ITEM.getCodec().listOf().fieldOf("tools").forGetter(ToolSkin::tools),
            Registries.ITEM.getCodec().listOf().optionalFieldOf("handheld", List.of()).forGetter(ToolSkin::handheld)
    ).apply(instance, ToolSkin::new));
}