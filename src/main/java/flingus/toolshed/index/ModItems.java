package flingus.toolshed.index;

import moriyashiine.strawberrylib.api.module.SLibRegistries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item TOOL_SKIN_SMITHING_TEMPLATE = SLibRegistries.registerItem("tool_skin_smithing_template", Item::new, new Item.Settings().rarity(Rarity.UNCOMMON));

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, TOOL_SKIN_SMITHING_TEMPLATE);
        });
    }
}