package de.mcmdev.vanish.integration;

import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.api.VanishApi;
import jakarta.inject.Inject;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Relational;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class VanishExpansion extends PlaceholderExpansion implements Relational {

    private final VanishApi api;
    private final VanishPlugin plugin;

    @Inject
    public VanishExpansion(final VanishApi api, final VanishPlugin plugin) {
        this.api = api;
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return plugin.getPluginMeta().getName();
    }

    @Override
    public @NotNull String getAuthor() {
        return String.join(" ", plugin.getPluginMeta().getAuthors());
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getPluginMeta().getVersion();
    }

    @Override
    public @NotNull List<String> getPlaceholders() {
        return List.of("vanished", "count");
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(final OfflinePlayer player, @NotNull final String params) {
        if(params.equals("vanished")) {
            if(!player.isOnline()) {
                return api.supportsOfflinePlayers() ? api.isVanished(player.getUniqueId()) ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse() : null;
            }

            return api.isVanished(player.getUniqueId()) ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
        }
        if(params.equals("count")) {
            return String.valueOf(Bukkit.getOnlinePlayers().stream()
                    .filter(all -> api.isVanished(all))
                    .count());
        }

        return null;
    }

    @Override
    public String onPlaceholderRequest(final Player one, final Player two, final String identifier) {
        if(identifier.equals("vanished")) {
            if(one == null || two == null) {
                return null;
            }

            if(!api.isVanished(two)) {
                return PlaceholderAPIPlugin.booleanFalse();
            }
            if(one.equals(two)) return PlaceholderAPIPlugin.booleanTrue();
            return api.canSee(one, two) ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
        }

        if(identifier.equals("can_see")) {
            if(one == null || two == null) {
                return null;
            }

            if(!api.isVanished(two)) {
                return PlaceholderAPIPlugin.booleanTrue();
            }
            if(one.equals(two)) return PlaceholderAPIPlugin.booleanTrue();
            return api.canSee(one, two) ?  PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
        }

        return null;
    }
}
