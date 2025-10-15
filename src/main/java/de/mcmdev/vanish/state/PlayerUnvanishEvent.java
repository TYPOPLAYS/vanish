package de.mcmdev.vanish.state;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public final class PlayerUnvanishEvent extends PlayerEvent {

  private static final HandlerList handlers = new HandlerList();

  PlayerUnvanishEvent(@NotNull Player player) {
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
