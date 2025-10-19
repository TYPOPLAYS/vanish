package de.mcmdev.vanish.visibility;

import org.bukkit.entity.Player;

final class NoopVisibilityCalculator implements VisibilityCalculator {
    @Override
    public boolean canSee(final Player viewer, final Player target) {
        return viewer.equals(target);
    }

    @Override
    public boolean supportsLevels() {
        return false;
    }

    @Override
    public int getSeeLevel(final Player player) {
        throw new UnsupportedOperationException("The noop visibility calculator does not support levels.");
    }

    @Override
    public int getUseLevel(final Player player) {
        throw new UnsupportedOperationException("The noop visibility calculator does not support levels.");
    }

    @Override
    public int getMaximumUseLevel(final Player player) {
        throw new UnsupportedOperationException("The noop visibility calculator does not support levels.");
    }
}
