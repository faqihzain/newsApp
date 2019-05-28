package in.faqihza.newsapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.faqihza.newsapp.news.NewsFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public Unbinder unbinder;

    private HeadlinesFragment headlinesFragment;
    private NewsFragment newsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Bundle bundle = new Bundle();
            switch (item.getItemId()) {
                case R.id.navigation_headlines:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, headlinesFragment)
                            .commit();
                    bundle.putString("item_category",
                                getString(R.string.title_headlines)
                        );
                    return true;
                    case R.id.navigation_saved:
                        if (newsFragment == null) {
                            newsFragment = NewsFragment.newInstance(null);
                        }
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, newsFragment)
                                .commit();
                        bundle.putString(
                                "item_category",
                                getString(R.string.title_saved)
                        );
//                        return true;
//                    case R.id.navigation_sources:
//                        if (sourceFragment == null) {
//                            sourceFragment = SourceFragment.newInstance();
//                        }
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.fragment_container, sourceFragment)
//                                .commit();
//                        bundle.putString(
//                                FirebaseAnalytics.Param.ITEM_CATEGORY,
//                                getString(R.string.title_sources)
//                        );
//                        return true;
            }
            return false;
        });

        if (savedInstanceState == null) {
            // Add a default fragment
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, HeadlinesFragment.newInstance())
                    .commit();
        }

        setupToolbar();

    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.app_name));
            //Remove trailing space from toolbar
            toolbar.setContentInsetsAbsolute(10, 10);
        }
    }
}
