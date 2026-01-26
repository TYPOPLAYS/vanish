package de.mcmdev.vanish.visibility;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public final class PermissionVisibilityCalculator extends AbstractVisibilityCalculator {

    private final PermissionIterator seeIterator;
    private final PermissionIterator useIterator;

    public PermissionVisibilityCalculator(final String seePermissionTemplate, final String usePermissionTemplate, final int maximumLevel) {
        this.seeIterator = new PermissionIterator(seePermissionTemplate, maximumLevel);
        this.useIterator = new PermissionIterator(usePermissionTemplate, maximumLevel);
    }

    @Override
    public int getSeeLevel(final Player player) {
        return seeIterator.get(player);
    }

    @Override
    public int getUseLevel(final Player player) {
        return useIterator.get(player);
    }

    private static class PermissionIterator {

        private final String permissionTemplate;
        private final int maximumLevel;
        private final LoadingCache<UUID, Integer> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public Integer load(final UUID key) {
                        return PermissionIterator.this.load(key);
                    }
                });

        private PermissionIterator(final String permissionTemplate, final int maximumLevel) {
            this.permissionTemplate = permissionTemplate;
            this.maximumLevel = maximumLevel;
        }

        public int get(final Player player) {
            try {
                return cache.get(player.getUniqueId());
            } catch (final ExecutionException e) {
                return 0;
            }
        }

        private int load(final UUID uuid) {
            final Player player = Bukkit.getPlayer(uuid);
            if (player == null) return 0;

            for (int i = maximumLevel; i > 0; i--) {
                final String permission = permissionTemplate.formatted(i);
                if (player.hasPermission(permission)) {
                    return i;
                }
            }
            return 0;
        }
    }
}
