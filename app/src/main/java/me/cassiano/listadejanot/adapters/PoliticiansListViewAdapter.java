package me.cassiano.listadejanot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.cassiano.listadejanot.R;
import me.cassiano.listadejanot.models.Party;
import me.cassiano.listadejanot.models.Politician;

/**
 * Created by matheus on 3/20/15.
 */
public class PoliticiansListViewAdapter extends BaseAdapter {

    private List<Politician> data;
    private Context context;

    public PoliticiansListViewAdapter(Context context, List<Politician> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.lj_listview_item, parent, false);
        }

        ImageView iv = (ImageView) convertView.findViewById(R.id.icon);
        TextView tv1 = (TextView) convertView.findViewById(R.id.line1);
        TextView tv2 = (TextView) convertView.findViewById(R.id.line2);

        Politician politician = (Politician) getItem(position);

        int imageId =
                context.getResources().
                        getIdentifier(
                                "drawable/" + politician.getPicture(), "drawable", context.getPackageName());

        iv.setImageResource(imageId);

        tv1.setText(politician.getName());

        String pnText = String.format(
                context.getString(R.string.politician_line2),
                politician.getPosition(), politician.getState());

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
}
