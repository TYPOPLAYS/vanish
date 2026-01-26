package de.mcmdev.vanish.listeners;

import de.mcmdev.vanish.api.VanishApi;
import de.mcmdev.vanish.config.Config;
import de.mcmdev.vanish.events.VanishEnterEvent;
import de.mcmdev.vanish.events.VanishExitEvent;
import jakarta.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class FakeMessageListener implements Listener {

    private final VanishApi vanishApi;
    private final Config config;

    @Inject
    public FakeMessageListener(final VanishApi vanishApi, final Config config) {
        this.vanishApi = vanishApi;
        this.config = config;
    }

    @EventHandler
    private void onJoin(final PlayerJoinEvent event) {
        if (vanishApi.isVanished(event.getPlayer())) {
            event.joinMessage(null);

            Bukkit.getOnlinePlayers().stream()
                    .filter(viewer -> vanishApi.canSee(viewer, event.getPlayer()))
                    .forEach(player -> config.messages().notifyJoin().send(player, event.getPlayer()));
        }
    }

    @EventHandler
    private void onQuit(final PlayerQuitEvent event) {
        if (vanishApi.isVanished(event.getPlayer())) {
            event.quitMessage(null);

            Bukkit.getOnlinePlayers().stream()
                    .filter(viewer -> vanishApi.canSee(viewer, event.getPlayer()))
                    .forEach(player -> config.messages().notifyQuit().send(player, event.getPlayer()));
        }
    }

    @EventHandler
    private void onEnter(final VanishEnterEvent event) {
        final Player target = event.getPlayer();

        for (final Player viewer : Bukkit.getOnlinePlayers()) {
            if (!vanishApi.canSee(viewer, target)) {
                config.messages().fakeQuit().send(viewer, target);
            }
        }
    }

    @EventHandler
    private void onExit(final VanishExitEvent event) {
        final Player target = event.getPlayer();

        for (final Player viewer : Bukkit.getOnlinePlayers()) {
            if (!vanishApi.canSee(viewer, target)) {
                config.messages().fakeJoin().send(viewer, target);
            }
        }
    }

}
