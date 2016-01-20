package com.doomdev.fybersampel.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.doomdev.fybersampel.R;
import com.doomdev.fybersampel.presentation.handler.BufferHandler;
import com.doomdev.fybersampel.presentation.model.OfferModel;
import com.doomdev.fybersampel.presentation.presenter.FyberConnectionPresenter;


import com.doomdev.fybersampel.presentation.util.FyberParameterDemoHelper;
import com.doomdev.fybersampel.presentation.util.Params;
import com.doomdev.fybersampel.presentation.view.Msg;

import java.util.List;

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
    @Bind(R.id.layout_progres_bar_api_call)
    protected FrameLayout layoutProgress;
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
        this.mPresenter = new FyberConnectionPresenter(new FyberConnectionHandler(this,Looper.getMainLooper()));
        this.mPresenter.loadAdvertisingIdentifier(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fyber_connection, container, false);
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
    protected void callFyberApi() {
        layoutProgress.setVisibility(View.VISIBLE);
        mTextViewErrorMsg.setVisibility(View.GONE);
        mPresenter.callFyberApi(fyberParameterDemoHelper.prepareAndGetParams(), API_KEY);
    }

    @OnLongClick(R.id.btn_connect)
    protected boolean loadSampelParameterInFields() {
        mEditTextIp.setText(Params.IP.getValue());
        mEditTextLocale.setText(Params.LOCALE.getValue());
        mEditTextUid.setText(Params.UID.getValue());
        mEditTextAppid.setText(Params.APPID.getValue());
        getView().requestLayout();
        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        Log.d(TAG, " onDetach()... ");
        super.onDetach();
        mListener = null;

    }
    @Override
    public void onResume() {
        Log.d(TAG, " onResume()... ");
        super.onResume();
        mPresenter.resume();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    public void onPause() {
        Log.d(TAG, " onPause()... ");
        super.onPause();
        mPresenter.pause();

    }


    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {
        Log.d(TAG, " onDestroy()... ");
        super.onDestroy();
        this.mPresenter.destroy();
//        this.mHandler.unbind();
    }

    @Override
    public void onOffersLoaded(List<OfferModel> offerModelList) {
        Log.d(TAG, " onOffersLoaded()... ");
        if (offerModelList == null) {
            Toast.makeText(getContext(), getString(R.string.no_offers_msg), Toast.LENGTH_LONG).show();
        } else if (mListener != null) {
            mListener.onLoadOfferListFragment(offerModelList);
        }

    }

    @Override
    public void onAdvertisingIdentifierLoaded(String id) {
        Log.d(TAG, " onAdvertisingIdentifierLoaded() " + id);
        fyberParameterDemoHelper.setDeviceId(id);

    }

    @Override
    public void hideProgress() {
        layoutProgress.setVisibility(View.GONE);

    }

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
        void onLoadOfferListFragment(List<OfferModel> offerModelList);
    }

    /**
     * This class deliver messages from presenter.
     *
     */
    public static class FyberConnectionHandler extends BufferHandler{
        FyberConnectionPresenter.View mView;
        FyberConnectionHandler(FyberConnectionPresenter.View view, Looper looper){
            super(looper);
            this.mView = view;

        }

        @Override
        public void destroy(){
            super.destroy();
            this.mView = null;
            Log.d(TAG, " Handler destroy()..");
        }

        @Override
        protected void processMessage(Message msg) {
        if(mView == null){
            Log.e(TAG, " handler: mView is null, return");
            return;
        }
            switch (msg.what){
                case Msg.ON_OFFERS_LOADED:
                    mView.onOffersLoaded((List<OfferModel>) msg.obj);
                    Log.d(TAG, " handleMessage()... ");
                    break;
                case Msg.ON_ADVERTISING_IDENTIFIER_LOADED:
                    mView.onAdvertisingIdentifierLoaded((String)msg.obj);
                    break;
                case Msg.HIDE_PROGRESS:
                    mView.hideProgress();
                    break;
                case Msg.ON_ERROR:
                    mView.onError((String) msg.obj);
                    break;
            }
        }
    }
}
