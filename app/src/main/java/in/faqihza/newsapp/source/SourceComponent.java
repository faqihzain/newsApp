package in.faqihza.newsapp.source;

import dagger.Subcomponent;

@SourceScope
@Subcomponent(modules = {SourceModule.class})
public interface SourceComponent {

    SourceFragment inject(SourceFragment fragment);

}
