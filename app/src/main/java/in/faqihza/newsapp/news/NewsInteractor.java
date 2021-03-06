package in.faqihza.newsapp.news;

import in.faqihza.newsapp.models.news.NewsWrapper;
import in.faqihza.newsapp.models.Specification;
import io.reactivex.Observable;

public interface NewsInteractor {

    Observable<NewsWrapper> newsGetHeadlines(Specification specs);
}
