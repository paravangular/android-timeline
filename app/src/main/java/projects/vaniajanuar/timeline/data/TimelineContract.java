package projects.vaniajanuar.timeline.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by vania on 6/10/16.
 */
public class TimelineContract {

    public static final String CONTENT_AUTHORITY = "projects.vaniajanuar.timeline";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_EVENT = "event";

    public static final class EventEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENT;

        public static final String COLUMN_EVENT_NAME = "event_name";
        public static final String COLUMN_EVENT_LOCATION = "event_location";
        public static final String COLUMN_EVENT_DESCRIPTION = "event_description";
        public static final String COLUMN_EVENT_START_DATE = "event_start_date";
        public static final String COLUMN_EVENT_END_DATE = "event_end_date";

        public static Uri buildEventUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getEventId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
}
