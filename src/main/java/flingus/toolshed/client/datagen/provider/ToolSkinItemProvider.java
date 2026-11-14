
package flingus.toolshed.client.datagen.provider;

import flingus.toolshed.Toolshed;
import flingus.toolshed.index.ToolSkins;
import flingus.toolshed.registry.ToolSkin;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ToolSkinItemProvider implements DataProvider {
    private final FabricDataOutput output;
    private final CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture;

    public ToolSkinItemProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        this.output = output;
        this.registriesFuture = registriesFuture;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        RegistryWrapper.WrapperLookup registries = registriesFuture.join();
        RegistryWrapper<ToolSkin> skins = registries.getOrThrow(ToolSkins.REGISTRY);

        DataOutput.PathResolver resolver = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "items");
        List<CompletableFuture<?>> futures = new ArrayList<>();

        skins.streamEntries().forEach(entry -> {
            ToolSkin skin = entry.value();
            String skinName = skin.id().getPath();

            if (skinName.equals("heaviest")) return;

            for (Item tool : skin.tools()) {
                String toolName = Registries.ITEM.getId(tool).getPath();

                Identifier itemId = Toolshed.id("tool_skin/" + skinName + "/" + toolName);

                JsonObject item = createItem(skin, tool);

                futures.add(DataProvider.writeToPath(
                        writer,
                        item,
                        resolver.resolveJson(itemId)
                ));
            }
        });

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private JsonObject createItem(ToolSkin skin, Item tool) {
        String skinName = skin.id().getPath();
        String toolName = Registries.ITEM.getId(tool).getPath();

        Identifier model = Toolshed.id("item/tool_skin/" + skinName + "/" + toolName);
        Identifier handheldModel = Toolshed.id("item/tool_skin/" + skinName + "/" + toolName + "_handheld");

        JsonObject root = new JsonObject();

        if (tool.equals(Items.TRIDENT)) {
            Identifier throwingModel = Toolshed.id("item/tool_skin/" + skinName + "/" + toolName + "_throwing");

            root.add("model", createTridentDisplayContextModel(model, handheldModel, throwingModel));
            root.addProperty("swap_animation_scale", 1.95);
            return root;
        }

        boolean hasHandheld = skin.handheld().contains(tool) || tool.equals(Items.NETHERITE_SPEAR);

        if (!hasHandheld) {
            root.add("model", createBasic(model));
            return root;
        }

        root.add("model", createHandheld(model, handheldModel));
        root.addProperty("swap_animation_scale", 1);

        return root;
    }

    private JsonObject createHandheld(Identifier modelId, Identifier handheldId) {
        JsonObject select = new JsonObject();
        select.addProperty("type", "minecraft:select");
        select.addProperty("property", "minecraft:display_context");

        JsonArray cases = new JsonArray();
        JsonObject displayCase = new JsonObject();

        JsonArray contexts = new JsonArray();
        contexts.add("gui");
        contexts.add("ground");
        contexts.add("fixed");
        contexts.add("on_shelf");
        contexts.add("head");

        displayCase.add("when", contexts);
        displayCase.add("model", createBasic(modelId));

        cases.add(displayCase);

        select.add("cases", cases);
        select.add("fallback", createBasic(handheldId));

        return select;
    }

    private JsonObject createTridentDisplayContextModel(Identifier modelId, Identifier handheldId, Identifier throwingId) {
        JsonObject select = new JsonObject();
        select.addProperty("type", "minecraft:select");
        select.addProperty("property", "minecraft:display_context");

        JsonArray cases = new JsonArray();
        JsonObject displayCase = new JsonObject();

        JsonArray contexts = new JsonArray();
        contexts.add("gui");
        contexts.add("ground");
        contexts.add("fixed");
        contexts.add("on_shelf");

        displayCase.add("when", contexts);
        displayCase.add("model", createBasic(modelId));

        cases.add(displayCase);

        JsonObject condition = new JsonObject();
        condition.addProperty("type", "minecraft:condition");
        condition.addProperty("property", "minecraft:using_item");
        condition.add("on_true", createBasic(throwingId));
        condition.add("on_false", createBasic(handheldId));

        select.add("cases", cases);
        select.add("fallback", condition);

        return select;
    }

    private JsonObject createBasic(Identifier modelId) {
        JsonObject model = new JsonObject();
        model.addProperty("type", "minecraft:model");
        model.addProperty("model", modelId.toString());
        return model;
    }

    @Override
    public String getName() {
        return "Tool skin items";
    }
}
