package de.mcmdev.vanish.listeners;

import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.api.VanishApi;
import jakarta.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.GenericGameEvent;

public final class ProtectionListener implements Listener {

    private final VanishPlugin plugin;
    private final VanishApi api;

    @Inject
    public ProtectionListener(final VanishPlugin plugin, final VanishApi api) {
        this.plugin = plugin;
        this.api = api;
    }

    public void register() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockPlace(final BlockPlaceEvent event) {
        if(shouldProtect(event.getPlayer(), "block_place")) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockBreak(final BlockBreakEvent event) {
        if(shouldProtect(event.getPlayer(), "block_break")) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof final Player player) {
            if(api.isVanished(player)) {
                event.setCancelled(true);
            }
        }

        if(event.getDamager() instanceof final Player player) {
            if(shouldProtect(player, "entity_damage")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onEntityDamageByBlock(final EntityDamageByBlockEvent event) {
        if(event.getEntity() instanceof final Player player) {
            if(api.isVanished(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onEntityDamage(final EntityDamageEvent event) {
        if(event.getEntity() instanceof final Player player) {
            if(api.isVanished(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onPlayerInteract(final PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL) {
            if(api.isVanished(event.getPlayer())) {
                event.setCancelled(true);
            }
        }   else    {
            if(shouldProtect(event.getPlayer(), "player_interact")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onEntityTarget(final EntityTargetEvent event) {
        if(event.getTarget() instanceof final Player player) {
            if(api.isVanished(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onSculkTrigger(final GenericGameEvent event)   {
        if(event.getEntity() instanceof final Player player) {
            if(api.isVanished(player)) {
                event.setCancelled(true);
            }
        }
    }

    private boolean shouldProtect(final Player player, final String actionName) {
        return api.isVanished(player) && !player.hasPermission("vanish.protection." + actionName);
    }

}
