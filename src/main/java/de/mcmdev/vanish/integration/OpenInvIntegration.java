package de.mcmdev.vanish.integration;

import com.lishid.openinv.IOpenInv;
import de.mcmdev.vanish.VanishPlugin;
import de.mcmdev.vanish.effects.event.VanishApplyEffectsEvent;
import de.mcmdev.vanish.effects.event.VanishClearEffectsEvent;
import jakarta.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class OpenInvIntegration implements Listener {

    private final VanishPlugin plugin;
    private final IOpenInv openInv;

    @Inject
    public OpenInvIntegration(final VanishPlugin plugin) {
        this.plugin = plugin;
        this.openInv = (IOpenInv) Bukkit.getPluginManager().getPlugin("OpenInv");
    }

    public void register() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onVanish(final VanishApplyEffectsEvent event)  {
        openInv.setAnyContainerStatus(event.getPlayer(), true);
        openInv.setSilentContainerStatus(event.getPlayer(), true);
    }

    @EventHandler
    private void onUnvanish(final VanishClearEffectsEvent event)  {
        openInv.setAnyContainerStatus(event.getPlayer(), false);
        openInv.setSilentContainerStatus(event.getPlayer(), false);
    }

}
