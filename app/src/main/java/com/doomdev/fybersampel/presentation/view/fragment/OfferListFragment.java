package com.doomdev.fybersampel.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doomdev.fybersampel.R;
import com.doomdev.fybersampel.presentation.model.OfferModel;
import com.doomdev.fybersampel.presentation.presenter.OfferListPresenter;
import com.doomdev.fybersampel.presentation.util.FyberModelConverter;
import com.doomdev.fybersampel.presentation.view.adapter.OffersRecyclerViewAdapter;
import com.doomdev.fybersampel.presentation.view.fragment.item.OfferItem;

import java.util.ArrayList;

import butterknife.ButterKnife;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class OfferListFragment extends Fragment implements OfferListPresenter.View{

    private static final String OFFER_MODEL_LIST = "OFFER_MODEL_LIST";
    private OnListFragmentInteractionListener mListener;
    private ArrayList<OfferModel> offerModels;
    private FyberModelConverter fyberModelConverter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OfferListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static OfferListFragment newInstance(ArrayList<OfferModel> offerModels) {
        OfferListFragment fragment = new OfferListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(OFFER_MODEL_LIST, offerModels);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fyberModelConverter = new FyberModelConverter();
        if (getArguments() != null) {
            offerModels = getArguments().getParcelableArrayList(OFFER_MODEL_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer_list, container, false);
        ButterKnife.bind(this, view);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (offerModels != null && offerModels.size() > 0) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(new OffersRecyclerViewAdapter(getContext(),fyberModelConverter.convertOfferModel(offerModels), mListener));
            }
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(OfferItem item);
    }
}
