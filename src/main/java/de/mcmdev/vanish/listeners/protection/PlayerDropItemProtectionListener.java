package de.mcmdev.vanish.listeners.protection;

import de.mcmdev.vanish.api.VanishApi;
import jakarta.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerDropItemEvent;

public final class PlayerDropItemProtectionListener extends AbstractProtectionListener {

    @Inject
    public PlayerDropItemProtectionListener(final VanishApi api) {
        super(api);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onPlayerDropItem(final PlayerDropItemEvent event) {
        if (shouldProtect(event.getPlayer(), "player_drop")) {
            event.setCancelled(true);
        }
    }

}
