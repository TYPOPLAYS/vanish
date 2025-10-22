package de.mcmdev.vanish.effects.registry;

import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.api.VanishApi;
import de.mcmdev.vanish.config.Config;
import de.mcmdev.vanish.effects.event.VanishApplyEffectsEvent;
import de.mcmdev.vanish.effects.event.VanishClearEffectsEvent;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import jakarta.inject.Inject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

final class VanishStatusScheduler implements Listener {

    private final VanishApi vanishApi;
    private final VanishPlugin plugin;
    private final Config config;
    private final Map<UUID, ScheduledTask> statusTask = new HashMap<>();

    @Inject
    public VanishStatusScheduler(final VanishApi vanishApi, final VanishPlugin plugin,
                                 Config config) {
        this.vanishApi = vanishApi;
        this.plugin = plugin;
        this.config = config;
    }

    @EventHandler
    private void onVanish(final VanishApplyEffectsEvent event)  {
        final Player player = event.getPlayer();
        final UUID uniqueId = player.getUniqueId();
        final ScheduledTask scheduledTask = player.getScheduler().runAtFixedRate(plugin, (createdTask) -> {
            config.messages().actionbar().sendActionbar(player, Placeholder.parsed("use_level", String.valueOf(vanishApi.getUseLevel(player))));
        }, () -> statusTask.remove(uniqueId), 1, 20);
        statusTask.put(uniqueId, scheduledTask);
    }

    @EventHandler
    private void onUnvanish(final VanishClearEffectsEvent event)  {
        event.getPlayer().sendActionBar(Component.empty());
        final ScheduledTask scheduledTask = statusTask.remove(event.getPlayer().getUniqueId());
        if(scheduledTask != null) {
            scheduledTask.cancel();
        }
    }

}
