package com.as.casovi_plivanja.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.as.casovi_plivanja.R;
import com.as.casovi_plivanja.adapters.BlogAdapter;
import com.as.casovi_plivanja.data.Blog;

import java.util.ArrayList;
import java.util.List;

public class BlogFragment extends Fragment {

    private List<Blog> blogs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blog, container, false);

        requireActivity().setTitle(getString(R.string.blog));

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
        int numberOfColumns = 1;
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), numberOfColumns);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        blogs = new ArrayList<>();

        addBlogs();

        recyclerView.setAdapter(new BlogAdapter(getActivity(), blogs));
        return rootView;
    }

    private void addBlogs() {
        blogs.add(new Blog(R.drawable.post1,
                getString(R.string.post1),
                getString(R.string.post1_des),
                getString(R.string.phone_number),
                getString(R.string.site)));
        blogs.add(new Blog(R.drawable.post1,
                getString(R.string.post2),
                getString(R.string.post2_des),
                getString(R.string.phone_number),
                getString(R.string.site)));
    }
}