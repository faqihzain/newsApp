package in.faqihza.newsapp.news;

import in.faqihza.newsapp.models.news.NewsWrapper;
import in.faqihza.newsapp.models.Specification;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenterImpl implements NewsPresenter {

    private NewsView view;
    private NewsInteractor newsInteractor;
    private Disposable newsSubscription;
    public NewsPresenterImpl(NewsInteractor interactor) {
        this.newsInteractor = interactor;
    }

    @Override
    public void setView(NewsView view) {
        this.view = view;
    }

    @Override
    public void showNewsHeadline(Specification specs) {
        newsSubscription = newsInteractor.newsGetHeadlines(specs)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailed);
    }

    private void onSuccess(NewsWrapper newsWrapper) {
        view.showNews(newsWrapper);
    }

    private void onFailed(Throwable throwable) {

    }

    @Override
    public void showNewsSaved() {

    }
}
