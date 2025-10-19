package de.mcmdev.vanish.protection;

import de.mcmdev.vanish.storage.Storage;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import java.util.function.Function;

abstract class AbstractProtectionListener<E extends Event & Cancellable> implements Listener {

    private final Storage storage;
    private final String permissionName;

    AbstractProtectionListener(Storage storage, String permissionName) {
        this.storage = storage;
        this.permissionName = permissionName;
    }

    static <E extends Event & Cancellable> AbstractProtectionListener<E> create(Storage storage, String permissionName, Function<E, Object> playerFunction) {
        return new AbstractProtectionListener<>(storage, permissionName) {
            @Override
            Object getPlayer(E event) {
                return playerFunction.apply(event);
            }
        };
    }

    boolean canBypass(Player player) {
        return player.hasPermission(permissionName);
    }

    boolean doesApply(Player player) {
        return !canBypass(player) && storage.isVanished(player.getUniqueId());
    }

    void onEvent(E event) {
        Object object = getPlayer(event);
        if (object instanceof Player player) {
            if (doesApply(player)) {
                event.setCancelled(true);
            }
        }
    }

    abstract Object getPlayer(E event);

}
