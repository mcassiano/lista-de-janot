package me.cassiano.listadejanot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ocpsoft.pretty.time.PrettyTime;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import me.cassiano.listadejanot.R;
import twitter4j.Status;

/**
 * Created by matheus on 3/20/15.
 */
public class TweetsListViewAdapter extends BaseAdapter {

    private List<Status> data;
    private PrettyTime formatter;

    public TweetsListViewAdapter(List<Status> tweets) {

        this.data = new ArrayList<>();
        this.formatter = new PrettyTime();

        if (tweets != null)
            addTweets(tweets);

    }

    static class TweetViewHolder {
        TextView username;
        TextView screenName;
        TextView text;
        TextView date;
        ImageView avatar;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TweetViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.tweet_row, parent, false);

            holder = new TweetViewHolder();

            holder.avatar = (ImageView) convertView.findViewById(R.id.tweet_avatar);
            holder.username = (TextView) convertView.findViewById(R.id.tweet_user_name);
            holder.screenName = (TextView) convertView.findViewById(R.id.tweet_user_screename);
            holder.text = (TextView) convertView.findViewById(R.id.tweet_text);
            holder.date = (TextView) convertView.findViewById(R.id.tweet_date);

            convertView.setTag(holder);
        }

        else {
            holder = (TweetViewHolder) convertView.getTag();
        }

        Status status = (Status) getItem(position);

        holder.username.setText(status.getUser().getName());
        holder.screenName.setText(status.getUser().getScreenName());
        holder.text.setText(status.getText());
        holder.date.setText(formatter.format(status.getCreatedAt()));

        Picasso.with(parent.getContext()).
                load(status.getUser().getBiggerProfileImageURL()).into(holder.avatar);

        return convertView;
    }

    public void addTweets(List<Status> tweets) {
        data.clear();

        for (Status status : tweets)
            data.add(status);

        notifyDataSetChanged();
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
