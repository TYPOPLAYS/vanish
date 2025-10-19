package de.mcmdev.vanish.effects.registry;

import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.api.VanishApi;
import de.mcmdev.vanish.effects.event.VanishApplyEffectEvent;
import de.mcmdev.vanish.effects.event.VanishClearEffectEvent;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import jakarta.inject.Inject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
    private final Map<UUID, ScheduledTask> statusTask = new HashMap<>();

    @Inject
    public VanishStatusScheduler(final VanishApi vanishApi, final VanishPlugin plugin) {
        this.vanishApi = vanishApi;
        this.plugin = plugin;
    }

    @EventHandler
    private void onVanish(final VanishApplyEffectEvent event)  {
        final Player player = event.getPlayer();
        final UUID uniqueId = player.getUniqueId();
        final ScheduledTask scheduledTask = player.getScheduler().runAtFixedRate(plugin, (createdTask) -> {
            player.sendActionBar(getMessage(player));
        }, () -> statusTask.remove(uniqueId), 1, 20);
        statusTask.put(uniqueId, scheduledTask);
    }

    @EventHandler
    private void onUnvanish(final VanishClearEffectEvent event)  {
        event.getPlayer().sendActionBar(Component.empty());
        final ScheduledTask scheduledTask = statusTask.remove(event.getPlayer().getUniqueId());
        if(scheduledTask != null) {
            scheduledTask.cancel();
        }
    }

    private Component getMessage(final Player player) {
        return MiniMessage.miniMessage().deserialize("You are invisible | UseLevel: <use_level>", Placeholder.unparsed("use_level", String.valueOf(vanishApi.getUseLevel(player))));
    }

}
