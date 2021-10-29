package androidsamples.java.journalapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.UUID;

@Dao
public interface JournalEntryDao {
  @Insert
  void insert(JournalEntry entry);

  @Query("SELECT * from journal_table ORDER BY title ASC")
  LiveData<List<JournalEntry>> getAllEntries();

  @Query("SELECT * from journal_table WHERE id=(:id)")
  LiveData<JournalEntry> getEntry(UUID id);

  @Update
  void update(JournalEntry entry);
}
