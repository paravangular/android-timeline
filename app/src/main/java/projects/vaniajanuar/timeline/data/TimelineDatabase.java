package projects.vaniajanuar.timeline.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import projects.vaniajanuar.timeline.data.TimelineContract.EventEntry;

import projects.vaniajanuar.timeline.data.TimelineContract.TrackEntry;
/**
 * Created by vania on 6/10/16.
 */
public class TimelineDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    static final String DATABASE_NAME = "timeline.db";
    private Context mContext;

    public TimelineDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    interface Tables {
        String EVENTS = "events";
        String TRACKS = "tracks";
        String TRACKS_EVENTS = "tracks_events";

        String TRACKS_EVENTS_JOIN_TRACKS = "tracks_events "
                + "LEFT OUTER JOIN tracks ON tracks_events.track_id=tracks._id";
    }

    public interface TracksEvents {
        String EVENT_ID = "event_id";
        String TRACK_ID = "track_id";
    }


    private interface References {
        String EVENT_ID = "REFERENCES " + Tables.EVENTS + "(" + EventEntry._ID + ")";
        String TRACK_ID = "REFERENCES " + Tables.TRACKS + "(" + TrackEntry._ID + ")";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_EVENTS_TABLE = "CREATE TABLE " + Tables.EVENTS + " (" +
                EventEntry._ID + " INTEGER PRIMARY KEY," +
                EventEntry.COLUMN_EVENT_NAME + " TEXT NOT NULL, " +
                EventEntry.COLUMN_EVENT_LOCATION + " TEXT, " +
                EventEntry.COLUMN_EVENT_DESCRIPTION + " TEXT, " +
                EventEntry.COLUMN_EVENT_START_DATE + " INTEGER NOT NULL, " +
                EventEntry.COLUMN_EVENT_END_DATE + " INTEGER " +
                " );";

        final String SQL_CREATE_TRACKS_TABLE = "CREATE TABLE " + Tables.TRACKS + " (" +
                TrackEntry._ID + " INTEGER PRIMARY KEY," +
                TrackEntry.COLUMN_TRACK_NAME + " TEXT NOT NULL, " +
                TrackEntry.COLUMN_TRACK_DESCRIPTION + " TEXT, " +
                TrackEntry.COLUMN_TRACK_COLOR + " INTEGER " +
                " );";

        final String SQL_CREATE_TRACKS_EVENTS_TABLE = "CREATE TABLE " + Tables.TRACKS_EVENTS + "( " +
                BaseColumns._ID + " INTEGER PRIMARY KEY," +
                TracksEvents.TRACK_ID + " TEXT NOT NULL " + References.TRACK_ID + "," +
                TracksEvents.EVENT_ID + " TEXT NOT NULL " + References.EVENT_ID + "," +
                "UNIQUE (" + TracksEvents.EVENT_ID + "," + TracksEvents.TRACK_ID +
                ") ON CONFLICT REPLACE)";

        sqLiteDatabase.execSQL(SQL_CREATE_EVENTS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TRACKS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TRACKS_EVENTS_TABLE);

        }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL("CREATE TEMPORARY TABLE events_backup" + " (" +
//                EventEntry._ID + " INTEGER PRIMARY KEY," +
//                EventEntry.COLUMN_EVENT_NAME + " TEXT NOT NULL, " +
//                EventEntry.COLUMN_EVENT_LOCATION + " TEXT, " +
//                EventEntry.COLUMN_EVENT_DESCRIPTION + " TEXT, " +
//                EventEntry.COLUMN_EVENT_START_DATE + " INTEGER NOT NULL, " +
//                EventEntry.COLUMN_EVENT_END_DATE + " INTEGER " +
//                " );");
//
//        sqLiteDatabase.execSQL("INSERT INTO events_backup SELECT" +
//                EventEntry._ID + " INTEGER PRIMARY KEY," +
//                EventEntry.COLUMN_EVENT_NAME + " TEXT NOT NULL," +
//                EventEntry.COLUMN_EVENT_LOCATION + " TEXT, " +
//                EventEntry.COLUMN_EVENT_DESCRIPTION + " TEXT, " +
//                EventEntry.COLUMN_EVENT_START_DATE + " INTEGER NOT NULL, " +
//                EventEntry.COLUMN_EVENT_END_DATE + " INTEGER " +
//                "FROM " + Tables.EVENTS);

        mContext.deleteDatabase("DB_NAME");


        onCreate(sqLiteDatabase);
    }
}
