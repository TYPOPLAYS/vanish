package de.mcmdev.vanish.listeners.protection;

import de.mcmdev.vanish.api.VanishApi;
import jakarta.inject.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public final class EntityDamageProtectionListener extends AbstractProtectionListener {

    @Inject
    public EntityDamageProtectionListener(final VanishApi api) {
        super(api);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof final Player player) {
            if(shouldProtect(player, "entity_damage")) {
                event.setCancelled(true);
            }
        }
    }

}
