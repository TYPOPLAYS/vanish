package de.mcmdev.vanish.listeners;

import de.mcmdev.vanish.api.VanishApi;
import de.mcmdev.vanish.config.Config;
import de.mcmdev.vanish.events.VanishApplyEffectsEvent;
import de.mcmdev.vanish.events.VanishClearEffectsEvent;
import jakarta.inject.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public final class HookListener implements Listener {

    private final VanishApi api;
    private final Config config;

    @Inject
    public HookListener(final VanishApi api, final Config config) {
        this.api = api;
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

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onPlayerInteractEntity(final PlayerInteractEntityEvent event) {
        if (!api.isVanished(event.getPlayer())) {
            return;
        }

        if (event.getRightClicked() instanceof final Player target) {
            config.playerClickHook().execute(event.getPlayer(), target);
        }
    }
}
