package in.faqihza.newsapp;

import javax.inject.Singleton;

import dagger.Component;
import in.faqihza.newsapp.network.NetworkModule;
import in.faqihza.newsapp.news.NewsComponent;
import in.faqihza.newsapp.news.NewsModule;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class})

public interface AppComponent {

    NewsComponent plus(NewsModule newsModule);
}
