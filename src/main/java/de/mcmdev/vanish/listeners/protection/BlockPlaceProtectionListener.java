package de.mcmdev.vanish.listeners.protection;

import de.mcmdev.vanish.api.VanishApi;
import jakarta.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;

public final class BlockPlaceProtectionListener extends AbstractProtectionListener {

    @Inject
    public BlockPlaceProtectionListener(final VanishApi api) {
        super(api);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockPlace(final BlockPlaceEvent event) {
        if (shouldProtect(event.getPlayer(), "block_place")) {
            event.setCancelled(true);
        }
    }

}
