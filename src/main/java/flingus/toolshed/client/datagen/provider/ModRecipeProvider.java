package flingus.toolshed.client.datagen.provider;

import flingus.toolshed.Toolshed;
import flingus.toolshed.index.ModDataComponentTypes;
import flingus.toolshed.index.ModItems;
import flingus.toolshed.index.ToolSkins;
import flingus.toolshed.registry.ToolSkin;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.component.ComponentChanges;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.SmithingTransformRecipe;
import net.minecraft.recipe.TransmuteRecipeResult;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                offerSmithingTemplateCopyingRecipe(ModItems.TOOL_SKIN_SMITHING_TEMPLATE, Items.TUFF);

                RegistryWrapper<ToolSkin> skinRegistry = registries.getOrThrow(ToolSkins.REGISTRY);

                for (RegistryEntry.Reference<ToolSkin> entry : skinRegistry.streamEntries().toList()) {
                    ToolSkin skin = entry.value();
                    String skinName = skin.id().getPath();

                    for (Item toolItem : skin.tools()) {
                        String toolItemName = Registries.ITEM.getId(toolItem).getPath();

                        RegistryKey<Recipe<?>> recipeKey = RegistryKey.of(RegistryKeys.RECIPE, Toolshed.id("tool_skin/" + skinName + "_" + toolItemName));

                        ComponentChanges changes = ComponentChanges.builder().add(ModDataComponentTypes.SKIN_COMPONENT, entry).build();

                        TransmuteRecipeResult result = new TransmuteRecipeResult(Registries.ITEM.getEntry(toolItem), 1, changes);

                        SmithingTransformRecipe recipe = new SmithingTransformRecipe(Optional.of(Ingredient.ofItems(ModItems.TOOL_SKIN_SMITHING_TEMPLATE)), Ingredient.ofItems(toolItem), Optional.of(skin.material()), result);

                        Advancement.Builder advancementBuilder = exporter.getAdvancementBuilder().criterion("has_recipe", RecipeUnlockedCriterion.create(recipeKey)).rewards(AdvancementRewards.Builder.recipe(recipeKey)).criteriaMerger(AdvancementRequirements.CriterionMerger.OR);

                        advancementBuilder.criterion(hasItem(ModItems.TOOL_SKIN_SMITHING_TEMPLATE), conditionsFromItem(ModItems.TOOL_SKIN_SMITHING_TEMPLATE));

                        exporter.accept(recipeKey, recipe, advancementBuilder.build(recipeKey.getValue().withPrefixedPath("recipes/misc/")));
                    }
                }
            }
        };
    }

    @Override
    public String getName() {
        return "Toolshed Recipes";
    }
}