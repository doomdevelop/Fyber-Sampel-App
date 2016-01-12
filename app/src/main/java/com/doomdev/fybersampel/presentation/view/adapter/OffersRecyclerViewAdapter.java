package com.doomdev.fybersampel.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doomdev.fybersampel.R;
import com.doomdev.fybersampel.presentation.view.fragment.OfferListFragment.OnListFragmentInteractionListener;
import com.doomdev.fybersampel.presentation.view.fragment.item.DummyContent.DummyItem;
import com.doomdev.fybersampel.presentation.view.fragment.item.OfferItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class OffersRecyclerViewAdapter extends RecyclerView.Adapter<OffersRecyclerViewAdapter.ViewHolder> {

    private final List<OfferItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public OffersRecyclerViewAdapter(List<OfferItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_offer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitle.setText(mValues.get(position).title);
        holder.mTeaser.setText(mValues.get(position).teaser);
        holder.mPayout.setText(mValues.get(position).payout);
//        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mThumbnail;
        public final ProgressBar mProgress;
        public final View mView;
        public final TextView mTitle;
        public final TextView mTeaser;
        public final TextView mPayout;
        public OfferItem mItem;

        public ViewHolder(View view) {
            super(view);
            mThumbnail = (ImageView) view.findViewById(R.id.offer_thumbnail);
            mProgress = (ProgressBar) view.findViewById(R.id.offer_loading_progress);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.offer_title);
            mTeaser = (TextView) view.findViewById(R.id.offer_teaser);
            mPayout = (TextView) view.findViewById(R.id.offer_payout);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}
