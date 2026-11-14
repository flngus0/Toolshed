package flingus.toolshed.client.datagen.provider;

import flingus.toolshed.index.ToolSkins;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ToolSkinProvider extends FabricDynamicRegistryProvider {
    public ToolSkinProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(ToolSkins.REGISTRY));
    }

    @Override
    public String getName() {
        return "Tool skins";
    }
}