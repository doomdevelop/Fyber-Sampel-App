package com.doomdev.fybersampel.presentation.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doomdev.fybersampel.R;
import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.data.pojo.Offers;
import com.doomdev.fybersampel.presentation.util.FyberParameterDemoHelper;
import com.doomdev.fybersampel.presentation.presenter.FyberConnectionPresenter;
import com.doomdev.fybersampel.presentation.util.Params;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FyberConnectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FyberConnectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FyberConnectionFragment extends Fragment implements FyberConnectionPresenter.View {

    private OnFragmentInteractionListener mListener;
    private FyberConnectionPresenter mPresenter;
    private FyberParameterDemoHelper fyberParameterDemoHelper;
    private static final String TAG = FyberConnectionFragment.class.getSimpleName();
    public static final String API_KEY = "1c915e3b5d42d05136185030892fbb846c278927";
    public static final String API_KEY_FALSE = "1c915e3b5d42d05136185030892fbb846c27892";

    @Bind(R.id.text_view_main_info)
    protected TextView mTextViewMainInfo;
    @Bind(R.id.text_view_error_msg)
    protected TextView mTextViewErrorMsg;
    @Bind(R.id.edit_text_api_key)
    protected EditText mEditTextApiKey;
    @Bind(R.id.edit_text_uid)
    protected EditText mEditTextUid;
    @Bind(R.id.edit_text_ip)
    protected EditText mEditTextIp;
    @Bind(R.id.edit_text_locale)
    protected EditText mEditTextLocale;
    @Bind(R.id.edit_text_appid)
    protected EditText mEditTextAppid;

    @Bind(R.id.progres_bar_api_call)
    protected ProgressBar mProgressBar;


    public FyberConnectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ApiConnectionFragment.
     */
    public static FyberConnectionFragment newInstance() {
        FyberConnectionFragment fragment = new FyberConnectionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Log.d(TAG, "onCreate()..");
        this.fyberParameterDemoHelper = new FyberParameterDemoHelper();
        this.mPresenter = new FyberConnectionPresenter(this);
        this.mPresenter.loadAdvertisingIdentifier(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fyber_connection, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
    }
    @OnClick(R.id.btn_connect)
    protected void callFyberApi(){
        mProgressBar.setVisibility(View.VISIBLE);
        mPresenter.callFyberApi(fyberParameterDemoHelper.prepareAndGetParams(), API_KEY);
    }
    @OnLongClick(R.id.btn_connect)
    protected boolean loadSampelParameterInFields(){
//        mEditTextApiKey.setText(FyberDemoCall.Params.APPID);
        mEditTextIp.setText(Params.IP.getValue());
        mEditTextLocale.setText(Params.LOCALE.getValue());
        mEditTextUid.setText(Params.UID.getValue());
        mEditTextAppid.setText(Params.APPID.getValue());
        getView().requestLayout();
        return true;
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

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.destroy();
        Log.d(TAG, "onDestroy()..");
    }

    @Override
    public void onOffersLoaded(FyberResponse fyberResponse) {
        Log.d(TAG, " onAdvertisingIdentifierLoaded(): " + fyberResponse.toString());
        Log.d(TAG, " **************************  Offers  **********************");

        Offers[] offers = fyberResponse.getOffers();
        for(Offers offers1 : offers){
            Log.d(TAG, offers1.toString());
        }

    }

    @Override
    public void onAdvertisingIdentifierLoaded(String id) {
        Log.d(TAG, " onAdvertisingIdentifierLoaded() " + id);
        fyberParameterDemoHelper.setDeviceId(id);

    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String message) {
        mTextViewErrorMsg.setVisibility(View.VISIBLE);
        mTextViewErrorMsg.setText(message);
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
