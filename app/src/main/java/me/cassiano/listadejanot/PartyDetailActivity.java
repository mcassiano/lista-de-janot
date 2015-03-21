package me.cassiano.listadejanot;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import me.cassiano.listadejanot.adapters.PoliticiansListViewAdapter;
import me.cassiano.listadejanot.models.Politician;


public class PartyDetailActivity extends BaseActivity {

    List<Politician> politicians;
    public static final String EXTRA_LIST_ARRAY = "politicians";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        politicians = getIntent().getParcelableArrayListExtra(EXTRA_LIST_ARRAY);

        setTitle(getString(R.string.party_detail_activity_title) +
                " - " + politicians.get(0).getParty());

        ListView lv = (ListView) findViewById(R.id.politicians);
        PoliticiansListViewAdapter adapter = new PoliticiansListViewAdapter(this, politicians);
        lv.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_party_detail;
    }



}
