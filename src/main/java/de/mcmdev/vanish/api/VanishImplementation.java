package de.mcmdev.vanish.api;

import de.mcmdev.vanish.events.EventDispatcher;
import de.mcmdev.vanish.storage.Storage;
import de.mcmdev.vanish.visibility.VisibilityCalculator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

final class VanishImplementation implements VanishApi {

    private final Storage storage;
    private final EventDispatcher eventDispatcher;
    private final VisibilityCalculator visibilityCalculator;

    VanishImplementation(final Storage storage, final EventDispatcher eventDispatcher, final VisibilityCalculator visibilityCalculator) {
        this.storage = storage;
        this.eventDispatcher = eventDispatcher;
        this.visibilityCalculator = visibilityCalculator;
    }

    @Override
    public void vanish(final UUID uuid) {
        if (isVanished(uuid)) {
            return;
        }

        final Player player = Bukkit.getPlayer(uuid);

        if (player == null && !storage.supportsOfflinePlayers()) {
            throw new IllegalArgumentException("Offline players are not supported by the storage implementation.");
        }

        storage.setVanished(uuid, true);

        if (player != null) {
            eventDispatcher.applyEffects(player);
            eventDispatcher.enter(player);
        }
    }

    @Override
    public void unvanish(final UUID uuid) {
        if (!isVanished(uuid)) {
            return;
        }

        final Player player = Bukkit.getPlayer(uuid);

        if (player == null && !storage.supportsOfflinePlayers()) {
            throw new IllegalArgumentException("Offline players are not supported by the storage implementation.");
        }

        storage.setVanished(uuid, false);


        if (player != null) {
            eventDispatcher.exit(player);
            eventDispatcher.clearEvents(player);
        }
    }

    @Override
    public boolean isVanished(final UUID uuid) {
        final Player player = Bukkit.getPlayer(uuid);

        if (player == null && !storage.supportsOfflinePlayers()) {
            throw new IllegalArgumentException("Offline players are not supported by the storage implementation.");
        }

        return storage.isVanished(uuid);
    }

    @Override
    public boolean supportsOfflinePlayers() {
        return storage.supportsOfflinePlayers();
    }

    @Override
    public boolean canSee(final Player viewer, final Player target) {
        return visibilityCalculator.canSee(viewer, target);
    }

    @Override
    public int getSeeLevel(final Player player) {
        return visibilityCalculator.getSeeLevel(player);
    }

    @Override
    public int getUseLevel(final Player player) {
        return visibilityCalculator.getUseLevel(player);
    }

    @Override
    public int getMaximumUseLevel(final Player player) {
        return visibilityCalculator.getMaximumUseLevel(player);
    }

    @Override
    public Integer getLevelOverride(final UUID uuid) {
        return storage.getVanishLevelOverride(uuid);
    }

    @Override
    public void setLevelOverride(final UUID uuid, final Integer value) {
        final Player player = Bukkit.getPlayer(uuid);

        if (player == null && !storage.supportsOfflinePlayers()) {
            throw new IllegalArgumentException("Offline players are not supported by the storage implementation.");
        }

        storage.setVanishLevelOverride(uuid, value);

        if(player != null) {
            eventDispatcher.recalculate(player);
        }
    }

    @Override
    public boolean supportsLevels() {
        return visibilityCalculator.supportsLevels();
    }
}
