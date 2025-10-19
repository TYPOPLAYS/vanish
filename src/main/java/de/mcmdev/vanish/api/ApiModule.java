package de.mcmdev.vanish.api;

import de.mcmdev.vanish.effects.event.EffectDispatcher;
import de.mcmdev.vanish.storage.Storage;
import de.mcmdev.vanish.visibility.VisibilityCalculator;

public final class ApiModule {

    public VanishApi api(final Storage storage, final EffectDispatcher effectDispatcher, final VisibilityCalculator visibilityCalculator) {
        return new VanishImplementation(storage, effectDispatcher, visibilityCalculator);
    }

}
