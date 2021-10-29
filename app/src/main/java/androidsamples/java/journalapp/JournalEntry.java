package androidsamples.java.journalapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "journal_table")
public class JournalEntry {
  @PrimaryKey
  @ColumnInfo(name = "id")
  @NonNull
  private UUID mUid;

  @ColumnInfo(name = "title")
  private String mTitle;

  @ColumnInfo(name = "duration")
  private int mDuration;

  public JournalEntry(@NonNull String title, int duration) {
    mUid = UUID.randomUUID();
    mTitle = title;
    mDuration = duration;
  }

  @NonNull
  public UUID getUid() {
    return mUid;
  }

  public void setUid(@NonNull UUID id) {
    mUid = id;
  }

  @NonNull
  public String title() {
    return mTitle;
  }

  public void setTitle(String title) {
    mTitle = title;
  }

  public int duration() {
    return mDuration;
  }

  public void setDuration(int duration) {
    mDuration = duration;
  }
}
