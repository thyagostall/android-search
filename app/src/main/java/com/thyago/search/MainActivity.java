package com.thyago.search;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.widget.Filterable;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Test";
    private ListView mListSomeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SomeDataAdapter adapter = new SomeDataAdapter(getLayoutInflater());

        mListSomeData = (ListView) findViewById(R.id.list_some_data);
        mListSomeData.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(LOG_TAG, query);
                Filterable filterable = (Filterable) mListSomeData.getAdapter();
                filterable.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                Log.d(LOG_TAG, query);
                Filterable filterable = (Filterable) mListSomeData.getAdapter();
                filterable.getFilter().filter(query);
                return true;
            }

        });

        return true;
    }
}
