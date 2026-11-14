package flingus.toolshed.index;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKeys;

public class ModLootEvents {
    public static void init() {
        LootTableEvents.MODIFY_DROPS.register((entry, context, drops) -> {
            if (entry.matchesKey(LootTables.TRIAL_CHAMBERS_REWARD_UNIQUE_CHEST)) {
                drops.add(ModItems.TOOL_SKIN_SMITHING_TEMPLATE.getDefaultStack());
            }
        });
    }
}
