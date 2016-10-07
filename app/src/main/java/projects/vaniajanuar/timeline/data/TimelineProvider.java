package projects.vaniajanuar.timeline.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by vania on 6/10/16.
 */
public class TimelineProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private TimelineDatabase mOpenHelper;

    static final int EVENTS = 200;
    static final int EVENT_ID = 201;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = TimelineContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, TimelineContract.PATH_EVENT, EVENTS);
        matcher.addURI(authority, TimelineContract.PATH_EVENT + "/#", EVENT_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new TimelineDatabase(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case EVENTS:
                return TimelineContract.EventEntry.CONTENT_TYPE;
            case EVENT_ID:
                return TimelineContract.EventEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unrecognized uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor returnCursor;

        switch (sUriMatcher.match(uri)) {
            case EVENTS:
                returnCursor = mOpenHelper.getReadableDatabase().query(
                        TimelineDatabase.Tables.EVENTS,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case EVENT_ID:
                String eventId = TimelineContract.EventEntry.getEventId(uri);
                returnCursor = mOpenHelper.getReadableDatabase().query(
                        TimelineDatabase.Tables.EVENTS,
                        projection,
                        TimelineContract.EventEntry._ID + " = ?",
                        new String[]{eventId},
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unrecognized uri: " + uri);
        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase sqLiteDatabase = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case EVENTS:
                long _id = sqLiteDatabase.insert(TimelineDatabase.Tables.EVENTS, null, contentValues);
                if (_id > 0)
                    returnUri = TimelineContract.EventEntry.buildEventUri(_id);
                else
                    throw new SQLException("Failed to insert row into " + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unrecognized uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase sqLiteDatabase = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        if ( null == selection ) selection = "1";
        switch (match) {
            case EVENTS:
                rowsDeleted = sqLiteDatabase.delete(TimelineDatabase.Tables.EVENTS,
                        selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unrecognized uri: " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final SQLiteDatabase sqLiteDatabase = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case EVENTS:
                rowsUpdated = sqLiteDatabase.update(TimelineDatabase.Tables.EVENTS, contentValues,
                        selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unrecognized uri: " + uri);
        }

        return rowsUpdated;
    }
}
