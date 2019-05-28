package in.faqihza.newsapp.news;

import in.faqihza.newsapp.models.Specification;

public interface NewsPresenter {

    void setView(NewsView view);
    void showNewsHeadline(Specification specs);
    void showNewsSaved();

}
