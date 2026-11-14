package flingus.toolshed.client;

import flingus.toolshed.Toolshed;
import flingus.toolshed.client.particle.CartoonyParticle;
import flingus.toolshed.index.ModDataComponentTypes;
import flingus.toolshed.index.ModItems;
import flingus.toolshed.registry.ToolSkin;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ToolshedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(Toolshed.POW_PARTICLE, CartoonyParticle.Factory::new);

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            RegistryEntry<ToolSkin> skinEntry = itemStack.get(ModDataComponentTypes.SKIN_COMPONENT);
            if (itemStack.contains(ModDataComponentTypes.SKIN_COMPONENT) && skinEntry != null) {
                Text skin = Text.literal(" (")
                        .append(ModDataComponentTypes.getTranslatedSkinName(skinEntry))
                        .append(")")
                        .formatted(Formatting.DARK_GRAY);
                list.set(0, Text.empty().append(list.getFirst()).append(skin));
            }
            if(itemStack.isOf(ModItems.TOOL_SKIN_SMITHING_TEMPLATE)) {
                list.add(1, Text.translatable("item.minecraft.smithing_template").formatted(Formatting.GRAY));
                list.add(2, Text.empty());
                list.add(3, Text.translatable("item.minecraft.smithing_template.applies_to").formatted(Formatting.GRAY));
                list.add(4, Text.literal(" ").append(Text.translatable("item.toolshed.tool_skin_smithing_template.tools.tridents").formatted(Formatting.BLUE)));
                list.add(5, Text.literal(" ").append(Text.translatable("item.toolshed.tool_skin_smithing_template.tools.maces").formatted(Formatting.BLUE)));
                list.add(6, Text.literal(" ").append(Text.translatable("item.toolshed.tool_skin_smithing_template.tools.netherite_tools").formatted(Formatting.BLUE)));
            }
        });
    }
}
