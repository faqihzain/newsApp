package in.faqihza.newsapp.news;

import dagger.Module;
import dagger.Provides;
import in.faqihza.newsapp.network.NewsApiService;

@Module
public class NewsModule {

    @Provides
    @NewsScope
    NewsInteractor provideNewsInteractor(NewsApiService newsApiService){
        return new NewsInteractorImpl(newsApiService);
    }

    @Provides
    @NewsScope
    NewsPresenter provideNewsPresenter(NewsInteractor interactor){
        return new NewsPresenterImpl(interactor);
    }

}
