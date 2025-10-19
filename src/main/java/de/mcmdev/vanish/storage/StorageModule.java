package de.mcmdev.vanish.storage;

import de.mcmdev.vanish.config.Config;

public final class StorageModule {

    public Storage storage(Config config) {
        return switch (config.storageType()) {
            case PERSISTENT_DATA_CONTAINER -> new PersistentDataContainerStorage();
        };
    }

}
