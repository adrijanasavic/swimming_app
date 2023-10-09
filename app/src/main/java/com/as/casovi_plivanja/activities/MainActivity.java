package com.as.casovi_plivanja.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.as.casovi_plivanja.R;
import com.as.casovi_plivanja.dialog.AboutDialog;
import com.as.casovi_plivanja.fragments.BlogFragment;
import com.as.casovi_plivanja.fragments.HomeFragment;
import com.as.casovi_plivanja.fragments.KinesitherapyFragment;
import com.as.casovi_plivanja.fragments.MenuFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            onNavigationItemSelected(navigationView.getMenu().getItem(0).setChecked(true));
        }

        LinearLayout nav_footer = findViewById(R.id.footer_text);

        nav_footer.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+381641494033"));
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right);
        });

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.change_lang:
                showChangeLang();
                Toast.makeText(this, "Change language ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.about:
                Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();
                AboutDialog dialog = new AboutDialog(MainActivity.this);
                dialog.show();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    public void showChangeLang() {
        final String[] listItem = {"Serbian", "English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Languages...");
        mBuilder.setSingleChoiceItems(listItem, -1, (dialogInterface, i) -> {
            if (i == 0) {
                setLocate(MainActivity.this, "sr");
                recreate();
            }
            if (i == 1) {
                setLocate(MainActivity.this, "en");
                recreate();
            }
            dialogInterface.dismiss();
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    public void setLocate(Activity activity, String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);

        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_info) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new HomeFragment())
                    .commit();
        } else if (id == R.id.menu) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new MenuFragment())
                    .commit();
        } else if (id == R.id.kinesi) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new KinesitherapyFragment())
                    .commit();
        } else if (id == R.id.blog) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new BlogFragment())
                    .commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}