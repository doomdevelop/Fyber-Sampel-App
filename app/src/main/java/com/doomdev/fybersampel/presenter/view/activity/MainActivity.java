package com.doomdev.fybersampel.presenter.view.activity;

import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.doomdev.fybersampel.R;
import com.doomdev.fybersampel.presenter.model.OfferModel;
import com.doomdev.fybersampel.presenter.util.EspressoIdlingResource;
import com.doomdev.fybersampel.presenter.view.fragment.FyberConnectionFragment;
import com.doomdev.fybersampel.presenter.view.fragment.OfferListFragment;
import com.doomdev.fybersampel.presenter.view.fragment.item.OfferItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FyberConnectionFragment.OnFragmentInteractionListener, OfferListFragment.OnListFragmentInteractionListener  {

    private android.support.v4.app.Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_main);
        configureImageLoader();
        if(savedInstanceState == null){
            changeFragment(FyberConnectionFragment.instantiate(this, FyberConnectionFragment.class.getName()),FyberConnectionFragment.class.getName());
        }
    }

    private void configureImageLoader(){
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config); // Do it on Application start
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void changeFragment(android.support.v4.app.Fragment fragment, String name) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, name);
        if(!(fragment instanceof FyberConnectionFragment)) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void onLoadOfferListFragment(List<OfferModel> offerModelList) {
        changeFragment(OfferListFragment.newInstance((ArrayList) offerModelList), OfferListFragment.class.getSimpleName());
    }

    @Override
    public void onListFragmentInteraction(OfferItem item) {

    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
