package flingus.toolshed.client.datagen.provider;

import flingus.toolshed.Toolshed;
import flingus.toolshed.index.ModItems;
import flingus.toolshed.index.ToolSkins;
import flingus.toolshed.registry.ToolSkin;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModModelProvider extends FabricModelProvider {
    public static final Model TRIDENT_HANDHELD = openBundle("template_trident_handheld", "trident_handheld", TextureKey.LAYER0);
    public static final Model TRIDENT_THROWING = openBundle("template_trident_throwing", "trident_throwing", TextureKey.LAYER0);

    private static Model openBundle(String parent, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Toolshed.id("item/" + parent)), Optional.of(variant), requiredTextureKeys);
    }

    private final CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture;

    public ModModelProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output);
        this.registriesFuture = registriesFuture;
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        RegistryWrapper.WrapperLookup registries = registriesFuture.join();
        RegistryWrapper<ToolSkin> skins = registries.getOrThrow(ToolSkins.REGISTRY);

        skins.streamEntries().forEach((RegistryEntry.Reference<ToolSkin> entry) -> registerToolSkin(entry.value(), itemModelGenerator));

        itemModelGenerator.register(ModItems.TOOL_SKIN_SMITHING_TEMPLATE, Models.GENERATED);
    }

    public final void registerToolSkin(ToolSkin skin, ItemModelGenerator generator) {
        Identifier id = skin.id();
        String name = id.getPath();

        if (name.equals("heaviest")) return;

        for (Item tool : skin.tools()) {
            String toolName = Registries.ITEM.getId(tool).getPath();
            Identifier texture = Toolshed.id("item/tool_skin/" + name + "/" + toolName);

            Model model = tool.equals(Items.MACE) ? Models.HANDHELD_MACE : Models.HANDHELD;

            model.upload(texture, TextureMap.layer0(texture), generator.modelCollector);

            if (tool.equals(Items.TRIDENT)) {
                Identifier tridentTexture = Toolshed.id("item/tool_skin/" + name + "/" + toolName + "_handheld");

                TRIDENT_HANDHELD.upload(Toolshed.id("item/tool_skin/" + name + "/" + toolName + "_handheld"), TextureMap.layer0(tridentTexture), generator.modelCollector);
                TRIDENT_THROWING.upload(Toolshed.id("item/tool_skin/" + name + "/" + toolName + "_throwing"), TextureMap.layer0(tridentTexture), generator.modelCollector);
            }
            else if (tool.equals(Items.NETHERITE_SPEAR)) {
                Identifier handheldId = Toolshed.id("item/tool_skin/" + name + "/" + toolName + "_handheld");
                Identifier handheldTexture = Toolshed.id("item/tool_skin/" + name + "/" + toolName + "_handheld");

                Model spearHandheld = new Model(Optional.of(Identifier.ofVanilla("item/spear_in_hand")), Optional.of("spear_in_hand"), TextureKey.LAYER0);

                spearHandheld.upload(handheldId, TextureMap.layer0(handheldTexture), generator.modelCollector);
            }
            else if (skin.handheld().contains(tool)) {
                Identifier handheldId = Toolshed.id("item/tool_skin/" + name + "/" + toolName + "_handheld");
                Identifier handheldTexture = Toolshed.id("item/tool_skin/" + name + "/" + toolName + "_handheld");

                Model handheldModel = tool.equals(Items.MACE) ? openBundle("template_large_mace", "large_mace", TextureKey.LAYER0) : openBundle("template_large_handheld", "large_handheld", TextureKey.LAYER0);

                handheldModel.upload(handheldId, TextureMap.layer0(handheldTexture), generator.modelCollector);
            }
        }
    }
}