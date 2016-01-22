package com.doomdev.fybersampel.presenter.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doomdev.fybersampel.R;
import com.doomdev.fybersampel.presenter.view.fragment.OfferListFragment.OnListFragmentInteractionListener;
import com.doomdev.fybersampel.presenter.view.fragment.item.OfferItem;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link OfferItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class OffersRecyclerViewAdapter extends RecyclerView.Adapter<OffersRecyclerViewAdapter.ViewHolder> {

    private final List<OfferItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private static final String TAG = OffersRecyclerViewAdapter.class.getSimpleName();
    private Context mContext;
    private FragmentLifecycleListener fragmentLifecycleListener;

    public OffersRecyclerViewAdapter(Context context, List<OfferItem> items, OnListFragmentInteractionListener listener) {
        this.mContext = context;
        this.mValues = items;
        this.mListener = listener;
    }

    /**
     * Called when a view created by this adapter has been detached from its window.
     * <p/>
     * <p>Becoming detached from the window is not necessarily a permanent condition;
     * the consumer of an Adapter's views may choose to cache views offscreen while they
     * are not visible, attaching an detaching them as appropriate.</p>
     *
     * @param holder Holder of the view being detached
     */
    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    /**
     * Called by RecyclerView when it stops observing this Adapter.
     *
     * @param recyclerView The RecyclerView instance which stopped observing this adapter.
     * @see #onAttachedToRecyclerView(RecyclerView)
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
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
//        ImageLoader.getInstance().displayImage(mValues.get(position).thumbnail, holder.mThumbnail);
        holder.mProgress.setVisibility(View.VISIBLE);
        holder.mThumbnail.setVisibility(View.INVISIBLE);
        ImageLoader.getInstance().displayImage(mValues.get(position).thumbnail, holder.mThumbnail,
                new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        holder.mProgress.setVisibility(View.GONE);
                        holder.mThumbnail.setVisibility(View.VISIBLE);
                    }
                });
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

    /**
     * This class is holding gui elements for each item
     */
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

    public interface FragmentLifecycleListener {
        void onFragmentDetach();
    }
}
