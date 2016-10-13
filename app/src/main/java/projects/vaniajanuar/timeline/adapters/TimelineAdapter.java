package projects.vaniajanuar.timeline.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import projects.vaniajanuar.timeline.R;
import projects.vaniajanuar.timeline.data.TimelineContract;
import projects.vaniajanuar.timeline.utils.DateUtils;

/**
 * Created by vania on 7/10/16.
 */
public class TimelineAdapter extends RecyclerViewCursorAdapter<TimelineAdapter.TimelineViewHolder> {

    public class TimelineViewHolder extends RecyclerViewCursorViewHolder {
        public final TextView tvEventStartDate;
        public final TextView tvEventName;
        public final TextView tvEventLocation;

        public TimelineViewHolder(View view) {
            super(view);
            tvEventStartDate = (TextView) view.findViewById(R.id.list_item_event_start_date);
            tvEventName = (TextView) view.findViewById(R.id.list_item_event_name);
            tvEventLocation = (TextView) view.findViewById(R.id.list_item_event_location);
        }

        @Override
        public void bindCursor(Cursor cursor) {

            String eventStartDate = DateUtils.getDateString(Long.parseLong(
                    cursor.getString(cursor.getColumnIndexOrThrow(TimelineContract.EventEntry.COLUMN_EVENT_START_DATE))));
            String eventName = cursor.getString(cursor.getColumnIndexOrThrow(TimelineContract.EventEntry.COLUMN_EVENT_NAME));
            String eventLocation = cursor.getString(cursor.getColumnIndexOrThrow(TimelineContract.EventEntry.COLUMN_EVENT_LOCATION));

            tvEventStartDate.setText(eventStartDate);
            tvEventName.setText(eventName);
            tvEventLocation.setText(eventLocation);
        }
    }

    public TimelineAdapter(Context context) {
        super(context);

        setupCursorAdapter(null, 0, R.layout.list_item_event, false);
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TimelineViewHolder(mCursorAdapter.newView(mContext, mCursorAdapter.getCursor(), parent));
    }

    @Override
    public void onBindViewHolder(TimelineViewHolder holder, int position) {
        mCursorAdapter.getCursor().moveToPosition(position);
        setViewHolder(holder);
        mCursorAdapter.bindView(null, mContext, mCursorAdapter.getCursor());
    }

}