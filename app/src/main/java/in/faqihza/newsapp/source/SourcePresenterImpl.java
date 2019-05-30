package in.faqihza.newsapp.source;

import in.faqihza.newsapp.models.Specification;
import in.faqihza.newsapp.models.sources.SourceWrapper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SourcePresenterImpl implements SourcePresenter {

    private SourceInteractor sourceInteractor;
    private SourceView view;
    private Disposable sourceSubscription;

    public SourcePresenterImpl(SourceInteractor interactor) {
        this.sourceInteractor = interactor;
    }

    @Override
    public void setView(SourceView view) {
        this.view = view;
    }

    @Override
    public void getSource(Specification specs) {
        sourceSubscription = sourceInteractor.getSources(specs)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailed);
    }

    private void onSuccess(SourceWrapper sourceWrapper) {
        view.showSource(sourceWrapper);
    }

    private void onFailed(Throwable throwable) {

    }
}
