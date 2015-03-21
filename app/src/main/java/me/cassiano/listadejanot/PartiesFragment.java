package me.cassiano.listadejanot;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import me.cassiano.listadejanot.adapters.LJListViewAdapter;
import me.cassiano.listadejanot.models.Party;

/**
 * Created by matheus on 3/21/15.
 */
public class PartiesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle(getString(R.string.fragment_parties_title));

        View view = inflater.inflate(R.layout.fragment_parties, container, false);
        List<Party> parties = loadPartiesFromJson();

        ListView lv = (ListView) view.findViewById(R.id.parties);
        LJListViewAdapter adapter = new LJListViewAdapter(getActivity(), parties);
        adapter.sortByNumberOfPoliticians(false);
        lv.setAdapter(adapter);

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

        List<Party> parties = gson.fromJson(reader, new TypeToken<List<Party>>() {}.getType());
        return parties;

    }

}