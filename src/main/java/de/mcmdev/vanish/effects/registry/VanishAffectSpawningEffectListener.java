package de.mcmdev.vanish.effects.registry;

import de.mcmdev.vanish.effects.event.VanishApplyEffectsEvent;
import de.mcmdev.vanish.effects.event.VanishClearEffectsEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class VanishAffectSpawningEffectListener implements Listener {

    @EventHandler
    private void onVanish(final VanishApplyEffectsEvent event)    {
        event.getPlayer().setAffectsSpawning(false);
    }

    @EventHandler
    private void onUnvanish(final VanishClearEffectsEvent event)    {
        event.getPlayer().setAffectsSpawning(true);
    }

}
