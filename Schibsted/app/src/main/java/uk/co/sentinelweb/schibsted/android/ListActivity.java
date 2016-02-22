package uk.co.sentinelweb.schibsted.android;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import uk.co.sentinelweb.schibsted.R;
import uk.co.sentinelweb.schibsted.android.adapter.RecipeItemAdapter;
import uk.co.sentinelweb.schibsted.loader.LoaderId;
import uk.co.sentinelweb.schibsted.loader.RecipeLoader;
import uk.co.sentinelweb.schibsted.model.Recipe;
import uk.co.sentinelweb.schibsted.net.call.RecipeResponse;

public class ListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    public static final String ARG_Q = "q";
    RecyclerView listView;
    RecipeItemAdapter recipeItemAdapter;
    ProgressBar _progress;
    private Bundle _loaderArgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        listView = (RecyclerView)findViewById(R.id.recipe_list);
        listView.setLayoutManager(new LinearLayoutManager(this));
        _loaderArgs = new Bundle();
        ((EditText)findViewById(R.id.search_edit)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView v, final int actionId, final KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // TODO pass bundle with search text
                    _loaderArgs.putString(ARG_Q, v.getText().toString());
                    getSupportLoaderManager().restartLoader(LoaderId.LIST_LOADER, _loaderArgs, ListActivity.this);
                }
                return true;
            }
        });
        _progress = (ProgressBar)findViewById(R.id.recipe_progress);
        getSupportLoaderManager().initLoader(LoaderId.LIST_LOADER, _loaderArgs, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader onCreateLoader(final int id, final Bundle args) {
        if (id == LoaderId.LIST_LOADER) {
            _progress.setVisibility(View.VISIBLE);
            final RecipeLoader recipeLoader = new RecipeLoader(this);
            recipeLoader.setArgs(args);
            return recipeLoader;
        }
        return null;
    }

    @Override
    public void onLoadFinished(final android.support.v4.content.Loader loader, final Object data) {
        recipeItemAdapter = new RecipeItemAdapter(((RecipeResponse)data).getRecipes());
        listView.setAdapter(recipeItemAdapter);
        _progress.setVisibility(View.GONE);

    }

    @Override
    public void onLoaderReset(final android.support.v4.content.Loader loader) {
        _progress.setVisibility(View.VISIBLE);
        loader.reset();
    }

}
