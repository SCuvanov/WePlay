package com.scuvanov.weplay;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.scuvanov.weplay.dummy.DummyContent;
import com.scuvanov.weplay.fragment.CollectionsFragment;
import com.scuvanov.weplay.fragment.FeedFragment;
import com.scuvanov.weplay.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity implements SearchFragment.OnListFragmentInteractionListener, FeedFragment.OnListFragmentInteractionListener, CollectionsFragment.OnListFragmentInteractionListener {

    private final String TAG = MainActivity.class.getCanonicalName();

    FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {
                case R.id.navigation_collections:
                    showCollectionsFragment();
                    return true;
                case R.id.navigation_feed:
                    showFeedFragment();
                    return true;
                case R.id.navigation_search:
                    showSearchFragment();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        showCollectionsFragment();
    }

    private void showCollectionsFragment() {
        fragmentManager = getSupportFragmentManager();
        Fragment collectionsFragment = new CollectionsFragment();
        fragmentManager.beginTransaction().replace(R.id.content, collectionsFragment).commit();
    }

    private void showFeedFragment() {
        fragmentManager = getSupportFragmentManager();
        Fragment feedFragment = new FeedFragment();
        fragmentManager.beginTransaction().replace(R.id.content, feedFragment).commit();
    }

    private void showSearchFragment() {
        fragmentManager = getSupportFragmentManager();
        Fragment searchFragment = new SearchFragment();
        fragmentManager.beginTransaction().replace(R.id.content, searchFragment).commit();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onListFragmentInteraction(com.scuvanov.weplay.fragment.dummy.DummyContent.DummyItem item) {

    }
}
