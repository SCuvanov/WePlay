package com.scuvanov.weplay.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scuvanov.weplay.R;
import com.scuvanov.weplay.application.WePlayApplication;
import com.scuvanov.weplay.entity.Game;
import com.scuvanov.weplay.fragment.SearchFragment.OnListFragmentInteractionListener;
import com.scuvanov.weplay.util.ImageUtil;

import java.util.List;

public class MySearchRecyclerViewAdapter extends RecyclerView.Adapter<MySearchRecyclerViewAdapter.ViewHolder> {

    private final List<Game> mGames;
    private final OnListFragmentInteractionListener mListener;
    private final Context context;

    public MySearchRecyclerViewAdapter(List<Game> games, OnListFragmentInteractionListener listener) {
        mGames = games;
        Log.e("ADAPTER", mGames.toString());
        mListener = listener;
        context = WePlayApplication.getContext();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mGame = mGames.get(position);
        holder.mTvTitle.setText(mGames.get(position).getName());
        holder.mTvRating.setText((int) mGames.get(position).getRating() + " / 100");
        //holder.mTvDevelopers.setText(mGames.get(position).getDeveloperNames());

        holder.mLlPlatforms.removeAllViews();
        int[] platforms = mGames.get(position).getPlatforms();
        if(platforms != null) {
            holder.mTvPlatforms.setText("");
            for (int i :platforms) {
                ImageView iv = new ImageView(context);
                int imageId = ImageUtil.getImageId(context, "ic_" + i);
                iv.setImageResource(imageId);
                holder.mLlPlatforms.addView(iv);
            }
        }

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mGame);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTvTitle;
        public final TextView mTvRating;
        public final TextView mTvPlatforms;
        public final TextView mTvDevelopers;
        public final LinearLayout mLlPlatforms;
        public Game mGame;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTvTitle = view.findViewById(R.id.tvTitle);
            mTvDevelopers = view.findViewById(R.id.tvDevelopers);
            mTvRating = view.findViewById(R.id.tvRating);
            mTvPlatforms = view.findViewById(R.id.tvPlatforms);
            mLlPlatforms = view.findViewById(R.id.llPlatforms);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
