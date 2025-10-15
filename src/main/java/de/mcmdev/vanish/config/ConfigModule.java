package de.mcmdev.vanish.config;

import de.mcmdev.vanish.VanishPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.arim.dazzleconf.Configuration;
import space.arim.dazzleconf.StandardErrorPrint;
import space.arim.dazzleconf.backend.Backend;
import space.arim.dazzleconf.backend.PathRoot;
import space.arim.dazzleconf.backend.yaml.YamlBackend;

public final class ConfigModule {

  private static final Logger log = LoggerFactory.getLogger(ConfigModule.class);

  Configuration<Config> configuration() {
    return Configuration.defaultBuilder(Config.class).build();
  }

  Backend backend(VanishPlugin vanishPlugin) {
    return new YamlBackend(new PathRoot(vanishPlugin.getDataPath().resolve("config.yml")));
  }

  public Config config(Configuration<Config> configuration, Backend backend) {
    return configuration.configureOrFallback(backend, new StandardErrorPrint(printable -> log.error(printable.printString())));
  }

}
