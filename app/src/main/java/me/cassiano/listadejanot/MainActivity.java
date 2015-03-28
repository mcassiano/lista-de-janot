package me.cassiano.listadejanot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends BaseActivity {

    private final String ACTIVE_FRAGMENT_POSITION = "activeFragmentPosition";

    private DrawerLayout drawer;
    private ListView drawerMenu;

    private FragmentEnum activeFragment;

    private enum FragmentEnum {

        Parties(0, PartiesFragment.TAG),
        Tweets(1, TweetsFragment.TAG),
        About(2, AboutFragment.TAG);

        int fragmentId;
        String fragmentTag;

        FragmentEnum(int id, String tag) {
            fragmentId = id;
            fragmentTag = tag;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.drawable.ic_ab_drawer);

        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

        String[] menuItems = getResources().getStringArray(R.array.menu_items);

        drawerMenu = (ListView) drawer.findViewById(R.id.drawerMenu);
        drawerMenu.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, menuItems));

        drawerMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        if (savedInstanceState == null) {
            activeFragment = FragmentEnum.Parties;
        }

        else {
            activeFragment = FragmentEnum.values()
                    [savedInstanceState.getInt(ACTIVE_FRAGMENT_POSITION)];
        }

        selectItem(activeFragment.fragmentId);

    }

    private void selectItem(int position) {

        FragmentEnum whatFragment = FragmentEnum.values()[position];
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(whatFragment.fragmentTag);

        if (fragment == null) {

            switch (whatFragment) {
                case Parties:
                    fragment = new PartiesFragment();
                    break;

                case Tweets:
                    fragment = new TweetsFragment();
                    break;

                case About:
                    fragment = new AboutFragment();
                    break;
            }

            fragmentManager.beginTransaction().
                    replace(R.id.content_frame, fragment, whatFragment.fragmentTag).commit();

        }


        activeFragment = whatFragment;

        drawerMenu.setItemChecked(position, true);
        drawer.closeDrawer(drawerMenu);


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(Gravity.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ACTIVE_FRAGMENT_POSITION, activeFragment.fragmentId);
        super.onSaveInstanceState(outState);
    }


}
