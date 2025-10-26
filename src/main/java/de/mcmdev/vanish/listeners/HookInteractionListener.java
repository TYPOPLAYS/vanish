package de.mcmdev.vanish.listeners;

import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.api.VanishApi;
import de.mcmdev.vanish.config.Config;
import jakarta.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public final class HookInteractionListener implements Listener {

    private final VanishPlugin plugin;
    private final VanishApi api;
    private final Config config;

    @Inject
    public HookInteractionListener(final VanishPlugin plugin, final VanishApi api, final Config config) {
        this.plugin = plugin;
        this.api = api;
        this.config = config;
    }

    public void register() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onPlayerInteractEntity(final PlayerInteractEntityEvent event)  {
        if (!api.isVanished(event.getPlayer())) {
            return;
        }

        if(event.getRightClicked() instanceof final Player target) {
            config.playerClickHook().execute(event.getPlayer(), target);
        }
    }

}
