package me.cassiano.listadejanot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.cassiano.listadejanot.models.Politician;


public class PoliticianDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "politician";


    private Politician politician;


    public PoliticianDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            politician = getArguments().getParcelable(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_politician_detail, container, false);


        if (politician != null) {

            int imageId =
                    getActivity().getResources().getIdentifier(
                            "drawable/" + politician.getPicture(), "drawable", getActivity().getPackageName());

            ((ImageView) rootView.findViewById(R.id.picture)).setImageResource(imageId);

            ((TextView) rootView.findViewById(R.id.label_politician_name)).
                    setText(politician.getName());

            ((TextView) rootView.findViewById(R.id.label_politician_party_detail)).
                    setText(String.format(
                            getString(R.string.politician_line2), politician.getPosition(),
                            politician.getState()));

            ((TextView) rootView.findViewById(R.id.label_politician_status)).
                    setText(String.format(
                            getString(R.string.politician_line3), politician.getStatus()));

            ((TextView) rootView.findViewById(R.id.label_politician_suspicion)).
                    setText(politician.getSuspicion());

            ((TextView) rootView.findViewById(R.id.label_politician_defense)).
                    setText(politician.getDefense());

        }

        return rootView;
    }
}
