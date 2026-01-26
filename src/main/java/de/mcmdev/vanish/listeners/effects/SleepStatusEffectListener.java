package de.mcmdev.vanish.listeners.effects;

import de.mcmdev.vanish.events.VanishApplyEffectsEvent;
import de.mcmdev.vanish.events.VanishClearEffectsEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class SleepStatusEffectListener implements Listener {

    @EventHandler
    private void onVanish(final VanishApplyEffectsEvent event) {
        event.getPlayer().setSleepingIgnored(true);
    }

    @EventHandler
    private void onUnvanish(final VanishClearEffectsEvent event) {
        event.getPlayer().setSleepingIgnored(false);
    }

}
