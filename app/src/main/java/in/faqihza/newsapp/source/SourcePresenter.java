package in.faqihza.newsapp.source;

import in.faqihza.newsapp.models.Specification;

public interface SourcePresenter {

    void setView(SourceView view);
    void getSource(Specification specs);
}
