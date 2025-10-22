package de.mcmdev.vanish.config;

import org.checkerframework.checker.nullness.qual.NonNull;
import space.arim.dazzleconf.LoadResult;
import space.arim.dazzleconf.engine.DeserializeInput;
import space.arim.dazzleconf.engine.SerializeDeserialize;
import space.arim.dazzleconf.engine.SerializeOutput;

final class MessageTypeLiaison implements SerializeDeserialize<Message> {

  @Override
  public @NonNull LoadResult<@NonNull Message> deserialize(@NonNull DeserializeInput deser) {
    return deser.requireString().map(Message::new);
  }

  @Override
  public void serialize(@NonNull Message value, @NonNull SerializeOutput ser) {
    ser.outString(value.messageTemplate());
  }
}
