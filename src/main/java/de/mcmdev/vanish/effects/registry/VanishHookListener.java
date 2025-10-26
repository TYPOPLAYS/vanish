package de.mcmdev.vanish.effects.registry;

import de.mcmdev.vanish.config.Config;
import de.mcmdev.vanish.effects.event.VanishApplyEffectsEvent;
import de.mcmdev.vanish.effects.event.VanishClearEffectsEvent;
import jakarta.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VanishHookListener implements Listener {

    private final Config config;

    @Inject
    public VanishHookListener(final Config config) {
        this.config = config;
    }

    @EventHandler
    private void onVanish(final VanishApplyEffectsEvent event) {
        config.vanishHooks().execute(event.getPlayer());
    }

    @EventHandler
    private void onUnvanish(final VanishClearEffectsEvent event) {
        config.unvanishHooks().execute(event.getPlayer());
    }
}
