package in.faqihza.newsapp.headlines;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.faqihza.newsapp.R;
import in.faqihza.newsapp.network.NewsApiService;

public class HeadlinesFragment extends Fragment {
    private final String[] categories = {
            NewsApiService.Category.general.name(),
            NewsApiService.Category.business.name(),
            NewsApiService.Category.sports.name(),
            NewsApiService.Category.health.name(),
            NewsApiService.Category.entertainment.name(),
            NewsApiService.Category.technology.name(),
            NewsApiService.Category.science.name(),
    };
    private final int[] categoryIcons = {
            R.drawable.ic_headlines,
            R.drawable.nav_business,
            R.drawable.nav_sports,
            R.drawable.nav_health,
            R.drawable.nav_entertainment,
            R.drawable.nav_tech,
            R.drawable.nav_science,

    };

    @BindView(R.id.viewpager_headlines)
    ViewPager viewPagerHeadLine;

    @BindView(R.id.tablayout_headlines)
    TabLayout tabLayout;

    Unbinder unbinder;
//    private FragmentHeadlinesBinding binding;

    public HeadlinesFragment() {
        // Required empty public constructor
    }

    public static HeadlinesFragment newInstance() {
        return new HeadlinesFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_headlines, container, false);

        unbinder =  ButterKnife.bind(this, rootView);
        ViewCompat.setElevation(tabLayout, getResources().getDimension(R.dimen.tab_layout_elevation));

        if (getActivity() != null) {
            ViewPagerAdapter viewPager = new ViewPagerAdapter(getChildFragmentManager(), categories);
            viewPagerHeadLine.setAdapter(viewPager);
            tabLayout.setupWithViewPager(viewPagerHeadLine);
            setupTabIcons();
        }
        return rootView;
    }

    private void setupTabIcons() {
        TabLayout.Tab tab;
        for (int i = 0; i < categories.length; i++) {
            tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setIcon(categoryIcons[i]).setText(categories[i]);
            }
        }
    }
}
