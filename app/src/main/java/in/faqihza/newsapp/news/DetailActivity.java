package in.faqihza.newsapp.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.faqihza.newsapp.R;
import in.faqihza.newsapp.models.news.Article;
import in.faqihza.newsapp.utils.BindingUtils;

public class DetailActivity extends AppCompatActivity {
    public static final String PARAM_ARTICLE = "param-article";
    private Article article;
    private boolean isSaved;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.root)
    ScrollView scrollView;

    @BindView(R.id.iv_share)
    ImageButton ivShare;

    @BindView(R.id.btn_read_full)
    Button btnReadFull;

    @BindView(R.id.iv_news_image)
    ImageView ivnewsImage;

    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.tv_news_source)
    TextView tvNewsSource;

    @BindView(R.id.tv_news_title)
    TextView tvNewsTitle;

    @BindView(R.id.tv_news_desc)
    TextView tvNewsDesc;

    @BindView(R.id.tv_news_content)
    TextView tvNewsContent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        makeUiFullscreen();
        setupToolbar();
        setupArticleAndListener();

        getSavedState();

//        binding.ivSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isSaved) {
//                    newsRepository.removeSaved(article.id);
//                } else {
//                    newsRepository.save(article.id);
//                }
//            }
//        });
    }

    private void getSavedState() {
        if (article != null) {
//            newsRepository.isSaved(article.id).observe(this, new Observer<Boolean>() {
//                @Override
//                public void onChanged(@Nullable Boolean aBoolean) {
//                    if (aBoolean != null) {
//                        isSaved = aBoolean;
//                        if (isSaved) {
//                            binding.ivSave.setImageResource(R.drawable.ic_saved_item);
//                        } else {
//                            binding.ivSave.setImageResource(R.drawable.ic_save);
//                        }
//                    }
//                }
//            });
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void makeUiFullscreen() {
        // When applying fullscreen layout, transparent bar works only for VERSION < 21
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            scrollView.setFitsSystemWindows(true);
        }
        // Make UI fullscreen and make it load stable
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    }

    /**
     * Extracts Article from Arguments and Adds button listeners
     */
    private void setupArticleAndListener() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(DetailActivity.PARAM_ARTICLE))
        {
            Article article = extras.getParcelable(DetailActivity.PARAM_ARTICLE);
            if (article != null)
            {
                this.article = article;

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .priority(Priority.HIGH);

                Glide.with(this)
                        .asBitmap()
                        .load(article.getUrlToImage())
                        .apply(options)
                        .into(ivnewsImage);

                String datec = article.getPublishedAt();
                String defaultTimezone = TimeZone.getDefault().getID();
                Date date = null;
                try {
                    date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")).parse(datec.replaceAll("Z$", "+0000"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tvNewsTitle.setText(article.getTitle());
                tvTime.setText(BindingUtils.formatDateForDetails(new Timestamp(date.getTime())));
                tvNewsSource.setText(article.getSource().getName());
                tvNewsDesc.setText(article.getDescription());
                tvNewsContent.setText(article.getContent());
                setupShareButton(article);
                setupButtonClickListener(article);
            }
        }
    }

    private void setupShareButton(final Article article) {
        ivShare.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String shareText = article.getTitle() + "\n" + article.getUrl();
            intent.putExtra(Intent.EXTRA_TEXT, shareText);
            intent.setType("text/plain");

            startActivity(intent);
        });
    }

    private void setupButtonClickListener(final Article article) {
        btnReadFull.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
            startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_enter_transition, R.anim.slide_down_animation);
    }
}