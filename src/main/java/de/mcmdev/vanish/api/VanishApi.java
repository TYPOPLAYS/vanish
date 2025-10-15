package de.mcmdev.vanish.api;

import java.util.UUID;
import org.bukkit.entity.Player;

public sealed interface VanishApi permits VanishImplementation {

  void vanish(UUID uuid);

  void unvanish(UUID uuid);

  boolean isVanished(UUID uuid);

  boolean canSee(Player viewer, Player target);

  boolean supportsOfflinePlayers();

}
