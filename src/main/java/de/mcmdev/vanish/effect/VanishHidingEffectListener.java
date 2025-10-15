package de.mcmdev.vanish.effect;

import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.api.VanishApi;
import de.mcmdev.vanish.state.PlayerUnvanishEvent;
import de.mcmdev.vanish.state.PlayerVanishEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

final class VanishHidingEffectListener implements Listener {

  private final VanishPlugin plugin;
  private final VanishApi api;

  VanishHidingEffectListener(VanishPlugin plugin, VanishApi api) {
    this.plugin = plugin;
    this.api = api;
  }

  @EventHandler
  private void onVanish(PlayerVanishEvent event) {
    event.getPlayer().setVisibleByDefault(false);

    recalculateForTarget(event.getPlayer());
  }

  @EventHandler
  private void onUnvanish(PlayerUnvanishEvent event) {
    event.getPlayer().setVisibleByDefault(true);
  }

  @EventHandler
  private void onJoin(PlayerJoinEvent event) {
    recalculateForViewer(event.getPlayer());
  }

  public void recalculateForTarget(Player target) {
    for (Player viewer : Bukkit.getOnlinePlayers()) {
      if(api.canSee(viewer, target)) {
        viewer.showPlayer(plugin, target);
      } else {
        viewer.hidePlayer(plugin, target);
      }
    }
  }

  public void recalculateForViewer(Player viewer) {
    for (Player target : Bukkit.getOnlinePlayers()) {
      if(api.canSee(viewer, target)) {
        viewer.showPlayer(plugin, target);
      } else {
        viewer.hidePlayer(plugin, target);
      }
    }
  }

}
