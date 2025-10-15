package de.mcmdev.vanish.visibility;

import org.bukkit.entity.Player;

public interface VisibilityCalculator {

  boolean canSee(Player viewer, Player target);

}
