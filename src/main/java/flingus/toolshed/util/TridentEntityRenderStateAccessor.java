package flingus.toolshed.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public interface TridentEntityRenderStateAccessor {
    void toolshed$setLook(Vec3d look);
    Vec3d toolshed$getLook();
    void toolshed$setTridentStack(ItemStack stack);
    ItemStack toolshed$getTridentStack();
}