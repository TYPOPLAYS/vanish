package de.mcmdev.vanish.listeners.protection;

import de.mcmdev.vanish.api.VanishApi;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

abstract class AbstractProtectionListener implements Listener {

    protected final VanishApi api;

    AbstractProtectionListener(final VanishApi api) {
        this.api = api;
    }

    protected boolean shouldProtect(final Player player, final String actionName) {
        return api.isVanished(player) && !player.hasPermission("vanish.protection." + actionName);
    }

}
