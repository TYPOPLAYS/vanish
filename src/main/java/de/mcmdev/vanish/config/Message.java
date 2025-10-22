package de.mcmdev.vanish.config;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;

public final class Message {

  public static Message of(String messageTemplate) {
    return new Message(messageTemplate);
  }

  private final String messageTemplate;

  Message(String messageTemplate) {
    this.messageTemplate = messageTemplate;
  }

  String messageTemplate() {
    return messageTemplate;
  }

  public void send(Player receiver, TagResolver... resolvers) {
    String resolvedMessage = PlaceholderAPI.setPlaceholders(receiver, messageTemplate);
    receiver.sendRichMessage(resolvedMessage, resolvers);
  }

  public void send(Player receiver, Player subject, TagResolver... resolvers) {
    String resolvedMessage = PlaceholderAPI.setPlaceholders(subject, messageTemplate);
    receiver.sendRichMessage(resolvedMessage, resolvers);
  }

  public void sendActionbar(Player receiver, TagResolver... resolvers) {
    String resolvedMessage = PlaceholderAPI.setPlaceholders(receiver, messageTemplate);
    receiver.sendActionBar(MiniMessage.miniMessage().deserialize(resolvedMessage, resolvers));
  }
}
