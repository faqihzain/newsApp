package in.faqihza.newsapp.source;

import in.faqihza.newsapp.models.sources.Source;
import in.faqihza.newsapp.models.sources.SourceWrapper;

public interface SourceView {

    void showSource(SourceWrapper sourceWrapper);
    void onSourceItemClicked(Source source);
}
