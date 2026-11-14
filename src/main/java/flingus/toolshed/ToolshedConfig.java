package flingus.toolshed;

import eu.midnightdust.lib.config.MidnightConfig;

public class ToolshedConfig extends MidnightConfig {
    @Comment(centered = true) public static Comment lame;
    @Entry(name="Render Sword Skins") public static Boolean swordSkins = true;
    @Entry(name="Render Axe Skins") public static Boolean axeSkins = true;
    @Entry(name="Render Pickaxe Skins") public static Boolean pickaxeSkins = true;
    @Entry(name="Render Shovel Skins") public static Boolean shovelSkins = true;
    @Entry(name="Render Hoe Skins") public static Boolean hoeSkins = true;
    @Entry(name="Render Spear Skins") public static Boolean spearSkins = true;
    @Entry(name="Render Mace Skins") public static Boolean maceSkins = true;
    @Entry(name="Render Trident Skins") public static Boolean tridentSkins = true;
}
