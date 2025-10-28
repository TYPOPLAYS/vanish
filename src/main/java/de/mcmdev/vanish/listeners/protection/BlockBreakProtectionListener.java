package de.mcmdev.vanish.listeners.protection;

import de.mcmdev.vanish.api.VanishApi;
import jakarta.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

public final class BlockBreakProtectionListener extends AbstractProtectionListener {

    @Inject
    public BlockBreakProtectionListener(final VanishApi api) {
        super(api);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockBreak(final BlockBreakEvent event) {
        if(shouldProtect(event.getPlayer(), "block_break")) {
            event.setCancelled(true);
        }
    }

}
