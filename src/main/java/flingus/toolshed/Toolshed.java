package flingus.toolshed;

import eu.midnightdust.core.MidnightLib;
import eu.midnightdust.lib.config.MidnightConfig;
import flingus.toolshed.index.*;
import flingus.toolshed.registry.ToolSkin;
import moriyashiine.strawberrylib.api.SLib;
import moriyashiine.strawberrylib.api.event.AfterDamageIncludingDeathEvent;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.BlockEvents;
import net.fabricmc.fabric.api.event.player.ItemEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Toolshed implements ModInitializer {
	public static final String MOD_ID = "toolshed";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final SimpleParticleType POW_PARTICLE = FabricParticleTypes.simple();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Thurspoop world!");

        SLib.init(MOD_ID);

        ToolSkins.init();
        ModDataComponentTypes.init();
        ModItems.init();
        ModLootEvents.init();
        ModSounds.init();

        MidnightConfig.init(MOD_ID, ToolshedConfig.class);

        AfterDamageIncludingDeathEvent.EVENT.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
            if (!(source.getAttacker() instanceof LivingEntity attacker)) return;

            if (source.getSource() != attacker) return;

            ItemStack stack = attacker.getMainHandStack();
            RegistryEntry<ToolSkin> skinEntry = stack.get(ModDataComponentTypes.SKIN_COMPONENT);

            if (skinEntry == null || !skinEntry.matchesKey(ToolSkins.WONKY)) return;

            entity.getEntityWorld().playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.WHACK, entity.getSoundCategory(), 1.0f, 1.0f);

            if (entity.getEntityWorld() instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(Toolshed.POW_PARTICLE, entity.getX(), entity.getEyeY(), entity.getZ(), 1, 0.03f, 0.25f, 0.03f, 0.1f);
            }
        });

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack itemStack = player.getStackInHand(hand);
            BlockPos blockPos = hitResult.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);

            if (itemStack.contains(ModDataComponentTypes.SKIN_COMPONENT) && player.isSneaking() && blockState.isOf(Blocks.GRINDSTONE)) {
                if (!world.isClient()) {
                    itemStack.remove(ModDataComponentTypes.SKIN_COMPONENT);
                    world.playSound(null, blockPos, SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS);
                }

                player.swingHand(hand);
                return ActionResult.SUCCESS;
            }

            return ActionResult.PASS;
        });

        Registry.register(Registries.PARTICLE_TYPE, id("pow"), POW_PARTICLE);
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
