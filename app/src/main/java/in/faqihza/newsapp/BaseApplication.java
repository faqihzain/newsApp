package in.faqihza.newsapp;

import android.app.Application;

import in.faqihza.newsapp.network.NetworkModule;
import in.faqihza.newsapp.news.NewsComponent;
import in.faqihza.newsapp.news.NewsModule;
import in.faqihza.newsapp.source.SourceComponent;
import in.faqihza.newsapp.source.SourceModule;

public class BaseApplication extends Application {

    AppComponent appComponent;
    NewsComponent newsComponent;
    SourceComponent sourceComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = createAppComponent();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public NewsComponent createNewsComponent(){
        newsComponent = appComponent.plus(new NewsModule());
        return  newsComponent;
    }

    public SourceComponent createSourceComponent(){
        sourceComponent = appComponent.plus(new SourceModule());
        return  sourceComponent;
    }

}
