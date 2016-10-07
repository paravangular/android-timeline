package projects.vaniajanuar.timeline;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import projects.vaniajanuar.timeline.data.TimelineContract;

/**
 * Created by vania on 7/10/16.
 */
public class TimelineAdapter extends CursorAdapter {

    public TimelineAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_event, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView rvEventName = (TextView) view.findViewById(R.id.list_item_event_name);
        TextView rvEventLocation = (TextView) view.findViewById(R.id.list_item_event_location);

        String eventName = cursor.getString(cursor.getColumnIndexOrThrow(TimelineContract.EventEntry.COLUMN_EVENT_NAME));
        String eventLocation = cursor.getString(cursor.getColumnIndexOrThrow(TimelineContract.EventEntry.COLUMN_EVENT_LOCATION));

        rvEventName.setText(eventName);
        rvEventLocation.setText(eventLocation);
    }
}