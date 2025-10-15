package de.mcmdev.vanish.api;

import de.mcmdev.vanish.state.State;
import de.mcmdev.vanish.storage.Storage;
import de.mcmdev.vanish.visibility.VisibilityCalculator;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

final class VanishImplementation implements VanishApi {

  private final Storage storage;
  private final State state;
  private final VisibilityCalculator visibilityCalculator;

  VanishImplementation(Storage storage, State state, VisibilityCalculator visibilityCalculator) {
    this.storage = storage;
    this.state = state;
    this.visibilityCalculator = visibilityCalculator;
  }

  @Override
  public void vanish(UUID uuid) {
    if (isVanished(uuid)) {
      return;
    }

    Player player = Bukkit.getPlayer(uuid);

    if (player == null && !storage.supportsOfflinePlayers()) {
      throw new IllegalArgumentException("Offline players are not supported by the storage implementation.");
    }

    if(player != null) {
      state.applyVanish(player);
    }

    storage.setVanished(uuid, true);
  }

  @Override
  public void unvanish(UUID uuid) {
    if (!isVanished(uuid)) {
      return;
    }

    Player player = Bukkit.getPlayer(uuid);

    if (player == null && !storage.supportsOfflinePlayers()) {
      throw new IllegalArgumentException("Offline players are not supported by the storage implementation.");
    }

    if(player != null) {
      state.unapplyVanish(player);
    }


    storage.setVanished(uuid, false);
  }

  @Override
  public boolean isVanished(UUID uuid) {
    Player player = Bukkit.getPlayer(uuid);

    if (player == null && !storage.supportsOfflinePlayers()) {
      throw new IllegalArgumentException("Offline players are not supported by the storage implementation.");
    }

    return storage.isVanished(uuid);
  }

  @Override
  public boolean supportsOfflinePlayers() {
    return storage.supportsOfflinePlayers();
  }

  @Override
  public boolean canSee(Player viewer, Player target) {
    return visibilityCalculator.canSee(viewer, target);
  }
}
