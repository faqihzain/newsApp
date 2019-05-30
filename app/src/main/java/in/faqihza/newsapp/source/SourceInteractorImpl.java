package in.faqihza.newsapp.source;

import in.faqihza.newsapp.models.Specification;
import in.faqihza.newsapp.models.sources.SourceWrapper;
import in.faqihza.newsapp.network.NewsApiService;
import io.reactivex.Observable;

public class SourceInteractorImpl implements SourceInteractor {

    NewsApiService newsApiService;

    public SourceInteractorImpl(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }

    @Override
    public Observable<SourceWrapper> getSources(Specification specs) {
        return newsApiService.getSources(specs.getCategory(), specs.getCountry(), specs.getLanguage());
    }
}
