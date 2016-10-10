package projects.vaniajanuar.timeline;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import projects.vaniajanuar.timeline.data.TimelineContract;
import projects.vaniajanuar.timeline.utils.DateUtils;


public class EventFragment extends Fragment implements View.OnClickListener {
    public EventFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_insert, container, false);

        TextView tvStartDate = (TextView) rootView.findViewById(R.id.event_form_field_start_date);
        TextView tvEndDate = (TextView) rootView.findViewById(R.id.event_form_field_end_date);
        tvStartDate.setOnClickListener(this);
        tvEndDate.setOnClickListener(this);

        Button addEventButton = (Button) rootView.findViewById(R.id.event_form_btn);
        addEventButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.event_form_btn:
                addNewEvent(view);
                break;
            case R.id.event_form_field_start_date:
                showDatePickerDialog(view);
                break;
            case R.id.event_form_field_end_date:
                showDatePickerDialog(view);
                break;
        }
    }

    public void addNewEvent(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TimelineContract.EventEntry.COLUMN_EVENT_NAME,
                ((EditText) getActivity().findViewById(R.id.event_form_field_name)).getText().toString());

        contentValues.put(TimelineContract.EventEntry.COLUMN_EVENT_LOCATION,
                ((EditText) getActivity().findViewById(R.id.event_form_field_location)).getText().toString());

        contentValues.put(TimelineContract.EventEntry.COLUMN_EVENT_DESCRIPTION,
                ((EditText) getActivity().findViewById(R.id.event_form_field_description)).getText().toString());

        contentValues.put(TimelineContract.EventEntry.COLUMN_EVENT_START_DATE,
                DateUtils.convertDate(((TextView) getActivity().findViewById(R.id.event_form_field_start_date))
                        .getText().toString()));

        contentValues.put(TimelineContract.EventEntry.COLUMN_EVENT_END_DATE,
                DateUtils.convertDate(((TextView) getActivity().findViewById(R.id.event_form_field_end_date))
                        .getText().toString()));

        Uri uri = getActivity().getContentResolver().insert(
                TimelineContract.EventEntry.CONTENT_URI, contentValues);


    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();

        // To specify which TextView to update
        Bundle args = new Bundle();
        args.putInt("view_id", view.getId());

        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

}
