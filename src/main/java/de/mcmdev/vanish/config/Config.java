package de.mcmdev.vanish.config;

import space.arim.dazzleconf.engine.Comments;
import space.arim.dazzleconf.engine.liaison.SubSection;

@Comments("Configuration file for mcmdev's vanish plugin")
public interface Config {

    default StorageType storageType() {
        return StorageType.PERSISTENT_DATA_CONTAINER;
    }

    default int maximumHidingLevel() {
        return 100;
    }

    default CommandHook vanishHooks() {
        return CommandHook.empty();
    }

    default CommandHook unvanishHooks() {
        return CommandHook.empty();
    }

    default CommandHook playerClickHook() {
        return CommandHook.of("inv %player_name%");
    }

    @SubSection
    default Messages messages() {
        return new Messages() {
        };
    }

    enum StorageType {
        PERSISTENT_DATA_CONTAINER,
    }

    interface Messages {

        default Message actionbar() {
            return Message.of("<aqua>You are invisible.");
        }

        default Message toggleOn() {
            return Message.of("<aqua>You are now invisible.");
        }

        default Message toggleOff() {
            return Message.of("<aqua>You are no longer invisible.");
        }

        default Message levelOverrideSet() {
            return Message.of("<aqua>Your level override has been set to <yellow><level></yellow>.");
        }

        default Message levelOverrideCleared() {
            return Message.of("<aqua>Your level override has been cleared.");
        }

        default Message levelOverrideNotSet() {
            return Message.of("<red>You currently have no level override set.");
        }

        default Message fakeJoin() {
            return Message.of("<yellow>%player_name% joined the game.</yellow>");
        }

        default Message fakeQuit() {
            return Message.of("<yellow>%player_name% left the game.</yellow>");
        }

        default Message notifyJoin() {
            return Message.of("<aqua>%player_name% joined the game while invisible.</aqua>");
        }

        default Message notifyQuit() {
            return Message.of("<aqua>%player_name% left the game while invisible.</aqua>");
        }

    }

}
