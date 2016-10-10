package projects.vaniajanuar.timeline;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import projects.vaniajanuar.timeline.data.TimelineContract;
import projects.vaniajanuar.timeline.utils.DateUtils;

/**
 * Created by vania on 7/10/16.
 */
public class TimelineAdapter extends CursorAdapter {

    public static class ViewHolder {
        public final TextView tvEventStartDate;
        public final TextView tvEventName;
        public final TextView tvEventLocation;

        public ViewHolder(View view) {
            tvEventStartDate = (TextView) view.findViewById(R.id.list_item_event_start_date);
            tvEventName = (TextView) view.findViewById(R.id.list_item_event_name);
            tvEventLocation = (TextView) view.findViewById(R.id.list_item_event_location);
        }
    }

    public TimelineAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_event, parent, false);


        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String eventStartDate = DateUtils.getDateString(Long.parseLong(
                cursor.getString(cursor.getColumnIndexOrThrow(TimelineContract.EventEntry.COLUMN_EVENT_START_DATE))));
        String eventName = cursor.getString(cursor.getColumnIndexOrThrow(TimelineContract.EventEntry.COLUMN_EVENT_NAME));
        String eventLocation = cursor.getString(cursor.getColumnIndexOrThrow(TimelineContract.EventEntry.COLUMN_EVENT_LOCATION));

        viewHolder.tvEventStartDate.setText(eventStartDate);
        viewHolder.tvEventName.setText(eventName);
        viewHolder.tvEventLocation.setText(eventLocation);
    }
}