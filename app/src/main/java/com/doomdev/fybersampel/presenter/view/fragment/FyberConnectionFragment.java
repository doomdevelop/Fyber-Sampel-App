package com.doomdev.fybersampel.presenter.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doomdev.fybersampel.R;
import com.doomdev.fybersampel.presenter.handler.BufferHandler;
import com.doomdev.fybersampel.presenter.model.OfferModel;
import com.doomdev.fybersampel.presenter.presenter.FyberConnectionPresenter;
import com.doomdev.fybersampel.presenter.util.FyberParameterDemoHelper;
import com.doomdev.fybersampel.presenter.util.Params;
import com.doomdev.fybersampel.presenter.view.Msg;

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
    private FyberParameterDemoHelper mFyberParameterDemoHelper;

    private static final String TAG = FyberConnectionFragment.class.getSimpleName();
    public static final String API_KEY = "1c915e3b5d42d05136185030892fbb846c278927";
    public static final String API_KEY_FALSE = "1c915e3b5d42d05136185030892fbb846c27892";


    @Bind(R.id.text_view_error_msg)
    protected TextView mTextViewErrorMsg;
    @Bind(R.id.edit_text_api_key)
    protected EditText mEditTextApiKey;
    @Bind(R.id.edit_text_uid)
    protected EditText mEditTextUid;
    @Bind(R.id.edit_text_pub0)
    protected EditText mEditTextPub0;
    @Bind(R.id.edit_text_appid)
    protected EditText mEditTextAppid;
    @Bind(R.id.layout_progres_bar_api_call)
    protected FrameLayout mLayoutProgress;
    @Bind(R.id.progres_bar_api_call)
    protected ProgressBar mProgressBar;
    @Bind(R.id.btn_connect)
    protected Button mBtnConnect;


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
        this.mFyberParameterDemoHelper = new FyberParameterDemoHelper();
        this.mPresenter = new FyberConnectionPresenter(new FyberConnectionHandler(this, Looper.getMainLooper()));
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
     * This gives subclasses a chance to callGetOffersFyberApi themselves once
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

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditTextUid.getWindowToken(), 0);
//        getView().invalidate();
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d(TAG, "afterTextChanged: " + s.toString());
            if (s.toString() != null) {
                if (checkAllRequiredTextField()) {
                    mBtnConnect.setEnabled(true);
                } else {
                    mBtnConnect.setEnabled(false);
                }
            }
        }
    };

    private void addListenerOnEditText() {
        mEditTextApiKey.addTextChangedListener(mTextWatcher);
        mEditTextAppid.addTextChangedListener(mTextWatcher);
        mEditTextPub0.addTextChangedListener(mTextWatcher);
        mEditTextUid.addTextChangedListener(mTextWatcher);

    }

    private void removeTextChangeedListener() {
        mEditTextApiKey.removeTextChangedListener(mTextWatcher);
        mEditTextAppid.removeTextChangedListener(mTextWatcher);
        mEditTextPub0.removeTextChangedListener(mTextWatcher);
        mEditTextUid.removeTextChangedListener(mTextWatcher);
    }


    private boolean checkAllRequiredTextField() {
        return isNotNullAndHasText(mEditTextApiKey)
                && isNotNullAndHasText(mEditTextAppid)
                && isNotNullAndHasText(mEditTextPub0)
                && isNotNullAndHasText(mEditTextUid);
    }

    private boolean isNotNullAndHasText(EditText editText) {
        return editText.getText() != null && editText.getText().length() > 0;
    }

    @OnClick(R.id.btn_connect)
    protected void callFyberApi() {
        mLayoutProgress.setVisibility(View.VISIBLE);
        mTextViewErrorMsg.setVisibility(View.GONE);
        mPresenter.callGetOffersFyberApi(mFyberParameterDemoHelper.prepareAndGetParams(), API_KEY);
    }

    private void updateParamsMap() {
        mFyberParameterDemoHelper.setParam(Params.FORMAT, Params.FORMAT.getValue());
        mFyberParameterDemoHelper.setParam(Params.APPID, mEditTextAppid.getText().toString());
        mFyberParameterDemoHelper.setParam(Params.UID, mEditTextUid.getText().toString());
        mFyberParameterDemoHelper.setParam(Params.PUB0, mEditTextPub0.getText().toString());
        mFyberParameterDemoHelper.setParam(Params.OFFER_TYPES, Params.OFFER_TYPES.getValue());
        mFyberParameterDemoHelper.setParam(Params.TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000L));
        mFyberParameterDemoHelper.setParam(Params.DEVICE_ID, "");
    }

    @OnLongClick(R.id.edit_text_uid)
    protected boolean loadSampelParameterInFields() {
        //TODO: remove it !
        mEditTextPub0.setText(Params.PUB0.getValue());
        mEditTextUid.setText(Params.UID.getValue());
        mEditTextAppid.setText(Params.APPID.getValue());
        mEditTextApiKey.setText(API_KEY);
        if (getView() != null) {
            getView().requestLayout();
        }
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
        addListenerOnEditText();
        if (checkAllRequiredTextField()) {
            mBtnConnect.setEnabled(true);
        } else {
            mBtnConnect.setEnabled(false);
        }
        hideKeyboard();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    public void onPause() {
        Log.d(TAG, " onPause()... ");
        super.onPause();
        mPresenter.pause();
        removeTextChangeedListener();
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
        mFyberParameterDemoHelper.setDeviceId(id);
    }

    @Override
    public void hideProgress() {
        mLayoutProgress.setVisibility(View.GONE);

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
     */
    public static class FyberConnectionHandler extends BufferHandler {
        FyberConnectionPresenter.View mView;

        FyberConnectionHandler(FyberConnectionPresenter.View view, Looper looper) {
            super(looper);
            this.mView = view;

        }

        @Override
        public void destroy() {
            super.destroy();
            this.mView = null;
            Log.d(TAG, " Handler destroy()..");
        }

        @Override
        protected void processMessage(Message msg) {
            if (mView == null) {
                Log.e(TAG, " handler: mView is null, return");
                return;
            }
            switch (msg.what) {
                case Msg.ON_OFFERS_LOADED:
                    mView.onOffersLoaded((List<OfferModel>) msg.obj);
                    break;
                case Msg.ON_ADVERTISING_IDENTIFIER_LOADED:
                    mView.onAdvertisingIdentifierLoaded((String) msg.obj);
                    break;
                case Msg.HIDE_PROGRESS:
                    mView.hideProgress();
                    break;
                case Msg.ON_ERROR:
                    mView.onError((String) msg.obj);
                    break;
                case Msg.ON_EMPTY_OFFERS_LOADED:
                    mView.onOffersLoaded(null);
                    break;
            }
        }
    }
}
