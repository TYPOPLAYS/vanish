package de.mcmdev.vanish.effects.registry;

import de.mcmdev.vanish.VanishPlugin;
import jakarta.inject.Inject;
import org.bukkit.Bukkit;

public final class EffectRegistry {

    private final VanishPlugin plugin;
    private final VanishHidingEffectListener vanishHidingEffectListener;
    private final VanishMetadataEffectListener vanishMetadataEffectListener;
    private final VanishFakeMessageEffectListener vanishFakeMessageEffectListener;
    private final VanishStatusScheduler vanishStatusScheduler;

    @Inject
    public EffectRegistry(final VanishPlugin plugin,
                          final VanishHidingEffectListener vanishHidingEffectListener,
                          final VanishMetadataEffectListener vanishMetadataEffectListener,
                          final VanishFakeMessageEffectListener vanishFakeMessageEffectListener,
                          final VanishStatusScheduler vanishStatusScheduler
    ) {
        this.plugin = plugin;
        this.vanishHidingEffectListener = vanishHidingEffectListener;
        this.vanishMetadataEffectListener = vanishMetadataEffectListener;
        this.vanishFakeMessageEffectListener = vanishFakeMessageEffectListener;
        this.vanishStatusScheduler = vanishStatusScheduler;
    }

    public void register() {
        Bukkit.getPluginManager().registerEvents(vanishHidingEffectListener, plugin);
        Bukkit.getPluginManager().registerEvents(vanishMetadataEffectListener, plugin);
        Bukkit.getPluginManager().registerEvents(vanishFakeMessageEffectListener, plugin);
        Bukkit.getPluginManager().registerEvents(vanishStatusScheduler, plugin);
    }

}
