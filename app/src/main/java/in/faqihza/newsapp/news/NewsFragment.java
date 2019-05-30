package in.faqihza.newsapp.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.faqihza.newsapp.BaseApplication;
import in.faqihza.newsapp.R;
import in.faqihza.newsapp.network.NewsApiService;
import in.faqihza.newsapp.models.news.Article;
import in.faqihza.newsapp.models.news.NewsWrapper;
import in.faqihza.newsapp.models.Specification;


public class NewsFragment extends Fragment implements NewsView {
    public static final String PARAM_CATEGORY = "param-category";
    public static final String PARAM_LIST_STATE = "param-state";

    @BindView(R.id.rv_news_posts)
    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;

    Unbinder unbinder;

    private List<Article> articles = new ArrayList<>();


    private NewsApiService.Category newsCategory;
    private boolean showSaved = false;
    private Parcelable listState;

    @Inject
    NewsPresenter newsPresenter;

    public static NewsFragment newInstance(NewsApiService.Category category) {
        NewsFragment fragment = new NewsFragment();
        if (category == null) {
            return fragment;
        }
        Bundle args = new Bundle();
        args.putString(PARAM_CATEGORY, category.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseApplication) getActivity().getApplication()).createNewsComponent().inject(this);

        if (getArguments() != null) {
            newsCategory = NewsApiService.Category
                    .valueOf(getArguments().getString(PARAM_CATEGORY));
        } else {
            showSaved = true;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_fragment, container, false);

        unbinder = ButterKnife.bind(this, rootView);

        adapter = new NewsAdapter(articles, this);
        recyclerView.setAdapter(adapter);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(PARAM_LIST_STATE);
        }

        if (showSaved) {

            newsPresenter.setView(this);
            newsPresenter.showNewsSaved();
//            viewModel.getAllSaved().observeForever(new Observer<List<Article>>() {
//                @Override
//                public void onChanged(@Nullable List<Article> articles) {
//                    if (articles != null) {
//                        newsAdapter.setArticles(articles);
//                        restoreRecyclerViewState();
//                    } else {
//                        newsAdapter.notifyDataSetChanged();
//                        restoreRecyclerViewState();
//                    }
//                }
//            });
        } else {
            Specification specs = new Specification();
            specs.setCategory(newsCategory);
            newsPresenter.setView(this);
            newsPresenter.showNewsHeadline(specs);

//            viewModel.getNewsHeadlines(specs).observe(this, new Observer<List<Article>>() {
//                @Override
//                public void onChanged(@Nullable List<Article> articles) {
//                    if (articles != null) {
//                        newsAdapter.setArticles(articles);
//                        restoreRecyclerViewState();
//                    }
//                }
//            });
        }
    }

    @Override
    public void showNews(NewsWrapper newsWrapper) {
        articles.clear();
        articles.addAll(newsWrapper.getArticles());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNewsItemClicked(Article article) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(DetailActivity.PARAM_ARTICLE, article);
        intent.putExtras(extras);
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().overridePendingTransition(R.anim.slide_up_animation, R.anim.fade_exit_transition);
        }
    }

    @Override
    public void onItemOptionsClicked(Article article) {
//        OptionsBottomSheet bottomSheet = OptionsBottomSheet.getInstance(article.getTitle(), article.getUrl(), article.getId(), showSaved);
//        if (getActivity() != null) {
//            bottomSheet.show(getActivity().getSupportFragmentManager(), bottomSheet.getTag());
//        } else {
//            Timber.e("No Parent Activity was found!");
//        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

//        if (binding.rvNewsPosts.getLayoutManager() != null) {
//            listState = binding.rvNewsPosts.getLayoutManager().onSaveInstanceState();
//            outState.putParcelable(PARAM_LIST_STATE, listState);
//        }
    }

    private void restoreRecyclerViewState() {
//        if (binding.rvNewsPosts.getLayoutManager() != null) {
//            binding.rvNewsPosts.getLayoutManager().onRestoreInstanceState(listState);
//        }
    }
}
