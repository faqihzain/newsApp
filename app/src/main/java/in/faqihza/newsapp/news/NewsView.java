package in.faqihza.newsapp.news;

import in.faqihza.newsapp.models.news.Article;
import in.faqihza.newsapp.models.news.NewsWrapper;

public interface NewsView {

    void showNews(NewsWrapper newsWrapper);
    void onNewsItemClicked(Article article);
    void onItemOptionsClicked(Article article);
}
