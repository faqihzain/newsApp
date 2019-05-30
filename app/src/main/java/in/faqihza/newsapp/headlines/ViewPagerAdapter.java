package in.faqihza.newsapp.headlines;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.faqihza.newsapp.network.NewsApiService;
import in.faqihza.newsapp.news.NewsFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final NewsFragment[] newsFragments;


    public ViewPagerAdapter(FragmentManager fm, String[] categories) {
        super(fm);
        newsFragments = new NewsFragment[categories.length];
        for (int i = 0; i < categories.length; i++) {
            newsFragments[i] = NewsFragment.newInstance(NewsApiService.Category.valueOf(categories[i]));
        }
    }

    @Override
    public Fragment getItem(int i) {
        return newsFragments[i];
    }

    @Override
    public int getCount() {
        return newsFragments == null ? 0 : newsFragments.length;
    }
}
