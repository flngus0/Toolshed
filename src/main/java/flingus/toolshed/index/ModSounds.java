package flingus.toolshed.index;

import flingus.toolshed.Toolshed;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent WHACK = registerSound("skin.wonky.whack");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Toolshed.id(id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void init() {
        Toolshed.LOGGER.info("Registering " + Toolshed.MOD_ID + " Sounds");
    }
}
