package flingus.toolshed.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class CartoonyParticle extends BillboardParticle {
    private final float baseScale;

    protected CartoonyParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Sprite sprite, float baseScale) {
        super(world, x, y, z, velocityX, velocityY, velocityZ, sprite);
        this.velocityMultiplier = 0.9f;
        this.gravityStrength = 0.0f;
        this.baseScale = baseScale;
        this.scale = 0;
        this.maxAge = 40;
        this.setSprite(sprite);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    @Override
    public int getBrightness(float tint) {
        return 15728880;
    }

    @Override
    public void tick() {
        super.tick();

        int popTime = 2;
        if (this.age <= popTime) {
            float progress = (float)this.age / (float)popTime;
            this.scale = progress * (this.baseScale + 0.25f);
        }
        else if (this.age <= popTime + 2) {
            float shrinkProgress = (float)(this.age - popTime) / 2;
            this.scale = (this.baseScale + 0.25f) - (shrinkProgress * 0.25f);
        }
        else {
            this.scale = this.baseScale;
        }

        if (this.age > 15) {
            float fadeProgress = (float)(this.age - 15) / (float)(this.maxAge - 15);
            this.setAlpha(Math.max(0.0f, 1.0f - fadeProgress));
        }
    }

    @Override
    public BillboardParticle.RenderType getRenderType() {
        return RenderType.PARTICLE_ATLAS_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ, Random random) {
            float scale = 0.65f;

            Sprite sprite = this.spriteProvider.getSprite(random);

            return new CartoonyParticle(world, x, y, z, vX, vY, vZ, sprite, scale);
        }
    }
}