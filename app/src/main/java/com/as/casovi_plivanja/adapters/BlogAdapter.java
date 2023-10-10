package com.as.casovi_plivanja.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.as.casovi_plivanja.R;
import com.as.casovi_plivanja.activities.DetailActivity;
import com.as.casovi_plivanja.data.Blog;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {
    private final Context mContext;
    private final List<Blog> mBlogs;

    public BlogAdapter(Context context, List<Blog> foods) {
        mContext = context;
        mBlogs = foods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_blog, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mBlogs.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView nameTextView;
        private final CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.blog_image_card);
            nameTextView = itemView.findViewById(R.id.blog_name_card);
            cardView = itemView.findViewById(R.id.blog_view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Blog blog = mBlogs.get(position);
        Drawable drawable = ContextCompat.getDrawable(mContext, blog.getBlogImageId());
        holder.imageView.setBackground(drawable);
        holder.nameTextView.setText(blog.getBlogName());
        holder.cardView.setOnClickListener(view -> {

            Intent detailIntent = new Intent(mContext, DetailActivity.class);

            detailIntent.putExtra(mContext.getString(R.string.image_id),
                    blog.getBlogImageId());
            detailIntent.putExtra(mContext.getString(R.string.name),
                    blog.getBlogName());
            detailIntent.putExtra(mContext.getString(R.string.description),
                    blog.getBlogDescription());
            detailIntent.putExtra(mContext.getString(R.string.phone),
                    blog.getBlogPhone());
            detailIntent.putExtra(mContext.getString(R.string.web),
                    blog.getBlogWeb());
            mContext.startActivity(detailIntent);
        });
    }
}