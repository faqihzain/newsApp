package in.faqihza.newsapp.source;

import dagger.Module;
import dagger.Provides;
import in.faqihza.newsapp.network.NewsApiService;

@Module
public class SourceModule {

    @Provides
    @SourceScope
    SourceInteractor provideSourceInteractor(NewsApiService newsApiService){
        return new SourceInteractorImpl(newsApiService);
    }

    @Provides
    @SourceScope
    SourcePresenter provideSourcePresenter(SourceInteractor interactor){
        return new SourcePresenterImpl(interactor);
    }
}
