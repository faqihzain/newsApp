package in.faqihza.newsapp;

import android.app.Application;

import in.faqihza.newsapp.network.NetworkModule;
import in.faqihza.newsapp.news.NewsComponent;
import in.faqihza.newsapp.news.NewsModule;

public class BaseApplication extends Application {

    AppComponent appComponent;
    NewsComponent newsComponent;

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


}
