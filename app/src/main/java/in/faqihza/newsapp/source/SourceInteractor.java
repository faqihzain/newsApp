package in.faqihza.newsapp.source;

import in.faqihza.newsapp.models.Specification;
import in.faqihza.newsapp.models.sources.SourceWrapper;
import io.reactivex.Observable;

public interface SourceInteractor {

    Observable<SourceWrapper> getSources(Specification specs);

}
