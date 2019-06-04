package in.faqihza.newsapp.source;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import in.faqihza.newsapp.models.sources.Source;
import in.faqihza.newsapp.utils.BindingUtils;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder> {

    private List<Source> sources;
    Context context;
    SourceView sourceView;
    private int mExpandedPosition = -1;


    public SourceAdapter(List<Source> source, SourceView sourceView) {
        this.sources = source;
        this.sourceView = sourceView;
    }

    @NonNull
    @Override
    public SourceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.source_item, viewGroup, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceAdapter.ViewHolder viewHolder, int i) {

        final int position = viewHolder.getAdapterPosition();
        final boolean isExpanded = position == mExpandedPosition;

        String iconUrl = "https://besticon-demo.herokuapp.com/icon?url=%s&size=80..120..200";
        String sourceUrl = String.format(iconUrl, Uri.parse(sources.get(i).getUrl()).getAuthority());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        Glide.with(context)
                .asBitmap()
                .load(sourceUrl)
                .apply(options)
                .into(viewHolder.sourceImage);

        viewHolder.source = sources.get(i);

        viewHolder.sourceName.setText(sources.get(i).getName());
        viewHolder.sourceCategory.setText(BindingUtils.getSourceName(sources.get(i).getCategory(),sources.get(i).getCountry()));
        viewHolder.sourceDesc.setText(sources.get(i).getDescription());

        viewHolder.sourceDesc.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        viewHolder.btnOpen.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        viewHolder.constraintLayout.setActivated(isExpanded);
        viewHolder.constraintLayout.setOnClickListener(view -> {
            mExpandedPosition = isExpanded ? -1 : position;
            notifyDataSetChanged();
        });

        viewHolder.btnOpen.setOnClickListener(view -> {
            SourceAdapter.this.sourceView.onSourceItemClicked(viewHolder.source);

        });
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_source_image)
        ImageView sourceImage;

        @BindView(R.id.tv_source_name)
        TextView sourceName;

        @BindView(R.id.tv_source_category)
        TextView sourceCategory;

        @BindView(R.id.tv_source_desc)
        TextView sourceDesc;

        @BindView(R.id.btn_open)
        Button btnOpen;

        @BindView(R.id.rootCl)
        ConstraintLayout constraintLayout;

        Source source;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
