package projects.vaniajanuar.timeline.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import projects.vaniajanuar.timeline.data.TimelineContract.EventEntry;

/**
 * Created by vania on 6/10/16.
 */
public class TimelineDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "timeline.db";

    public TimelineDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    interface Tables {
        String EVENTS = "events";
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

        sqLiteDatabase.execSQL(SQL_CREATE_EVENTS_TABLE);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("CREATE TEMPORARY TABLE events_backup" + " (" +
                EventEntry._ID + " INTEGER PRIMARY KEY," +
                EventEntry.COLUMN_EVENT_NAME + " TEXT NOT NULL, " +
                EventEntry.COLUMN_EVENT_LOCATION + " TEXT, " +
                EventEntry.COLUMN_EVENT_DESCRIPTION + " TEXT, " +
                EventEntry.COLUMN_EVENT_START_DATE + " INTEGER NOT NULL, " +
                EventEntry.COLUMN_EVENT_END_DATE + " INTEGER " +
                " );");

        sqLiteDatabase.execSQL("INSERT INTO events_backup SELECT" +
                EventEntry._ID + " INTEGER PRIMARY KEY," +
                EventEntry.COLUMN_EVENT_NAME + " TEXT NOT NULL," +
                EventEntry.COLUMN_EVENT_LOCATION + " TEXT, " +
                EventEntry.COLUMN_EVENT_DESCRIPTION + " TEXT, " +
                EventEntry.COLUMN_EVENT_START_DATE + " INTEGER NOT NULL, " +
                EventEntry.COLUMN_EVENT_END_DATE + " INTEGER " +
                "FROM " + Tables.EVENTS);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tables.EVENTS);
        onCreate(sqLiteDatabase);
    }
}
