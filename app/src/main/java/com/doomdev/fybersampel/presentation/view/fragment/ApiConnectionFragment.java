package com.doomdev.fybersampel.presentation.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doomdev.fybersampel.R;
import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.presentation.presenter.FyberConnectionPresenter;

import java.util.Map;
import java.util.TreeMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ApiConnectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ApiConnectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApiConnectionFragment extends Fragment implements FyberConnectionPresenter.View {

    private OnFragmentInteractionListener mListener;
    private FyberConnectionPresenter presenter;
    private String devId = null;

    public ApiConnectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ApiConnectionFragment.
     */
    public static ApiConnectionFragment newInstance() {
        ApiConnectionFragment fragment = new ApiConnectionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.presenter = new FyberConnectionPresenter(this);
        this.presenter.loadAdvertisingIdentifier(getContext());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_api_connection, container, false);
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to callFyberApi themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callFyberApi(getParamsMap());
            }
        });
    }

    private Map<String, String> getParamsMap() {

        Map<String, String> map = new TreeMap<>();
        map.put("format", "json");
        map.put("appid", "2070");
        map.put("uid", "spiderman");
        map.put("locale", "DE");
        map.put("ip", "109.235.143.113");
        if (devId != null) {
            map.put("device_id", devId);
        }
//        map.put("API Key", "1c915e3b5d42d05136185030892fbb846c278927");
        return map;

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onOffersLoaded(FyberResponse offerList) {
        Log.d("MY APP", " onAdvertisingIdentifierLoaded(): " + offerList.toString());

    }

    @Override
    public void onAdvertisingIdentifierLoaded(String id) {
        this.devId = id;
        Log.d("MY APP", " onAdvertisingIdentifierLoaded() " + devId);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onNoInternetConnection() {

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
