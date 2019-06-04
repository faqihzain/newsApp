package in.faqihza.newsapp.source;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.faqihza.newsapp.BaseApplication;
import in.faqihza.newsapp.R;
import in.faqihza.newsapp.WebviewActivity;
import in.faqihza.newsapp.models.Specification;
import in.faqihza.newsapp.models.sources.Source;
import in.faqihza.newsapp.models.sources.SourceWrapper;


public class SourceFragment extends Fragment implements SourceView {

    @Inject SourcePresenter presenter;

    @BindView(R.id.rv_sources)
    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;

    private List<Source> articles = new ArrayList<>();

    public SourceFragment() {
        // Required empty public constructor
    }

    public static SourceFragment newInstance() {
        SourceFragment fragment = new SourceFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseApplication) getActivity().getApplication()).createSourceComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_source, container, false);
        ButterKnife.bind(this, rootview);

        adapter = new SourceAdapter(articles, this);
        recyclerView.setAdapter(adapter);

        return rootview;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.setView(this);
        getSource();
    }

    private void getSource() {
        Specification specification = new Specification();
        specification.setLanguage(Locale.getDefault().getLanguage());
        specification.setCountry(null);
        presenter.getSource(specification);

    }

    @Override
    public void showSource(SourceWrapper sourceWrapper) {
        articles.clear();
        articles.addAll(sourceWrapper.getSources());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSourceItemClicked(Source source) {
        Intent intent = new Intent(getContext(), WebviewActivity.class);
        intent.putExtra("webUrl", source.getUrl());
        startActivity(intent);
    }
}
