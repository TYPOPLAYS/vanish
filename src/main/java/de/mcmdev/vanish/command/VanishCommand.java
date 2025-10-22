package de.mcmdev.vanish.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.mcmdev.vanish.api.VanishApi;
import de.mcmdev.vanish.config.Config;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import jakarta.inject.Inject;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

public class VanishCommand {

    private final VanishApi vanishApi;
    private final Config config;

    @Inject
    public VanishCommand(final VanishApi vanishApi, final Config config) {
        this.vanishApi = vanishApi;
        this.config = config;
    }

    public LiteralCommandNode<CommandSourceStack> register() {
        return Commands.literal("vanish")
                .executes(this::run)
                .then(
                        Commands.literal("setlevel")
                                .executes(this::runSetlevel)
                                .then(
                                        Commands.argument("level", IntegerArgumentType.integer(0, config.maximumHidingLevel()))
                                                .executes(this::runSetlevelLevel)
                                )
                )
                .build();
    }

    private int run(final CommandContext<CommandSourceStack> context) {
        if (!(context.getSource().getExecutor() instanceof final Player player)) {
            return 1;
        }

        if (vanishApi.isVanished(player.getUniqueId())) {
            vanishApi.unvanish(player.getUniqueId());
            config.messages().toggleOff().send(player);
        } else {
            vanishApi.vanish(player.getUniqueId());
            config.messages().toggleOn().send(player);
        }

        return 0;
    }

    private int runSetlevel(final CommandContext<CommandSourceStack> context) {
        if (!(context.getSource().getExecutor() instanceof final Player player)) {
            return 1;
        }

        if(!vanishApi.supportsLevels()) {
            player.sendRichMessage("<red>Vanish levels are not available in the current configuration.");
            return 2;
        }

        final boolean hasOverride = vanishApi.getLevelOverride(player.getUniqueId()) != null;
        if(!hasOverride) {
            config.messages().levelOverrideNotSet().send(player);
            return 3;
        }

        vanishApi.setLevelOverride(player.getUniqueId(), null);
        config.messages().levelOverrideCleared().send(player);

        return 0;
    }

    private int runSetlevelLevel(final CommandContext<CommandSourceStack> context) {
        if (!(context.getSource().getExecutor() instanceof final Player player)) {
            return 1;
        }

        final Integer level = context.getArgument("level", Integer.class);

        if(!vanishApi.supportsLevels()) {
            player.sendRichMessage("<red>Vanish levels are not available in the current configuration.");
            return 2;
        }

        vanishApi.setLevelOverride(player.getUniqueId(), level);
        config.messages().levelOverrideSet().send(player, Placeholder.parsed("level", String.valueOf(level)));

        return 0;
    }

}
