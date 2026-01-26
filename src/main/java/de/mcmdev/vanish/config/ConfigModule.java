package de.mcmdev.vanish.config;

import de.mcmdev.vanish.VanishPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.arim.dazzleconf.Configuration;
import space.arim.dazzleconf.StandardErrorPrint;
import space.arim.dazzleconf.backend.Backend;
import space.arim.dazzleconf.backend.PathRoot;
import space.arim.dazzleconf.backend.yaml.YamlBackend;
import space.arim.dazzleconf.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;

public final class ConfigModule {

    private static final Logger log = LoggerFactory.getLogger(ConfigModule.class);

    public Configuration<Config> configuration() {
        return Configuration.defaultBuilder(Config.class)
                .addSimpleSerializer(new TypeToken<>() {
                }, new Message.Serdes())
                .addSimpleSerializer(new TypeToken<>() {
                }, new CommandHook.Serdes())
                .build();
    }

    public Backend backend(final VanishPlugin vanishPlugin) throws IOException {
        Files.createDirectories(vanishPlugin.getDataPath());
        return new YamlBackend(new PathRoot(vanishPlugin.getDataPath().resolve("config.yml")));
    }

    public Config config(final Configuration<Config> configuration, final Backend backend) {
        return configuration.configureOrFallback(backend, new StandardErrorPrint(printable -> log.error(printable.printString())));
    }

}
