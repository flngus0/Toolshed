package flingus.toolshed.client.datagen.provider;

import flingus.toolshed.index.ModTags;
import flingus.toolshed.index.ToolSkins;
import flingus.toolshed.registry.ToolSkin;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModTagProvider extends FabricTagProvider<ToolSkin> {
    public ModTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, ToolSkins.REGISTRY, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries) {
        getTagBuilder(ModTags.LONG_SWORDS)
                .add(ToolSkins.CORONATED.getValue())
                .add(ToolSkins.ROSETTE.getValue())
                .add(ToolSkins.VOIDSCI.getValue())
                .add(ToolSkins.HERO.getValue())
                .add(ToolSkins.LEGEND.getValue())
                .add(ToolSkins.WILDFLOWER.getValue())
                .add(ToolSkins.ECHO.getValue())
                .add(ToolSkins.AUREATE.getValue())
                .add(ToolSkins.MOTHER_NATURE.getValue());
    }
}
