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
    private final VanishAffectSpawningEffectListener vanishAffectSpawningEffectListener;
    private final VanishHookListener vanishHookListener;

    @Inject
    public EffectRegistry(final VanishPlugin plugin,
                          final VanishHidingEffectListener vanishHidingEffectListener,
                          final VanishMetadataEffectListener vanishMetadataEffectListener,
                          final VanishFakeMessageEffectListener vanishFakeMessageEffectListener,
                          final VanishStatusScheduler vanishStatusScheduler,
                          final VanishAffectSpawningEffectListener vanishAffectSpawningEffectListener, final VanishHookListener vanishHookListener
    ) {
        this.plugin = plugin;
        this.vanishHidingEffectListener = vanishHidingEffectListener;
        this.vanishMetadataEffectListener = vanishMetadataEffectListener;
        this.vanishFakeMessageEffectListener = vanishFakeMessageEffectListener;
        this.vanishStatusScheduler = vanishStatusScheduler;
        this.vanishAffectSpawningEffectListener = vanishAffectSpawningEffectListener;
        this.vanishHookListener = vanishHookListener;
    }

    public void register() {
        Bukkit.getPluginManager().registerEvents(vanishHidingEffectListener, plugin);
        Bukkit.getPluginManager().registerEvents(vanishMetadataEffectListener, plugin);
        Bukkit.getPluginManager().registerEvents(vanishFakeMessageEffectListener, plugin);
        Bukkit.getPluginManager().registerEvents(vanishStatusScheduler, plugin);
        Bukkit.getPluginManager().registerEvents(vanishAffectSpawningEffectListener, plugin);
        Bukkit.getPluginManager().registerEvents(vanishHookListener, plugin);
    }

}
