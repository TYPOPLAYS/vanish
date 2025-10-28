package de.mcmdev.vanish.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public final class VanishExitEvent extends PlayerEvent {

  private static final HandlerList handlers = new HandlerList();

  VanishExitEvent(@NotNull final Player player) {
    super(player);
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return handlers;
  }
}