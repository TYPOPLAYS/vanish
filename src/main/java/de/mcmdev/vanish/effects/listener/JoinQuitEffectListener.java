package de.mcmdev.vanish.effects.listener;

import de.mcmdev.vanish.effects.event.EventDispatcher;
import de.mcmdev.vanish.storage.Storage;
import jakarta.inject.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class JoinQuitEffectListener implements Listener {

    private final Storage storage;
    private final EventDispatcher eventDispatcher;

    @Inject
    public JoinQuitEffectListener(final Storage storage, final EventDispatcher eventDispatcher) {
        this.storage = storage;
        this.eventDispatcher = eventDispatcher;
    }

    @EventHandler
    private void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (!storage.isVanished(player.getUniqueId())) {
            return;
        }

        eventDispatcher.applyEffects(player);
    }

    @EventHandler
    private void onQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        if (!storage.isVanished(player.getUniqueId())) {
            return;
        }

        // This is counterproductive since we want this to stay as long as possible.
        // eventDispatcher.clearEvents(player);
    }

}
