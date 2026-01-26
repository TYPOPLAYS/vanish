package de.mcmdev.vanish.listeners.protection;

import de.mcmdev.vanish.api.VanishApi;
import jakarta.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;

public final class PlayerInteractProtectionListener extends AbstractProtectionListener {

    @Inject
    public PlayerInteractProtectionListener(final VanishApi api) {
        super(api);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onPlayerInteract(final PlayerInteractEvent event) {
        if (shouldProtect(event.getPlayer(), "player_interact")) {
            event.setCancelled(true);
        }
    }
}
