package me.cassiano.listadejanot;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by matheus on 3/21/15.
 */
public class AboutFragment extends Fragment {

    public static final String TAG = "AboutFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle(getString(R.string.fragment_about_title));
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        try {
            PackageInfo pInfo = getActivity().getPackageManager().
                    getPackageInfo(getActivity().getPackageName(), 0);

            TextView appVersion = (TextView) view.findViewById(R.id.app_version);
            TextView appDescription = (TextView) view.findViewById(R.id.app_description);

            appVersion.setText(String.format(getString(R.string.version), pInfo.versionName));
            appDescription.setText(Html.fromHtml(getString(R.string.app_description)));
            appDescription.setMovementMethod(LinkMovementMethod.getInstance());

        } catch (PackageManager.NameNotFoundException e) {
            // do nothing
        }

        return view;

    }
}
