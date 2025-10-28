package de.mcmdev.vanish.listeners.effects;

import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.api.VanishApi;
import de.mcmdev.vanish.events.VanishApplyEffectsEvent;
import de.mcmdev.vanish.events.VanishClearEffectsEvent;
import de.mcmdev.vanish.events.VanishRecalculateEvent;
import jakarta.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class HidingEffectListener implements Listener {

    private final VanishPlugin plugin;
    private final VanishApi api;

    @Inject
    public HidingEffectListener(final VanishPlugin plugin, final VanishApi api) {
        this.plugin = plugin;
        this.api = api;
    }

    @EventHandler
    private void onVanish(final VanishApplyEffectsEvent event) {
        event.getPlayer().setVisibleByDefault(false);

        recalculateForTarget(event.getPlayer());
    }

    @EventHandler
    private void onUnvanish(final VanishClearEffectsEvent event) {
        event.getPlayer().setVisibleByDefault(true);
    }

    @EventHandler
    private void onRecalculate(final VanishRecalculateEvent event) {
        recalculateForTarget(event.getPlayer());
    }

    @EventHandler
    private void onJoin(final PlayerJoinEvent event) {
        recalculateForViewer(event.getPlayer());
    }

    public void recalculateForTarget(final Player target) {
        for (final Player viewer : Bukkit.getOnlinePlayers()) {
            if (api.canSee(viewer, target)) {
                viewer.showPlayer(plugin, target);
            } else if (api.isVanished(target)) {
                viewer.hidePlayer(plugin, target);
            }
        }
    }

    public void recalculateForViewer(final Player viewer) {
        for (final Player target : Bukkit.getOnlinePlayers()) {
            if (api.canSee(viewer, target)) {
                viewer.showPlayer(plugin, target);
            } else if (api.isVanished(target)) {
                viewer.hidePlayer(plugin, target);
            }
        }
    }

}
