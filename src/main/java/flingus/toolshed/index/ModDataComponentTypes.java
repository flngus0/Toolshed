package flingus.toolshed.index;

import flingus.toolshed.Toolshed;
import flingus.toolshed.registry.ToolSkin;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryFixedCodec;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModDataComponentTypes {
    public static final ComponentType<RegistryEntry<ToolSkin>> SKIN_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Toolshed.id("skin"),
            ComponentType.<RegistryEntry<ToolSkin>>builder().codec(RegistryFixedCodec.of(ToolSkins.REGISTRY)).packetCodec(PacketCodecs.registryEntry(ToolSkins.REGISTRY)).build()
    );

    public static ToolSkin skin(Identifier id, RegistryWrapper.WrapperLookup registries) {
        return registries.getOrThrow(ToolSkins.REGISTRY).getOrThrow(RegistryKey.of(ToolSkins.REGISTRY, id)).value();
    }

    public static Text getTranslatedSkinName(RegistryEntry<ToolSkin> skin) {
        return Text.translatable(skin.value().id().toTranslationKey("skin"));
    }

    public static void init() {}
}