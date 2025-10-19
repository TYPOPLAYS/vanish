package de.mcmdev.vanish.storage;

import java.util.UUID;

public interface Storage {

    boolean isVanished(UUID uuid);

    void setVanished(UUID uuid, boolean vanished);

    Integer getVanishLevelOverride(UUID uuid);

    void setVanishLevelOverride(UUID uuid, Integer level);

    boolean supportsOfflinePlayers();

}
