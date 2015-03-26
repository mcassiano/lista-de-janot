package me.cassiano.listadejanot;

import android.os.Bundle;
import android.view.MenuItem;

import me.cassiano.listadejanot.models.Politician;


/**
 * An activity representing a single Politician detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link PoliticianListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link PoliticianDetailFragment}.
 */
public class PoliticianDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            Politician politician =
                    getIntent().getParcelableExtra(PoliticianDetailFragment.ARG_ITEM_ID);

            setTitle(politician.getName());

            Bundle arguments = new Bundle();
            arguments.putParcelable(PoliticianDetailFragment.ARG_ITEM_ID, politician);

            PoliticianDetailFragment fragment = new PoliticianDetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.politician_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_politician_detail;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            finish();
            //navigateUpTo(new Intent(this, PoliticianListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
