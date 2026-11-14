package flingus.toolshed.client.datagen;

import flingus.toolshed.client.datagen.provider.*;
import flingus.toolshed.index.ToolSkins;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

public class ToolshedDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ToolSkinProvider::new);
        pack.addProvider(ToolSkinItemProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModTagProvider::new);
	}

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(ToolSkins.REGISTRY, ToolSkins::bootstrap);
    }
}
