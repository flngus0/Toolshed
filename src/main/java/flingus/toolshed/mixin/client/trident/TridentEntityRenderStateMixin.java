package flingus.toolshed.mixin.client.trident;

import flingus.toolshed.util.TridentEntityRenderStateAccessor;
import net.minecraft.client.render.entity.state.TridentEntityRenderState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(TridentEntityRenderState.class)
public class TridentEntityRenderStateMixin implements TridentEntityRenderStateAccessor {
    @Unique
    private Vec3d toolshed$look = Vec3d.ZERO;

    @Override
    public void toolshed$setLook(Vec3d lookDirection) {
        toolshed$look = lookDirection;
    }

    @Override
    public Vec3d toolshed$getLook() {
        return toolshed$look;
    }

    @Unique
    private ItemStack toolshed$tridentStack = ItemStack.EMPTY;

    @Override
    public void toolshed$setTridentStack(ItemStack stack) {
        this.toolshed$tridentStack = stack;
    }

    @Override
    public ItemStack toolshed$getTridentStack() {
        return this.toolshed$tridentStack;
    }
}