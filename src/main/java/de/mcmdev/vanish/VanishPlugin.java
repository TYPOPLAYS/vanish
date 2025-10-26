package de.mcmdev.vanish;

import de.mcmdev.vanish.api.ApiModule;
import de.mcmdev.vanish.command.VanishCommand;
import de.mcmdev.vanish.config.ConfigModule;
import de.mcmdev.vanish.effects.registry.EffectRegistry;
import de.mcmdev.vanish.effects.listener.JoinQuitEffectListener;
import de.mcmdev.vanish.integration.OpenInvIntegration;
import de.mcmdev.vanish.integration.VanishExpansion;
import de.mcmdev.vanish.listeners.HookInteractionListener;
import de.mcmdev.vanish.listeners.ProtectionListener;
import de.mcmdev.vanish.storage.StorageModule;
import de.mcmdev.vanish.visibility.VisibilityCalculatorModule;
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

        // Listeners
        injector.request(EffectRegistry.class).register();
        injector.request(JoinQuitEffectListener.class).register();
        injector.request(ProtectionListener.class).register();
        injector.request(HookInteractionListener.class).register();

        // Expansions (also mostly just listeners)
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            injector.request(VanishExpansion.class).register();
        }
        if(Bukkit.getPluginManager().isPluginEnabled("OpenInv")) {
            injector.request(OpenInvIntegration.class).register();
        }

        // Vanish command
        injector.request(VanishCommand.class).register();

    }
}
