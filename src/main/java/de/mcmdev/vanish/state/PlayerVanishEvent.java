package de.mcmdev.vanish.state;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public final class PlayerVanishEvent extends PlayerEvent {

  private static final HandlerList handlers = new HandlerList();

  PlayerVanishEvent(@NotNull Player player) {
    super(player);
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}
