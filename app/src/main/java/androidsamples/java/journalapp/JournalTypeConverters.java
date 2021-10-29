package androidsamples.java.journalapp;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import java.util.UUID;

public class JournalTypeConverters {
  @TypeConverter
  public UUID toUUID(@NonNull String uuid) {
    return UUID.fromString(uuid);
  }

  @TypeConverter
  public String fromUUID(@NonNull UUID uuid) {
    return uuid.toString();
  }
}
