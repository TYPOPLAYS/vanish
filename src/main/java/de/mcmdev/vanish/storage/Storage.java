package de.mcmdev.vanish.storage;

import java.util.UUID;

public interface Storage {

  boolean isVanished(UUID uuid);

  void setVanished(UUID uuid, boolean vanished);

  boolean supportsOfflinePlayers();

}
