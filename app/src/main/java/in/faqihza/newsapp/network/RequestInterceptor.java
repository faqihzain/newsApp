package in.faqihza.newsapp.network;


import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.faqihza.newsapp.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class RequestInterceptor implements Interceptor {

    @Inject
    public RequestInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.NEWS_API_KEY)
                .build();

        Request request = original.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}

