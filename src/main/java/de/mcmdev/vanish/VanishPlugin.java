package de.mcmdev.vanish;

import de.mcmdev.vanish.api.ApiModule;
import de.mcmdev.vanish.config.ConfigModule;
import de.mcmdev.vanish.protection.Protection;
import de.mcmdev.vanish.storage.StorageModule;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import space.arim.injector.Injector;
import space.arim.injector.InjectorBuilder;

public class VanishPlugin extends JavaPlugin {

  private Injector injector;

  @Override
  public void onEnable() {
    try {
      enable();
    } catch (Exception e) {
      getSLF4JLogger().error("Failed to enable plugin", e);
      Bukkit.getPluginManager().disablePlugin(this);
    }
  }

  @Override
  public void onDisable() {

  }

  private void enable() throws Exception {
    this.injector = new InjectorBuilder()
        .bindInstance(VanishPlugin.class, this)
        .addBindModules(new ConfigModule(), new StorageModule(), new ApiModule())
        .build();

    injector.request(Protection.class).register();
  }
}
