package jmc.com.poc_spotify.Utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by jogan1075 on 28-07-17.
 */


public class FragmentView {

    private WeakReference<View> viewRef;
    private WeakReference<Fragment> fragmentRef;
    private Context context;
    private Fragment fragment;

    public FragmentView(Fragment fragment, View view, Context context) {
        viewRef = new WeakReference<>(view);
        fragmentRef = new WeakReference<>(fragment);
        this.context = context;
        this.fragment = fragment;
    }

    @Nullable
    public Fragment getFragment() {
        return fragmentRef.get();
    }

    @Nullable
    public View getView() {
        return viewRef.get();
    }

    @Nullable
    public Context getContext() {
        if (null != getFragment()) {
            return getFragment().getActivity();
        } else {
            return null;
        }
    }

    @Nullable
    public FragmentManager getFragmentManager() {
        Fragment fragment = getFragment();
        return (fragment != null) ? fragment.getFragmentManager() : null;
    }
}
