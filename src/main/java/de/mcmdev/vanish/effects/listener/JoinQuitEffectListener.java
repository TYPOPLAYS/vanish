package de.mcmdev.vanish.effects.listener;

import de.mcmdev.vanish.effects.event.EffectDispatcher;
import de.mcmdev.vanish.storage.Storage;
import jakarta.inject.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class JoinQuitEffectListener implements Listener {

    private final Storage storage;
    private final EffectDispatcher effectDispatcher;

    @Inject
    public JoinQuitEffectListener(final Storage storage, final EffectDispatcher effectDispatcher) {
        this.storage = storage;
        this.effectDispatcher = effectDispatcher;
    }

    @EventHandler
    private void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (!storage.isVanished(player.getUniqueId())) {
            return;
        }

        effectDispatcher.applyVanish(player);
    }

    @EventHandler
    private void onQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        if (!storage.isVanished(player.getUniqueId())) {
            return;
        }

        effectDispatcher.clearVanish(player);
    }

}
