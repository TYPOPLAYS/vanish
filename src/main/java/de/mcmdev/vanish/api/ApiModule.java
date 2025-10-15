package de.mcmdev.vanish.api;

import de.mcmdev.vanish.state.State;
import de.mcmdev.vanish.storage.Storage;

public final class ApiModule {
  
  VanishApi api(Storage storage, State state) {
    return new VanishImplementation(storage, state);
  }
  
}
