package in.faqihza.newsapp.news;

import dagger.Subcomponent;

@NewsScope
@Subcomponent(modules = {NewsModule.class})
public interface NewsComponent {

    NewsFragment inject(NewsFragment fragment);
}
