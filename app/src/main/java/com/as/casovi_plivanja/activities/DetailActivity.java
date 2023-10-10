package com.as.casovi_plivanja.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.as.casovi_plivanja.R;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    private static final int DEFAULT_NUMBER = 0;
    private static final int NO_IMAGE_PROVIDED = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent detailIntent = getIntent();

        int imageId = detailIntent.getIntExtra(getString(R.string.image_id), NO_IMAGE_PROVIDED);
        String name = detailIntent.getStringExtra(getString(R.string.name));
        String description = detailIntent.getStringExtra(getString(R.string.description));
        String phone = detailIntent.getStringExtra(getString(R.string.phone));
        String web = detailIntent.getStringExtra(getString(R.string.web));

        setTitle(name);

        imageView = findViewById(R.id.detail_image_view);
        imageView.setImageResource(imageId);

        setDetails(R.id.detail_phone, phone, R.drawable.phone);
        setDetails(R.id.detail_web, web, R.drawable.public_web);

        TextView descriptionTextView = findViewById(R.id.detail_long_description);
        descriptionTextView.setText(description);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setDetails(int textViewId, String string, int icResourceId) {
        TextView textView = findViewById(textViewId);
        if (string == null) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(string);
            textView.setCompoundDrawablesWithIntrinsicBounds(icResourceId, DEFAULT_NUMBER, DEFAULT_NUMBER, DEFAULT_NUMBER);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
