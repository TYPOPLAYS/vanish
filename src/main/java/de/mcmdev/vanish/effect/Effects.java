package de.mcmdev.vanish.effect;

import de.mcmdev.vanish.VanishPlugin;
import org.bukkit.Bukkit;

public class Effects {

  private final VanishPlugin plugin;
  private final VanishHidingEffectListener vanishHidingEffectListener;
  private final VanishMetadataEffectListener vanishMetadataEffectListener;

  public Effects(VanishPlugin plugin, VanishHidingEffectListener vanishHidingEffectListener,
                 VanishMetadataEffectListener vanishMetadataEffectListener) {
    this.plugin = plugin;
    this.vanishHidingEffectListener = vanishHidingEffectListener;
    this.vanishMetadataEffectListener = vanishMetadataEffectListener;
  }

  public void register() {
    Bukkit.getPluginManager().registerEvents(vanishHidingEffectListener, plugin);
    Bukkit.getPluginManager().registerEvents(vanishMetadataEffectListener, plugin);
  }

}
