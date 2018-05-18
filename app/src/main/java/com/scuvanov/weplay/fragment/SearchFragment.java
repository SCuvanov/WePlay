package com.scuvanov.weplay.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;

    private final String TAG = SearchFragment.class.getCanonicalName();
    private final String SEARCH_AND_FILTERS = "Search & Filters";
    private FloatingActionButton fabSearch;
    private ArrayAdapter<String> spGenreAdapter, spPlatformAdapter, spESRBAdapter;

    private List<String> genreList = new ArrayList<String>();
    private Map<String, Integer> genreMap = new HashMap<String, Integer>();
    private List<String> platformList = new ArrayList<String>();
    private Map<String, Integer> platformMap = new HashMap<String, Integer>();
    private List<String> esrbList = new ArrayList<String>();
    private Map<String, Integer> esrbMap = new HashMap<String, Integer>();

    private ViewModelProvider.Factory viewModelFactory;
    private GenreViewModel genreViewModel;
    private PlatformViewModel platformViewModel;
    private EsrbViewModel esrbViewModel;
    private GameViewModel gameViewModel;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SearchFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SearchFragment newInstance(int columnCount) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MySearchRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }

        fabSearch = view.findViewById(R.id.fabSearch);
        fabSearch.setOnClickListener(this);

        spGenreAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, genreList);
        spGenreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genreViewModel = ViewModelProviders.of(this, viewModelFactory).get(GenreViewModel.class);
        genreViewModel.getAll().observe(this, genres -> { //new Observer<List<Genre>>()
            genreList.clear();
            spGenreAdapter.notifyDataSetChanged();
        });

        spPlatformAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, platformList);
        spPlatformAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        platformViewModel = ViewModelProviders.of(this, viewModelFactory).get(PlatformViewModel.class);
        platformViewModel.getAll().observe(this, platforms -> {
            platformList.clear();
            spPlatformAdapter.notifyDataSetChanged();
        });

        spESRBAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, esrbList);
        spESRBAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        esrbViewModel = ViewModelProviders.of(this, viewModelFactory).get(EsrbViewModel.class);
        esrbViewModel.getAll().observe(this, esrbs -> {
            esrbList.clear();
            spESRBAdapter.notifyDataSetChanged();
        });

        gameViewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel.class);

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
        final Spinner spGenre = inflatedView.findViewById(R.id.spGenre);
        spGenre.setAdapter(spGenreAdapter);

        final RangeBar rbRating = inflatedView.findViewById(R.id.rbRating);
        final Spinner spPlatform = inflatedView.findViewById(R.id.spPlatform);
        spPlatform.setAdapter(spPlatformAdapter);

        final Spinner spESRB = inflatedView.findViewById(R.id.spESRB);
        spESRB.setAdapter(spESRBAdapter);

        final List<View> dialogViews = new ArrayList<View>();
        dialogViews.add(etTitle);
        dialogViews.add(spGenre);
        dialogViews.add(rbRating);
        dialogViews.add(rbRating);
        dialogViews.add(spPlatform);
        dialogViews.add(spESRB);

        Button btnTitleFilter = inflatedView.findViewById(R.id.btnTitleFilter);
        btnTitleFilter.setOnClickListener(view -> hideAndShowViews(etTitle, dialogViews)); //new View.OnClickListener()

        Button btnGenreFilter = inflatedView.findViewById(R.id.btnGenreFilter);
        btnGenreFilter.setOnClickListener(view -> hideAndShowViews(spGenre, dialogViews));

        Button btnRatingFilter = inflatedView.findViewById(R.id.btnRatingFilter);
        btnRatingFilter.setOnClickListener(view -> hideAndShowViews(rbRating, dialogViews));

        Button btnPlatformFilterBtn = inflatedView.findViewById(R.id.btnPlatformFilterBtn);
        btnPlatformFilterBtn.setOnClickListener(view -> hideAndShowViews(spPlatform, dialogViews));

        Button btnESRBFilter = inflatedView.findViewById(R.id.btnESRBFilter);
        btnESRBFilter.setOnClickListener(view -> hideAndShowViews(spESRB, dialogViews));


        //TODO: This may not work.
        dialogSheet.setPositiveButton(android.R.string.ok, v -> { //new DialogSheet.OnPositiveClickListener()
            String title = etTitle.getText().toString();
            String genre = spGenre.getSelectedItem().toString();
            String platform = spPlatform.getSelectedItem().toString();
            String esrb = spESRB.getSelectedItem().toString();




        });
        dialogSheet.setNegativeButton(android.R.string.cancel, v -> {
            // Your action
        });
        dialogSheet.setButtonsColorRes(R.color.colorPrimaryDark);
        dialogSheet.setTitle(SEARCH_AND_FILTERS);
        dialogSheet.show();
    }

    private void hideAndShowViews(View mainView, List<View> views) {
        for (View v : views) {
            if (v.getId() == mainView.getId()) {
                mainView.setVisibility(View.VISIBLE);
            } else {
                v.setVisibility(View.GONE);
            }
        }
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
        void onListFragmentInteraction(DummyItem item);
    }
}
