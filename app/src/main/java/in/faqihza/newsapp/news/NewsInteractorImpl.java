package in.faqihza.newsapp.news;

import in.faqihza.newsapp.network.NewsApiService;
import in.faqihza.newsapp.models.NewsWrapper;
import in.faqihza.newsapp.models.Specification;
import io.reactivex.Observable;

public class NewsInteractorImpl implements NewsInteractor {

    NewsApiService newsApiService;

    public NewsInteractorImpl(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }



    @Override
    public Observable<NewsWrapper> newsGetHeadlines(Specification specs) {
        return newsApiService.getHeadlines(specs.getCategory(), specs.getCountry());
    }
}
