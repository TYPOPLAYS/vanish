package de.mcmdev.vanish;

import de.mcmdev.vanish.api.ApiModule;
import de.mcmdev.vanish.command.VanishCommand;
import de.mcmdev.vanish.config.ConfigModule;
import de.mcmdev.vanish.listeners.HookListener;
import de.mcmdev.vanish.listeners.JoinQuitDispatcherListener;
import de.mcmdev.vanish.integration.OpenInvIntegration;
import de.mcmdev.vanish.integration.VanishExpansion;
import de.mcmdev.vanish.listeners.effects.*;
import de.mcmdev.vanish.listeners.protection.*;
import de.mcmdev.vanish.storage.StorageModule;
import de.mcmdev.vanish.visibility.VisibilityCalculatorModule;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
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
        registerListener(JoinQuitDispatcherListener.class);
        registerListener(InteractionBlockingEffectListener.class);
        registerListener(HookListener.class);

        registerListener(BlockBreakProtectionListener.class);
        registerListener(BlockPlaceProtectionListener.class);
        registerListener(EntityDamageProtectionListener.class);
        registerListener(PlayerDropItemProtectionListener.class);
        registerListener(PlayerInteractProtectionListener.class);

        registerListener(AffectSpawningEffectListener.class);
        registerListener(HidingEffectListener.class);
        registerListener(InteractionBlockingEffectListener.class);
        registerListener(MetadataEffectListener.class);
        registerListener(VanishStatusScheduler.class);

        // Expansions (also mostly just listeners)
        injector.request(VanishExpansion.class).register();
        if(Bukkit.getPluginManager().isPluginEnabled("OpenInv")) {
            injector.request(OpenInvIntegration.class).register();
        }

        // Vanish command
        injector.request(VanishCommand.class).register();
    }

    private void registerListener(final Class<? extends Listener> listenerClass) {
        Bukkit.getPluginManager().registerEvents(injector.request(listenerClass), this);
    }
}
