package de.mcmdev.vanish.api;

import org.bukkit.entity.Player;

import java.util.UUID;

public sealed interface VanishApi permits VanishImplementation {

    void vanish(UUID uuid);

    void unvanish(UUID uuid);

    boolean isVanished(UUID uuid);

    boolean canSee(Player viewer, Player target);

    int getSeeLevel(Player player);

    int getUseLevel(Player player);

    int getMaximumUseLevel(Player player);

    Integer getLevelOverride(UUID uuid);

    void setLevelOverride(UUID uuid, Integer value);

    boolean supportsOfflinePlayers();

    boolean supportsLevels();

}
