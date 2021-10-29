package androidsamples.java.journalapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements EntryListFragment.Callbacks {
  static final String KEY_ENTRY_ID = "KEY_ENTRY_ID";
  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    if (currentFragment == null) {
      Fragment fragment = new EntryListFragment();
      getSupportFragmentManager()
          .beginTransaction()
          .add(R.id.fragment_container, fragment)
          .commit();
    }
  }

  @Override
  public void onEntrySelected(UUID entryId) {
    Log.d(TAG, "Entry selected: " + entryId);

    Bundle args = new Bundle();
    args.putSerializable(KEY_ENTRY_ID, entryId);

    Fragment fragment = new EntryDetailsFragment();
    fragment.setArguments(args);

    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .addToBackStack(null)
        .commit();
  }
}