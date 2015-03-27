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

    private DrawerLayout drawer;
    private ListView drawerMenu;

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

        selectItem(0);

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        // TODO: Criar enum ou outro meio de indentificar fragments sem ser hardcoded.

        switch (position) {
            case 0:
                fragment = new PartiesFragment();
                break;

            case 1:
                fragment = new TweetsFragment();
                break;

            case 2:
                fragment = new AboutFragment();
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().
                replace(R.id.content_frame, fragment).commit();

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
}
