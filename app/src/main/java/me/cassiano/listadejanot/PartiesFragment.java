package me.cassiano.listadejanot;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import me.cassiano.listadejanot.adapters.PartyListViewAdapter;
import me.cassiano.listadejanot.models.Party;
import me.cassiano.listadejanot.models.Politician;

/**
 * Created by matheus on 3/21/15.
 */
public class PartiesFragment extends Fragment {

    public static final String TAG = "PartiesFragment";
    private final String PARTIES_DATA_KEY = "parties";

    ArrayList<Party> parties;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle(getString(R.string.fragment_parties_title));

        View view = inflater.inflate(R.layout.fragment_parties, container, false);

        if (savedInstanceState == null)
            parties = (ArrayList<Party>) loadPartiesFromJson();
        else
            parties = savedInstanceState.getParcelableArrayList(PARTIES_DATA_KEY);

        ListView lv = (ListView) view.findViewById(R.id.parties);
        PartyListViewAdapter adapter = new PartyListViewAdapter( parties);
        adapter.sortByNumberOfPoliticians(false);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Party party = (Party) parent.getAdapter().getItem(position);
                ArrayList<Politician> pols = (ArrayList<Politician>) party.getPoliticians();

                Intent intent = new Intent(getActivity(), PoliticianListActivity.class);
                intent.putParcelableArrayListExtra(PoliticianListActivity.EXTRA_LIST_ARRAY,
                        pols);

                getActivity().startActivity(intent);
            }
        });

        return view;

    }

    private List<Party> loadPartiesFromJson() {
        AssetManager assetManager = getActivity().getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("lista-janot.json");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        Reader reader = new InputStreamReader(inputStream);

        Gson gson = new Gson();

        return gson.fromJson(reader, new TypeToken<List<Party>>() {}.getType());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(PARTIES_DATA_KEY, parties);
    }


}
