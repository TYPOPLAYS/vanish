package de.mcmdev.vanish.effects.registry;

import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.effects.event.VanishApplyEffectEvent;
import jakarta.inject.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

final class VanishMetadataEffectListener implements Listener {

    private final VanishPlugin plugin;

    @Inject
    public VanishMetadataEffectListener(final VanishPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onVanish(final VanishApplyEffectEvent event) {
        final Player player = event.getPlayer();

        player.setMetadata("vanished", getValue());
    }

    @EventHandler
    private void onUnvanish(final VanishApplyEffectEvent event) {
        final Player player = event.getPlayer();

        player.removeMetadata("vanished", plugin);
    }

    private MetadataValue getValue() {
        return new FixedMetadataValue(plugin, true);
    }

}
