/*
 * vanish
 * Copyright (C) 2026 MCMDEV
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
