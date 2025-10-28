package de.mcmdev.vanish.listeners.effects;

import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.events.VanishApplyEffectsEvent;
import de.mcmdev.vanish.events.VanishClearEffectsEvent;
import jakarta.inject.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public final class MetadataEffectListener implements Listener {

    private final VanishPlugin plugin;

    @Inject
    public MetadataEffectListener(final VanishPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onVanish(final VanishApplyEffectsEvent event) {
        final Player player = event.getPlayer();

        player.setMetadata("vanished", getValue());
    }

    @EventHandler
    private void onUnvanish(final VanishClearEffectsEvent event) {
        final Player player = event.getPlayer();

        player.removeMetadata("vanished", plugin);
    }

    private MetadataValue getValue() {
        return new FixedMetadataValue(plugin, true);
    }

}
