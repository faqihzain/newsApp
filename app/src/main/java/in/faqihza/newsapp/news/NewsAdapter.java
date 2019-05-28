package in.faqihza.newsapp.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.faqihza.newsapp.R;
import in.faqihza.newsapp.models.Article;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Article> articles;
    Context context;
    NewsView newsView;

    public NewsAdapter(List<Article> articles, NewsView newsView) {
        this.articles = articles;
        this.newsView = newsView;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.news_item, viewGroup, false);

        return new ViewHolder(rootView);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        viewHolder.options.setOnClickListener(this);
        viewHolder.newsTitle.setText(articles.get(i).getTitle());
        viewHolder.newsSource.setText(articles.get(i).getSource().getName());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        Glide.with(context)
                .asBitmap()
                .load(articles.get(i).getUrlToImage())
                .apply(options)
                .into(viewHolder.newsImage);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tv_news_title)
        TextView newsTitle;

        @BindView(R.id.tv_news_source)
        TextView newsSource;

        @BindView(R.id.iv_news_image)
        ImageView newsImage;

        @BindView(R.id.iv_options)
        ImageButton options;

        Article article;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @Override
        public void onClick(View view) {
            NewsAdapter.this.newsView.onNewsItemClicked(article);

            options.setOnClickListener(view1 -> {
                NewsAdapter.this.newsView.onItemOptionsClicked(article);
            });
        }
    }
}
