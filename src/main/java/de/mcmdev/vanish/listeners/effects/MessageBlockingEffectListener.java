package de.mcmdev.vanish.listeners.effects;

import de.mcmdev.vanish.api.VanishApi;
import jakarta.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public final class MessageBlockingEffectListener implements Listener {

    private final VanishApi api;

    @Inject
    public MessageBlockingEffectListener(final VanishApi api) {
        this.api = api;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onPlayerAdvancementDone(final PlayerAdvancementDoneEvent event) {
        if(api.isVanished(event.getPlayer())) {
            event.message(null);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onPlayerDeath(final PlayerDeathEvent event) {
        if(api.isVanished(event.getPlayer())) {
            event.deathMessage(null);
        }
    }

}
