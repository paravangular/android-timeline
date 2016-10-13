package projects.vaniajanuar.timeline;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import projects.vaniajanuar.timeline.data.TimelineContract;

public class TimelineActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final int TRACK_LOADER = 1;

    private Toolbar toolbar;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private LinearLayout mDrawerLinear;
    private ActionBarDrawerToggle mDrawerToggle;

    private TrackListAdapter mTracksAdapter;

    private FloatingActionMenu mFaMenu;
    private FloatingActionButton mFabAddEvent, mFabAddTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_timeline_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.setTitle("Timeline");
            setSupportActionBar(toolbar);
        }

        initDrawer();
        addDrawerItems();
        initFab();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.timeline_container, new TimelineFragment())
                    .commit();
        }


    }

    private void initDrawer() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mDrawerLinear = (LinearLayout) findViewById(R.id.main_drawer_container);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.timeline_drawer_open,
                R.string.timeline_drawer_close
        ) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void addDrawerItems() {
        mTracksAdapter = new TrackListAdapter(this, null, 0);
        mDrawerList = (ListView) findViewById(R.id.drawer_track_list_view);
        mDrawerList.setAdapter(mTracksAdapter);

        getLoaderManager().initLoader(TRACK_LOADER, null, this);

        final TextView tvAddTrack = (TextView) findViewById(R.id.drawer_button_add_track);
        tvAddTrack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showAddTrackDialog();
            }
        });
    }

    private void initFab() {

        mFaMenu = (FloatingActionMenu) findViewById(R.id.fa_menu);
        mFabAddEvent = (FloatingActionButton) findViewById(R.id.fa_menu_item_add_event);
        mFabAddTrack = (FloatingActionButton) findViewById(R.id.fa_menu_item_add_track);

        mFabAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EventActivity.class));
            }
        });

        mFabAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), EventActivity.class));
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loader, Bundle bundle) {
        CursorLoader returnCursor;
        switch (loader) {

            case TRACK_LOADER:
                returnCursor = new CursorLoader(
                        this,
                        TimelineContract.TrackEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                );
                break;
            default:
                returnCursor = null;

        }

        return returnCursor;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {

        switch (cursorLoader.getId()) {
            case TRACK_LOADER:
                mTracksAdapter.swapCursor(cursor);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mTracksAdapter.swapCursor(null);
    }

    void showAddTrackDialog() {
        DialogFragment newFragment = AddTrackFragment.newInstance();
        newFragment.show(getFragmentManager(), "dialog");
    }

}

