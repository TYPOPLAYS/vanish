package de.mcmdev.vanish.effects.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;


/**
 * Represents an event triggered when a player's vanish effects are cleared.
 * <p>
 * This event is specifically used in scenarios where a player's invisibility
 * or vanish state is removed, and any associated effects (e.g., gameplay changes
 * or visual adjustments) are reverted. For instance, when a player exits the vanish
 * mode, this event ensures that corresponding effects such as scheduler tasks or
 * visibility settings are cleared.
 */
public final class VanishClearEffectsEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    VanishClearEffectsEvent(@NotNull final Player player) {
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
