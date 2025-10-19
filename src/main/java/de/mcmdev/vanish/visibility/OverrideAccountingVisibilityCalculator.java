package de.mcmdev.vanish.visibility;

import de.mcmdev.vanish.storage.Storage;
import org.bukkit.entity.Player;

public final class OverrideAccountingVisibilityCalculator extends AbstractVisibilityCalculator {

    private final Storage storage;
    private final VisibilityCalculator delegate;

    public OverrideAccountingVisibilityCalculator(final Storage storage, final VisibilityCalculator delegate) {
        this.storage = storage;
        this.delegate = delegate;
    }

    @Override
    public int getUseLevel(final Player player) {
        final Integer vanishLevelOverride = storage.getVanishLevelOverride(player.getUniqueId());

        final int delegateUseLevel = delegate.getUseLevel(player);
        if(vanishLevelOverride == null) {
            return delegateUseLevel;
        }

        if(delegateUseLevel < vanishLevelOverride) {
            return delegateUseLevel;
        }

        return vanishLevelOverride;
    }

    @Override
    public int getSeeLevel(final Player player) {
        return delegate.getSeeLevel(player);
    }
}
