package de.mcmdev.vanish.api;

import org.bukkit.entity.Player;

import java.util.UUID;

public sealed interface VanishApi permits VanishImplementation {

    void vanish(UUID uuid);

    default void vanish(final Player player) {
        vanish(player.getUniqueId());
    }

    void unvanish(UUID uuid);

    default void unvanish(final Player player) {
        unvanish(player.getUniqueId());
    }

    boolean isVanished(UUID uuid);

    default boolean isVanished(final Player player) {
        return isVanished(player.getUniqueId());
    }

    boolean canSee(Player viewer, Player target);

    int getSeeLevel(Player player);

    int getUseLevel(Player player);

    int getMaximumUseLevel(Player player);

    Integer getLevelOverride(UUID uuid);

    void setLevelOverride(UUID uuid, Integer value);

    boolean supportsOfflinePlayers();

    boolean supportsLevels();

}
