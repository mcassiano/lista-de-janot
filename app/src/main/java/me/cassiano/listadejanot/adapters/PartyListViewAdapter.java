package me.cassiano.listadejanot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.cassiano.listadejanot.R;
import me.cassiano.listadejanot.models.Party;

/**
 * Created by matheus on 3/20/15.
 */
public class PartyListViewAdapter extends BaseAdapter {

    private List<Party> data;

    public PartyListViewAdapter(List<Party> data) {
        this.data = new ArrayList<>();

        if (data != null)
            addAll(data);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) parent.getContext().
                            getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.lj_listview_item, parent, false);
        }

        ImageView iv = (ImageView) convertView.findViewById(R.id.icon);
        TextView tv1 = (TextView) convertView.findViewById(R.id.line1);
        TextView tv2 = (TextView) convertView.findViewById(R.id.line2);

        Party party = (Party) getItem(position);

        int imageId =
                parent.getContext().getResources().getIdentifier("drawable/" + party.getPicture(),
                        "drawable", parent.getContext().getPackageName());

        iv.setImageResource(imageId);

        tv1.setText(party.getName());

        String pnText = String.format(parent.getContext().getString(R.string.party_number),
                party.getPoliticians().size());

        tv2.setText(pnText);

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void sortByNumberOfPoliticians(final boolean asc) {
        Collections.sort(data, new Comparator<Party>() {
            @Override
            public int compare(Party lhs, Party rhs) {
                int res = lhs.getPoliticians().size() - rhs.getPoliticians().size();

                if (!asc)
                    res = -res;

                return res;
            }
        });
    }

    public void addAll(List<Party> parties) {
            data.addAll(parties);
    }
}
