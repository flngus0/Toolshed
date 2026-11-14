package flingus.toolshed.mixin;

import flingus.toolshed.index.ModDataComponentTypes;
import flingus.toolshed.index.ToolSkins;
import flingus.toolshed.registry.ToolSkin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(TridentEntity.class)
public abstract class TridentEntityMixin {
    @Unique
    private static final TrackedData<String> SKIN = DataTracker.registerData(TridentEntity.class, TrackedDataHandlerRegistry.STRING);

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void toolshed$initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(SKIN, "");
    }

    @Inject(method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;)V", at = @At("TAIL"))
    private void toolshed$setSkin(World world, LivingEntity owner, ItemStack stack, CallbackInfo ci) {
        RegistryEntry<ToolSkin> skinEntry = stack.get(ModDataComponentTypes.SKIN_COMPONENT);

        if (skinEntry != null) {
            Identifier skinId = skinEntry.getKey()
                    .map(RegistryKey::getValue)
                    .orElse(skinEntry.value().id());

            ((TridentEntity)(Object)this).getDataTracker().set(SKIN, skinId.toString());
        }
    }

    @Inject(method = "getWeaponStack", at = @At("RETURN"), cancellable = true)
    private void toolshed$applySkin(CallbackInfoReturnable<ItemStack> cir) {
        String skinStr = ((TridentEntity)(Object)this).getDataTracker().get(SKIN);

        if (!skinStr.isEmpty()) {
            TridentEntity trident = (TridentEntity) (Object) this;
            Identifier skinId = Identifier.tryParse(skinStr);

            if (skinId != null) {
                Optional<RegistryEntry.Reference<ToolSkin>> optionalToolSkinReference = trident.getEntityWorld()
                        .getRegistryManager()
                        .getOrThrow(ToolSkins.REGISTRY)
                        .getEntry(skinId);

                if (optionalToolSkinReference.isPresent()) {
                    ItemStack stack = cir.getReturnValue().copy();
                    stack.set(ModDataComponentTypes.SKIN_COMPONENT, optionalToolSkinReference.get());
                    cir.setReturnValue(stack);
                }
            }
        }
    }
}