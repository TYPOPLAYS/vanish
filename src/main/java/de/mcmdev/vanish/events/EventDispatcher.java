package de.mcmdev.vanish.events;

import org.bukkit.entity.Player;

public final class EventDispatcher {

    public void enter(final Player player) {
        final VanishEnterEvent vanishEnterEvent = new VanishEnterEvent(player);
        vanishEnterEvent.callEvent();
    }

    public void exit(final Player player) {
        final VanishExitEvent vanishExitEvent = new VanishExitEvent(player);
        vanishExitEvent.callEvent();
    }

    public void applyEffects(final Player player) {
        final VanishApplyEffectsEvent vanishApplyEffectsEvent = new VanishApplyEffectsEvent(player);
        vanishApplyEffectsEvent.callEvent();
    }

    public void clearEvents(final Player player) {
        final VanishClearEffectsEvent vanishClearEffectsEvent = new VanishClearEffectsEvent(player);
        vanishClearEffectsEvent.callEvent();
    }

    public void recalculate(final Player player) {
        final VanishRecalculateEvent vanishRecalculateEvent = new VanishRecalculateEvent(player);
        vanishRecalculateEvent.callEvent();
    }

}
