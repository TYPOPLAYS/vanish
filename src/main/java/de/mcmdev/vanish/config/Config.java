package de.mcmdev.vanish.config;

import space.arim.dazzleconf.engine.Comments;

@Comments("This is the configuration file header.")
public interface Config {

  default StorageType storageType() {
    return StorageType.PERSISTENT_DATA_CONTAINER;
  }

  enum StorageType {
    PERSISTENT_DATA_CONTAINER,
  }

}
