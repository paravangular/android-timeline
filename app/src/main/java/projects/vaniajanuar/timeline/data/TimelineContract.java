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

    public static final String PATH_EVENTS = "events";
    public static final String PATH_TRACKS = "tracks";

    public interface EventColumns {
        String COLUMN_EVENT_NAME = "event_name";
        String COLUMN_EVENT_LOCATION = "event_location";
        String COLUMN_EVENT_DESCRIPTION = "event_description";
        String COLUMN_EVENT_START_DATE = "event_start_date";
        String COLUMN_EVENT_END_DATE = "event_end_date";
    }

    interface TrackColumns {
        String COLUMN_TRACK_NAME = "track_name";
        String COLUMN_TRACK_DESCRIPTION = "track_description";
        String COLUMN_TRACK_COLOR = "track_color";
    }

    public static final class EventEntry implements BaseColumns, EventColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENTS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTS;


        public static Uri buildEventUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getEventId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

    public static final class TrackEntry implements BaseColumns, TrackColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TRACKS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTS;


        public static Uri buildTrackUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getTrackId(Uri uri) {
            return uri.getPathSegments().get(1);
        }


    }
}
