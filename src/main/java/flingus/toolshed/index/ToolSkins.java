package flingus.toolshed.index;

import flingus.toolshed.Toolshed;
import flingus.toolshed.registry.ToolSkin;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.List;

public class ToolSkins {
    public static final RegistryKey<Registry<ToolSkin>> REGISTRY = RegistryKey.ofRegistry(Toolshed.id("tool_skin"));

    public static final RegistryKey<ToolSkin> CAPILLARY = of("capillary");
    public static final RegistryKey<ToolSkin> ESOTERIC = of("esoteric");
    public static final RegistryKey<ToolSkin> FRIGID = of("frigid");
    public static final RegistryKey<ToolSkin> HEAVIEST = of("heaviest");
    public static final RegistryKey<ToolSkin> WONKY = of("wonky");
    public static final RegistryKey<ToolSkin> ERROR = of("error");
    public static final RegistryKey<ToolSkin> TRIAL = of("trial");
    public static final RegistryKey<ToolSkin> OMINOUS = of("ominous");
    public static final RegistryKey<ToolSkin> CORONATED = of("coronated");
    public static final RegistryKey<ToolSkin> ROSETTE = of("rosette");
    public static final RegistryKey<ToolSkin> CANDY = of("candy");
    public static final RegistryKey<ToolSkin> YARRRR = of("yarrrr");
    public static final RegistryKey<ToolSkin> TUNG = of("tung");
    public static final RegistryKey<ToolSkin> VOIDSCI = of("voidsci");
    public static final RegistryKey<ToolSkin> WEATHERED = of("weathered");
    public static final RegistryKey<ToolSkin> VIRTUE = of("virtue");
    public static final RegistryKey<ToolSkin> TORRID = of("torrid");
    public static final RegistryKey<ToolSkin> GEODE = of("geode");
    public static final RegistryKey<ToolSkin> TWISTED_HATE = of("twisted_hate");
    public static final RegistryKey<ToolSkin> HERO = of("hero");
    public static final RegistryKey<ToolSkin> LEGEND = of("legend");
    public static final RegistryKey<ToolSkin> WIND = of("wind");
    public static final RegistryKey<ToolSkin> ECHO = of("echo");
    public static final RegistryKey<ToolSkin> BRONZE = of("bronze");
    public static final RegistryKey<ToolSkin> SANCTUARY = of("sanctuary");
    public static final RegistryKey<ToolSkin> WILDFLOWER = of("wildflower");
    public static final RegistryKey<ToolSkin> PALE = of("pale");
    public static final RegistryKey<ToolSkin> MOTHER_NATURE = of("mother_nature");
    public static final RegistryKey<ToolSkin> AUREATE = of("aureate");

    private static RegistryKey<ToolSkin> of(String name) {
        return RegistryKey.of(REGISTRY, Toolshed.id(name));
    }

    public static void bootstrap(Registerable<ToolSkin> registry) {
        register(registry, CAPILLARY, Toolshed.id("capillary"), Ingredient.ofItems(Items.ZOMBIE_HEAD, Items.PLAYER_HEAD), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.MACE, Items.NETHERITE_SPEAR
        ));

        register(registry, ESOTERIC, Toolshed.id("esoteric"), Ingredient.ofItems(Items.POPPED_CHORUS_FRUIT), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));

        register(registry, FRIGID, Toolshed.id("frigid"), Ingredient.ofItems(Items.PACKED_ICE), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));

        register(registry, HEAVIEST, Toolshed.id("heaviest"), Ingredient.ofItems(Items.MACE), List.of(
                Items.MACE
        ));

        register(registry, WONKY, Toolshed.id("wonky"), Ingredient.ofItems(Items.RED_DYE), List.of(
                Items.MACE
        ));

        register(registry, ERROR, Toolshed.id("error"), Ingredient.ofItems(Items.CHERRY_WOOD), List.of(
                Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE
        ));

        register(registry, TRIAL, Toolshed.id("trial"), Ingredient.ofItems(Items.TRIAL_KEY), List.of(
                Items.TRIDENT
        ));

        register(registry, OMINOUS, Toolshed.id("ominous"), Ingredient.ofItems(Items.OMINOUS_TRIAL_KEY), List.of(
                Items.TRIDENT
        ));

        register(registry, CORONATED, Toolshed.id("coronated"), Ingredient.ofItems(Items.GOLD_BLOCK), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.MACE, Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));

        register(registry, ROSETTE, Toolshed.id("rosette"), Ingredient.ofItems(Items.ROSE_BUSH), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.MACE, Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));

        register(registry, CANDY, Toolshed.id("candy"), Ingredient.ofItems(Items.SUGAR), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.NETHERITE_SPEAR
        ));

        register(registry, YARRRR, Toolshed.id("yarrrr"), Ingredient.ofItems(Items.DEEPSLATE_GOLD_ORE), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.MACE, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_SPEAR
        ));

        register(registry, TUNG, Toolshed.id("tung"), Ingredient.ofItems(Items.BREEZE_ROD), List.of(
                Items.MACE
        ));

        register(registry, VOIDSCI, Toolshed.id("voidsci"), Ingredient.ofItems(Items.AMETHYST_SHARD), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.MACE, Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));

        register(registry, WEATHERED, Toolshed.id("weathered"), Ingredient.ofItems(Items.COPPER_INGOT), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_SPEAR
        ));

        register(registry, VIRTUE, Toolshed.id("virtue"), Ingredient.ofItems(Items.IRON_CHAIN), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.MACE, Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));

        register(registry, TORRID, Toolshed.id("torrid"), Ingredient.ofItems(Items.BLAZE_ROD), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));

        register(registry, GEODE, Toolshed.id("geode"), Ingredient.ofItems(Items.AMETHYST_BLOCK), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.MACE, Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));

        register(registry, TWISTED_HATE, Toolshed.id("twisted_hate"), Ingredient.ofItems(Items.CRIMSON_ROOTS), List.of(
                Items.TRIDENT, Items.NETHERITE_SWORD
        ), List.of(Items.NETHERITE_SWORD));

        register(registry, HERO, Toolshed.id("hero"), Ingredient.ofItems(Items.GOLDEN_SWORD), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.MACE, Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));

        register(registry, LEGEND, Toolshed.id("legend"), Ingredient.ofItems(Items.MOSSY_STONE_BRICKS), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.MACE, Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));

        register(registry, WIND, Toolshed.id("wind"), Ingredient.ofItems(Items.BREEZE_ROD), List.of(
                Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.NETHERITE_SPEAR
        ));

        register(registry, ECHO, Toolshed.id("echo"), Ingredient.ofItems(Items.SCULK), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));

        register(registry, BRONZE, Toolshed.id("bronze"), Ingredient.ofItems(Items.COPPER_BLOCK), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE
        ));

        register(registry, SANCTUARY, Toolshed.id("sanctuary"), Ingredient.ofItems(Items.CRYING_OBSIDIAN), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE
        ));

        register(registry, WILDFLOWER, Toolshed.id("wildflower"), Ingredient.ofItems(Items.WILDFLOWERS), List.of(
                Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE
        ), List.of(Items.NETHERITE_SWORD));

        register(registry, PALE, Toolshed.id("pale"), Ingredient.ofItems(Items.RESIN_BRICK), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.NETHERITE_SPEAR
        ));

        register(registry, MOTHER_NATURE, Toolshed.id("mother_nature"), Ingredient.ofItems(Items.POPPY), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));

        register(registry, AUREATE, Toolshed.id("aureate"), Ingredient.ofItems(Items.GOLD_INGOT), List.of(
                Items.MACE, Items.TRIDENT, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
        ), List.of(
                Items.NETHERITE_SWORD, Items.NETHERITE_SPEAR
        ));
    }

    private static void register(Registerable<ToolSkin> registry, RegistryKey<ToolSkin> key, Identifier name, Ingredient material, List<Item> tools) {
        register(registry, key, name, material, tools, List.of());
    }

    private static void register(Registerable<ToolSkin> registry, RegistryKey<ToolSkin> key, Identifier name, Ingredient material, List<Item> tools, List<Item> handheld) {
        registry.register(key, new ToolSkin(name, material, tools, handheld));
    }

    public static void init() {
        DynamicRegistries.registerSynced(REGISTRY, ToolSkin.CODEC);
    }
}