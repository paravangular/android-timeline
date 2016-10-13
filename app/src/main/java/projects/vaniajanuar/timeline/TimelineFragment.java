package projects.vaniajanuar.timeline;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import projects.vaniajanuar.timeline.adapters.TimelineAdapter;
import projects.vaniajanuar.timeline.data.TimelineContract;

/**
 * Created by vania on 6/10/16.
 */
public class TimelineFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private final String LOG_TAG = TimelineFragment.class.getSimpleName();

    private static final int EVENT_LOADER = 0;
    private static final int TRACK_LOADER = 1;
    private TimelineAdapter mTimelineAdapter;

    public TimelineFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);
        RecyclerView mTimelineRecyclerView = (RecyclerView) rootView.findViewById(R.id.list_view_event);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTimelineRecyclerView.setLayoutManager(linearLayoutManager);

        mTimelineAdapter = new TimelineAdapter(getActivity());
        mTimelineRecyclerView.setAdapter(mTimelineAdapter);

        getLoaderManager().initLoader(EVENT_LOADER, null, this);

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loader, Bundle bundle) {
        CursorLoader returnCursor;
        switch (loader) {
            case EVENT_LOADER:
                String sortOrder = TimelineContract.EventEntry.COLUMN_EVENT_START_DATE + " ASC";
                returnCursor = new CursorLoader(
                       getActivity(),
                       TimelineContract.EventEntry.CONTENT_URI,
                       null,
                       null,
                       null,
                       sortOrder
                );
                break;
            case TRACK_LOADER:
                returnCursor = new CursorLoader(
                        getActivity(),
                        TimelineContract.TrackEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown loader id: " + loader);
        }

        return returnCursor;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {

        switch (cursorLoader.getId()) {
            case EVENT_LOADER:
                mTimelineAdapter.swapCursor(cursor);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mTimelineAdapter.swapCursor(null);
    }
}
