package de.mcmdev.vanish.visibility;

import de.mcmdev.vanish.storage.Storage;

public final class VisibilityCalculatorModule {

    public VisibilityCalculator visibilityCalculator(final Storage storage) {
        return new OverrideAccountingVisibilityCalculator(storage, new PermissionVisibilityCalculator("vanish.see.%d", "vanish.use.%d", 100));
    }

}
