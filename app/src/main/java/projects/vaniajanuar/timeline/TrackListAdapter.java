package projects.vaniajanuar.timeline;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import projects.vaniajanuar.timeline.data.TimelineContract;

/**
 * Created by vania on 8/10/16.
 */
public class TrackListAdapter extends CursorAdapter {

    public TrackListAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    public static class ViewHolder {
        public final TextView tvTrackName;

        public ViewHolder(View view) {
            tvTrackName = (TextView) view.findViewById(R.id.list_item_track_name);
        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_track, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // http://stackoverflow.com/questions/27770016/how-to-remove-all-but-the-checked-item-from-a-cursorloader
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String trackName = cursor.getString(cursor.getColumnIndexOrThrow(TimelineContract.EventEntry.COLUMN_EVENT_NAME)); // TODO TimelineContract.TrackEntry.COLUMN_TRACK_NAME));
        viewHolder.tvTrackName.setText(trackName);

    }
}
