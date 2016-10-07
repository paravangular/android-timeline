package projects.vaniajanuar.timeline;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import projects.vaniajanuar.timeline.R;
import projects.vaniajanuar.timeline.data.TimelineContract;

/**
 * Created by vania on 6/10/16.
 */
public class TimelineFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EVENT_LOADER = 0;
    private TimelineAdapter mTimelineAdapter;

    public TimelineFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mTimelineAdapter = new TimelineAdapter(getActivity(), null, 0);

        View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.list_view_event);
        listView.setAdapter(mTimelineAdapter);

        getLoaderManager().initLoader(EVENT_LOADER, null, this);

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(
                getActivity(),
                TimelineContract.EventEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mTimelineAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mTimelineAdapter.swapCursor(null);
    }
}
