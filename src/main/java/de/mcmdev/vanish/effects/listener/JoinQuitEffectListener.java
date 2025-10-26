package de.mcmdev.vanish.effects.listener;

import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.effects.event.EventDispatcher;
import de.mcmdev.vanish.storage.Storage;
import jakarta.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class JoinQuitEffectListener implements Listener {

    private final VanishPlugin plugin;
    private final Storage storage;
    private final EventDispatcher eventDispatcher;

    @Inject
    public JoinQuitEffectListener(final VanishPlugin plugin, final Storage storage, final EventDispatcher eventDispatcher) {
        this.plugin = plugin;
        this.storage = storage;
        this.eventDispatcher = eventDispatcher;
    }

    public void register() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
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
