package de.mcmdev.vanish.effects.event;

import org.bukkit.entity.Player;

public final class EffectDispatcher {

    public void applyVanish(final Player player) {
        final VanishApplyEffectEvent vanishApplyEffectEvent = new VanishApplyEffectEvent(player);
        vanishApplyEffectEvent.callEvent();
    }

    public void clearVanish(final Player player) {
        final VanishClearEffectEvent vanishClearEffectEvent = new VanishClearEffectEvent(player);
        vanishClearEffectEvent.callEvent();
    }

    public void recalculate(final Player player) {
        final VanishRecalculateEffectEvent vanishRecalculateEffectEvent = new VanishRecalculateEffectEvent(player);
        vanishRecalculateEffectEvent.callEvent();
    }

}
