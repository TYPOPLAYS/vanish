package de.mcmdev.vanish.protection;

import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.storage.Storage;
import jakarta.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public final class Protection {

    private final VanishPlugin plugin;
    private final Storage storage;

    @Inject
    public Protection(VanishPlugin plugin, Storage storage) {
        this.plugin = plugin;
        this.storage = storage;
    }

    public void register() {
        register(BlockBreakEvent.class, AbstractProtectionListener.create(storage, "block_break", BlockBreakEvent::getPlayer));
        register(BlockPlaceEvent.class, AbstractProtectionListener.create(storage, "block_place", BlockPlaceEvent::getPlayer));
        register(EntityDamageByEntityEvent.class, AbstractProtectionListener.create(storage, "entity_damage", EntityDamageByEntityEvent::getDamager));
        register(PlayerInteractEvent.class, AbstractProtectionListener.create(storage, "player_interact", PlayerInteractEvent::getPlayer));
    }

    private <E extends Event & Cancellable> void register(Class<E> eventClass, AbstractProtectionListener<E> listener) {
        Bukkit.getPluginManager().registerEvent(eventClass, listener, EventPriority.NORMAL, (listener1, event) -> execute(listener1, event, eventClass), plugin);
    }

    private <E extends Event & Cancellable> void execute(@NotNull Listener listener, @NotNull Event event, Class<E> eventClass) {
        if (!eventClass.isAssignableFrom(event.getClass())) {
            return;
        }
        E castEvent = eventClass.cast(event);
        ((AbstractProtectionListener<E>) listener).onEvent(castEvent);
    }

}
