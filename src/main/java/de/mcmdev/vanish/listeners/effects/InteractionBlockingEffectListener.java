package de.mcmdev.vanish.listeners.effects;

import de.mcmdev.vanish.api.VanishApi;
import jakarta.inject.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.GenericGameEvent;

public final class InteractionBlockingEffectListener implements Listener {

    private final VanishApi api;

    @Inject
    public InteractionBlockingEffectListener(final VanishApi api) {
        this.api = api;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onPlayerInteract(final PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL) {
            if(api.isVanished(event.getPlayer())) {
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
    private void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof final Player player) {
            if(api.isVanished(player)) {
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

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onEntityPickupItem(final EntityPickupItemEvent event) {
        if (event.getEntity() instanceof final Player player) {
            if(api.isVanished(player)) {
                event.setCancelled(true);
            }
        }
    }



}
