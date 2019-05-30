package in.faqihza.newsapp.network;

import in.faqihza.newsapp.BuildConfig;
import in.faqihza.newsapp.models.news.NewsWrapper;
import in.faqihza.newsapp.models.sources.SourceWrapper;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NewsApiService {

    @Headers("X-Api-Key:" + BuildConfig.NEWS_API_KEY)
    @GET("/v2/top-headlines")
    Observable<NewsWrapper> getHeadlines(
            @Query("category") String category,
            @Query("country") String country
    );

    @Headers("X-Api-Key:" + BuildConfig.NEWS_API_KEY)
    @GET("/v2/sources")
    Observable<SourceWrapper> getSources(
            @Query("category") String category,
            @Query("country") String country,
            @Query("language") String language
    );

    enum Category {
        business("Business"),
        entertainment("Entertainment"),
        general("General"),
        health("Health"),
        science("Science"),
        sports("Sports"),
        technology("Technology");

        public final String title;

        Category(String title) {
            this.title = title;
        }
    }
}
