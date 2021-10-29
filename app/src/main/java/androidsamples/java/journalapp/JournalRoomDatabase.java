package androidsamples.java.journalapp;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {JournalEntry.class}, version = 1, exportSchema = false)
@TypeConverters(JournalTypeConverters.class)
public abstract class JournalRoomDatabase extends RoomDatabase {
  /**
   * Generates an instance of the {@link JournalEntryDao}.
   * No need to implement this method, the {@link Room} library takes care of it.
   *
   * @return an instance of the {@code JournalEntryDao}
   */
  public abstract JournalEntryDao journalEntryDao();
}
