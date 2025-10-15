package de.mcmdev.vanish.state;

import org.bukkit.entity.Player;

public final class State {

  public void applyVanish(Player player) {
    PlayerVanishEvent playerVanishEvent = new PlayerVanishEvent(player);
    playerVanishEvent.callEvent();
  }

  public void unapplyVanish(Player player) {
    PlayerUnvanishEvent playerUnvanishEvent = new PlayerUnvanishEvent(player);
    playerUnvanishEvent.callEvent();
  }

  public void recalculate(Player player) {

  }

}
