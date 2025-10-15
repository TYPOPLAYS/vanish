package de.mcmdev.vanish.state;

import de.mcmdev.vanish.storage.Storage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

final class JoinQuitStateChangingListener implements Listener {

  private final Storage storage;
  private final State state;

  JoinQuitStateChangingListener(Storage storage, State state) {
    this.storage = storage;
    this.state = state;
  }

  @EventHandler
  private void onJoin(PlayerJoinEvent event)  {
    Player player = event.getPlayer();

    if (!storage.isVanished(player.getUniqueId())) {
      return;
    }

    if (!player.hasPermission("vanish")) {
      storage.setVanished(player.getUniqueId(), false);
      return;
    }

    state.applyVanish(player);
  }

}
