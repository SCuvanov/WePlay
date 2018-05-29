package com.scuvanov.weplay.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.appyvet.materialrangebar.RangeBar;
import com.marcoscg.dialogsheet.DialogSheet;
import com.scuvanov.weplay.R;
import com.scuvanov.weplay.entity.Esrb;
import com.scuvanov.weplay.entity.Game;
import com.scuvanov.weplay.entity.Genre;
import com.scuvanov.weplay.entity.Platform;
import com.scuvanov.weplay.fragment.dummy.DummyContent;
import com.scuvanov.weplay.fragment.dummy.DummyContent.DummyItem;
import com.scuvanov.weplay.viewmodel.EsrbViewModel;
import com.scuvanov.weplay.viewmodel.GameViewModel;
import com.scuvanov.weplay.viewmodel.GenreViewModel;
import com.scuvanov.weplay.viewmodel.PlatformViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    private OnListFragmentInteractionListener mListener;
    private MySearchRecyclerViewAdapter mySearchRecyclerViewAdapter;


    private final String TAG = SearchFragment.class.getCanonicalName();
    private final String SEARCH_AND_FILTERS = "Search";
    private FloatingActionButton fabSearch;

    private List<Game> gamesList = new ArrayList<Game>();

    private ViewModelProvider.Factory viewModelFactory;
    private GameViewModel gameViewModel;
    private Map<Integer, String> genreNameMap = new HashMap<>();
    private Map<Integer, String> platformNameMap = new HashMap<>();

    public SearchFragment() {}

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_list, container, false);

        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        gameViewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel.class);

        mySearchRecyclerViewAdapter = new MySearchRecyclerViewAdapter(gamesList, mListener);
        recyclerView.setAdapter(mySearchRecyclerViewAdapter);

        fabSearch = view.findViewById(R.id.fabSearch);
        fabSearch.setOnClickListener(this);

        gameViewModel.getObservableGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> games) {
                gamesList.clear();
                gamesList.addAll(games);
                mySearchRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabSearch:
                openSearchDialog();
                break;
        }
    }

    private void openSearchDialog() {
        DialogSheet dialogSheet = new DialogSheet(getActivity());
        dialogSheet.setView(R.layout.dialog_search);

        View inflatedView = dialogSheet.getInflatedView();

        final EditText etTitle = inflatedView.findViewById(R.id.etTitle);

        dialogSheet.setPositiveButton(android.R.string.ok, v -> { //new DialogSheet.OnPositiveClickListener()
            String title = etTitle.getText().toString();
            gameViewModel.getGames(title);
        });
        dialogSheet.setNegativeButton(android.R.string.cancel, v -> { });
        dialogSheet.setButtonsColorRes(R.color.colorPrimaryDark);
        dialogSheet.setTitle(SEARCH_AND_FILTERS);
        dialogSheet.show();
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Game game);
    }
}
