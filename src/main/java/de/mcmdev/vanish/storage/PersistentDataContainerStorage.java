package de.mcmdev.vanish.storage;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

final class PersistentDataContainerStorage implements Storage {

  private final NamespacedKey key = new NamespacedKey("vanish", "vanished");

  @Override
  public boolean isVanished(UUID uuid) {
    OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
    if (!player.hasPlayedBefore()) return false;
    return player.getPersistentDataContainer().getOrDefault(key, PersistentDataType.BOOLEAN, false);
  }

  @Override
  public void setVanished(UUID uuid, boolean vanished) {
    Player player = Bukkit.getPlayer(uuid);
    if(player == null) {
      throw new IllegalArgumentException("Offline players are not supported by this storage.");
    }
    player.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, vanished);
  }

  @Override
  public boolean supportsOfflinePlayers() {
    return false;
  }
}
