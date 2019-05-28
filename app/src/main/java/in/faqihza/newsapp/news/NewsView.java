package in.faqihza.newsapp.news;

import in.faqihza.newsapp.models.Article;
import in.faqihza.newsapp.models.NewsWrapper;

public interface NewsView {

    void showNews(NewsWrapper newsWrapper);
    void onNewsItemClicked(Article article);
    void onItemOptionsClicked(Article article);
}
