package de.mcmdev.vanish;

import de.mcmdev.vanish.api.ApiModule;
import de.mcmdev.vanish.command.VanishCommand;
import de.mcmdev.vanish.config.ConfigModule;
import de.mcmdev.vanish.effects.registry.EffectRegistry;
import de.mcmdev.vanish.effects.listener.JoinQuitEffectListener;
import de.mcmdev.vanish.integration.VanishExpansion;
import de.mcmdev.vanish.protection.Protection;
import de.mcmdev.vanish.storage.StorageModule;
import de.mcmdev.vanish.visibility.VisibilityCalculatorModule;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import space.arim.injector.Injector;
import space.arim.injector.InjectorBuilder;
import space.arim.injector.SpecificationSupport;

public class VanishPlugin extends JavaPlugin {

    private Injector injector;

    @Override
    public void onEnable() {
        try {
            enable();
        } catch (final Exception e) {
            getSLF4JLogger().error("Failed to enable plugin", e);
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private void enable() {
        this.injector = new InjectorBuilder()
                .specification(SpecificationSupport.JAKARTA)
                .bindInstance(VanishPlugin.class, this)
                .addBindModules(new ConfigModule(), new StorageModule(), new VisibilityCalculatorModule(), new ApiModule())
                .build();

        injector.request(Protection.class).register();
        injector.request(EffectRegistry.class).register();

        Bukkit.getPluginManager().registerEvents(injector.request(JoinQuitEffectListener.class), this);

        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> event.registrar().register(injector.request(VanishCommand.class).register()));

        injector.request(VanishExpansion.class).register();
    }
}
