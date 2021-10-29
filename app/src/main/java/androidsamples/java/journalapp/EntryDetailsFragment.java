package androidsamples.java.journalapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.UUID;

public class EntryDetailsFragment extends Fragment {
  private static final String TAG = "EntryDetailsFragment";
  private EditText mEditTitle, mEditDuration;
  private EntryDetailsViewModel mEntryDetailsViewModel;
  private JournalEntry mEntry;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    mEntryDetailsViewModel = new ViewModelProvider(getActivity()).get(EntryDetailsViewModel.class);

    UUID entryId = (UUID) getArguments().getSerializable(MainActivity.KEY_ENTRY_ID);
    Log.d(TAG, "Loading entry: " + entryId);

    mEntryDetailsViewModel.loadEntry(entryId);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.journal_entry_detail, container, false);
    mEditTitle = view.findViewById(R.id.edit_title);
    mEditDuration = view.findViewById(R.id.edit_duration);

    view.findViewById(R.id.btn_save).setOnClickListener(this::saveEntry);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mEntryDetailsViewModel.getEntryLiveData().observe(getActivity(),
        entry -> {
          this.mEntry = entry;
          if (entry != null) updateUI();
        });
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.fragment_entry_detail, menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.menu_delete_entry) {
      Log.d(TAG, "Delete button clicked");
    } else if (item.getItemId() == R.id.menu_share_entry) {
      Log.d(TAG, "Share button clicked");
    }
    return super.onOptionsItemSelected(item);
  }

  private void updateUI() {
    mEditTitle.setText(mEntry.title());
    mEditDuration.setText(Integer.toString(mEntry.duration()));
  }

  private void saveEntry(View v) {
    Log.d(TAG, "Save button clicked");
    mEntry.setTitle(mEditTitle.getText().toString());
    mEntry.setDuration(Integer.parseInt(mEditDuration.getText().toString()));
    mEntryDetailsViewModel.saveEntry(mEntry);

    getActivity().onBackPressed();
  }
}
