package me.cassiano.listadejanot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import java.util.ArrayList;

import me.cassiano.listadejanot.models.Politician;



public class PoliticianListActivity extends BaseActivity
        implements PoliticianListFragment.Callbacks {


    private boolean mTwoPane;
    public static final String EXTRA_LIST_ARRAY = "politicians";
    private ArrayList<Politician> politicians;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FragmentManager fragmentManager = getSupportFragmentManager();

        PoliticianListFragment plf = (PoliticianListFragment)
                fragmentManager.findFragmentById(R.id.politician_list);

        if (getIntent().hasExtra(EXTRA_LIST_ARRAY)) {
            politicians = getIntent().getParcelableArrayListExtra(EXTRA_LIST_ARRAY);
            if (politicians == null)
                politicians = new ArrayList<>();

            setTitle(getString(R.string.party_detail_activity_title) +
                    " - " + politicians.get(0).getParty());
        }

        if (plf == null) {
            plf = new PoliticianListFragment();

            Bundle args = new Bundle();
            args.putParcelableArrayList(EXTRA_LIST_ARRAY, politicians);
            plf.setArguments(args);

            fragmentManager.beginTransaction().replace(R.id.politician_list, plf).commit();
        }

        if (findViewById(R.id.politician_detail_container) != null) {

            mTwoPane = true;

            //plf.setActivateOnItemClick(true);
        }

    }

    @Override
    public void onItemSelected(Politician politician) {

        if (mTwoPane) {

            // Two pane

            Bundle arguments = new Bundle();
            arguments.putParcelable(PoliticianDetailFragment.ARG_ITEM_ID, politician);

            PoliticianDetailFragment fragment = new PoliticianDetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.politician_detail_container, fragment)
                    .commit();

        } else {

            // Single pane

            Intent detailIntent = new Intent(this, PoliticianDetailActivity.class);
            detailIntent.putExtra(PoliticianDetailFragment.ARG_ITEM_ID, politician);
            startActivity(detailIntent);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_politician_list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            //navigateUpTo(new Intent(this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
