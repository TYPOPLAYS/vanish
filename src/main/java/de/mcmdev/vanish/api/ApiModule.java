package de.mcmdev.vanish.api;

import de.mcmdev.vanish.events.EventDispatcher;
import de.mcmdev.vanish.storage.Storage;
import de.mcmdev.vanish.visibility.VisibilityCalculator;

public final class ApiModule {

    public VanishApi api(final Storage storage, final EventDispatcher eventDispatcher, final VisibilityCalculator visibilityCalculator) {
        return new VanishImplementation(storage, eventDispatcher, visibilityCalculator);
    }

}
