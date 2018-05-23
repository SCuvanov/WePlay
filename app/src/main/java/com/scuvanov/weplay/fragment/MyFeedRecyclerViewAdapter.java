package com.scuvanov.weplay.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scuvanov.weplay.R;
import com.scuvanov.weplay.entity.GameCollection;
import com.scuvanov.weplay.fragment.FeedFragment.OnListFragmentInteractionListener;
import com.scuvanov.weplay.fragment.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFeedRecyclerViewAdapter extends RecyclerView.Adapter<MyFeedRecyclerViewAdapter.ViewHolder> {

    private final List<GameCollection> mGameCollections;
    private final OnListFragmentInteractionListener mListener;

    public MyFeedRecyclerViewAdapter(List<GameCollection> gameCollections, OnListFragmentInteractionListener listener) {
        mGameCollections = gameCollections;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.gameCollection = mGameCollections.get(position);
        holder.mIdView.setText(mGameCollections.get(position).getId());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.gameCollection);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGameCollections.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public GameCollection gameCollection;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
