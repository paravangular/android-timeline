package projects.vaniajanuar.timeline;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import projects.vaniajanuar.timeline.data.TimelineContract;
import projects.vaniajanuar.timeline.utils.DateUtils;

/**
 * Created by vania on 11/10/16.
 */
public class AddTrackFragment extends DialogFragment {

    public static AddTrackFragment newInstance() {
        AddTrackFragment addTrackFragment = new AddTrackFragment();

        return addTrackFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        setStyle(style, theme);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_track_dialog, container, false);

        Button addTrackButton = (Button) rootView.findViewById(R.id.track_form_btn);
        addTrackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewTrack(rootView);
                dismiss(); // TODO: check if record is added
            }
        });

        return rootView;
    }

    public void addNewTrack(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TimelineContract.TrackEntry.COLUMN_TRACK_NAME,
                ((EditText) view.findViewById(R.id.track_form_field_name)).getText().toString());

        Uri uri = getActivity().getContentResolver().insert(
                TimelineContract.TrackEntry.CONTENT_URI, contentValues);
    }

}
