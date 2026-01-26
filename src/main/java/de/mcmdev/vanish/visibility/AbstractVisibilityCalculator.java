package de.mcmdev.vanish.visibility;

import org.bukkit.entity.Player;

abstract class AbstractVisibilityCalculator implements VisibilityCalculator {

    @Override
    public boolean canSee(final Player viewer, final Player target) {
        if (viewer.equals(target)) return true;

        return getSeeLevel(viewer) >= getUseLevel(target);
    }

    @Override
    public boolean supportsLevels() {
        return true;
    }

    @Override
    public int getMaximumUseLevel(final Player player) {
        return getUseLevel(player);
    }
}
