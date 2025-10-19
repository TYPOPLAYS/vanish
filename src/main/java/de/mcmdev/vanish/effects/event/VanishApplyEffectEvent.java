package de.mcmdev.vanish.effects.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event triggered when vanish effects are applied to a player.
 * <p>
 * This event is specifically used in scenarios where a player enters a vanish state,
 * and any associated effects (e.g., gameplay changes, visibility adjustments, or status messages)
 * are applied. For instance, when a player is set to invisible mode, this event ensures
 * that corresponding actions such as visual changes or scheduled tasks for visibility updates
 * are executed.
 */
public final class VanishApplyEffectEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    VanishApplyEffectEvent(@NotNull final Player player) {
        super(player);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
