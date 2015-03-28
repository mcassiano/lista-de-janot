package me.cassiano.listadejanot;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import me.cassiano.listadejanot.adapters.TweetsListViewAdapter;
import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterMethod;


/**
 * Created by matheus on 3/21/15.
 */
public class TweetsFragment extends ListFragment {

    public static final String TAG = "TweetsFragment";

    private ListFragmentSwipeRefreshLayout refreshLayout;
    AsyncTwitter twitter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle(getString(R.string.fragment_tweets_title));

        View view = super.onCreateView(inflater, container, savedInstanceState);


        refreshLayout = new ListFragmentSwipeRefreshLayout(container.getContext());
        refreshLayout.addView(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        refreshLayout.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );


        return refreshLayout;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (twitter == null) {

            AsyncTwitterFactory factory = new AsyncTwitterFactory(
                    TwitterConfig.getConfigurationBuilder().build());

            twitter = factory.getInstance();

            twitter.addListener(new TwitterAdapter() {
                @Override
                public void searched(final QueryResult queryResult) {

                    // Twitter4J vai rodar isso em um thread separado,
                    // mas se isso acontecer, as views que precisam ser
                    // atualizadas não serão. DOIS dias pra perceber isso.

                    getActivity().runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    List<Status> tweets = queryResult.getTweets();
                                    TweetsFragment.this.tweetsUpdated(tweets);
                                }
                            }
                    );

                }

                @Override
                public void onException(TwitterException te, TwitterMethod method) {
                    super.onException(te, method);

                    // TODO: Fazer detecção de erros e informar usuário

//                    if (te.isCausedByNetworkIssue()) {
//                        UIUtil.makeToast(getActivity().getApplicationContext(),
//                                getString(R.string.error_connectivity_problem));
//                    }
                }
            });
        }

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateTweets();
            }
        });

        updateTweets();

    }

    private class ListFragmentSwipeRefreshLayout extends SwipeRefreshLayout {
        public ListFragmentSwipeRefreshLayout(Context context) {
            super(context);
        }

        @Override
        public boolean canChildScrollUp() {
            final ListView listView = getListView();

            if (listView.getVisibility() == View.VISIBLE)
                return canListViewScrollUp(listView);
            else
                return false;

        }
    }

    private void updateTweets() {
        twitter.search(new Query(getString(R.string.twitter_search_query)));
    }

    private void tweetsUpdated(List<Status> tweets) {

        if (getListAdapter() == null) {
            TweetsListViewAdapter tweetsListViewAdapter = new TweetsListViewAdapter(tweets);
            setListAdapter(tweetsListViewAdapter);
        }

        else {

            ((TweetsListViewAdapter) getListAdapter()).addTweets(tweets);
        }

        refreshLayout.setRefreshing(false);
    }

    private static boolean canListViewScrollUp(ListView listView) {
        if (android.os.Build.VERSION.SDK_INT >= 14) {
            // For ICS and above we can call canScrollVertically() to determine this
            return ViewCompat.canScrollVertically(listView, -1);
        } else {
            // Pre-ICS we need to manually check the first visible item and the child view's top
            // value
            return listView.getChildCount() > 0 &&
                    (listView.getFirstVisiblePosition() > 0
                            || listView.getChildAt(0).getTop() < listView.getPaddingTop());
        }
    }

}
