package de.mcmdev.vanish.integration;

import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.events.VanishEnterEvent;
import de.mcmdev.vanish.events.VanishExitEvent;
import github.scarsz.discordsrv.DiscordSRV;
import jakarta.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class DiscordSrvIntegration implements Listener {

    private final VanishPlugin plugin;

    @Inject
    public DiscordSrvIntegration(final VanishPlugin plugin) {
        this.plugin = plugin;
    }

    public void register() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onEnter(final VanishEnterEvent event) {
        DiscordSRV.getPlugin().sendLeaveMessage(event.getPlayer(), null);
    }

    @EventHandler
    private void onExit(final VanishExitEvent event) {
        DiscordSRV.getPlugin().sendJoinMessage(event.getPlayer(), null);
    }

}
