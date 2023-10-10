package com.as.casovi_plivanja.fragments;


import static java.util.Objects.requireNonNull;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import com.as.casovi_plivanja.R;
import com.as.casovi_plivanja.adapters.HomeSliderAdapter;


public class HomeFragment extends Fragment {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    Timer timer;
    int page_position = 0;
    private int dotscount;
    private ImageView[] dots;
    private final Integer[] images = {R.drawable.bazen1, R.drawable.bazen2, R.drawable.bazen3, R.drawable.bazen4, R.drawable.bazen5, R.drawable.bazen6, R.drawable.bazen7, R.drawable.bazen8, R.drawable.bazen9, R.drawable.bazen10, R.drawable.bazen11, R.drawable.bazen12, R.drawable.bazen13, R.drawable.bazen14, R.drawable.bazen15, R.drawable.bazen16, R.drawable.bazen17, R.drawable.bazen18, R.drawable.bazen19, R.drawable.bazen20};
    Button que_btn1, que_btn2, sendMail;
    TextView textContact, gym, address, my_contact, show_tv1, show_tv2, personName;
    EditText editTitle, editEmailBody;
    RelativeLayout callPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        requireActivity().setTitle(getString(R.string.app_name));

        textContact = rootView.findViewById(R.id.contact);
        textContact.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+381641494033"));
            startActivity(intent);
        });

        callPhone = rootView.findViewById(R.id.con1);
        callPhone.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+381641494033"));
            startActivity(intent);
        });

        gym = rootView.findViewById(R.id.gym);
        gym.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://nolimitgym.rs/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        address = rootView.findViewById(R.id.address);
        address.setOnClickListener(v -> {
            String url = "https://www.google.com/maps/place/No+Limit+Gym/@45.2412196,19.7566835,13z/data=!4m20!1m13!4m12!1m4!2m2!1d19.7812926!2d45.2299468!4e1!1m6!1m2!1s0x475b11644c526459:0x423907e63ffc1ef7!2sNo+Limit+Gym!2m2!1d19.8166904!2d45.25391!3m5!1s0x475b11644c526459:0x423907e63ffc1ef7!8m2!3d45.25391!4d19.8166904!16s%2Fg%2F11q3_594bc?entry=ttu";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        editTitle = rootView.findViewById(R.id.editTitle);
        editEmailBody = rootView.findViewById(R.id.editEmailBody);
        sendMail = rootView.findViewById(R.id.sendMail);

        sendMail.setOnClickListener(v -> {
            String title = editTitle.getText().toString().trim();
            String emailBody = editEmailBody.getText().toString().trim();
            String email = "casoviplivanjazb@gmail.com";

            Activity activity = getActivity();
            if (title.isEmpty()) {
                Toast.makeText(activity, "Field is empty!", Toast.LENGTH_SHORT).show();
            } else if (emailBody.isEmpty()) {
                Toast.makeText(activity, "Field is empty!", Toast.LENGTH_SHORT).show();

            } else {
                String mail = "mailto:" + email + "?&subject=" + Uri.encode(title) + "&body=" + Uri.encode(emailBody);
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(mail));

                try {
                    startActivity(Intent.createChooser(intent, "Send Mail"));
                } catch (Exception e) {
                    Toast.makeText(activity, "Exception" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                editTitle.setText("");
                editEmailBody.setText("");
            }
        });

        my_contact = rootView.findViewById(R.id.my_contact);
        my_contact.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://full-stack-portfolio-as.netlify.app/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        timer = new Timer();
        viewPager = rootView.findViewById(R.id.viewPager);
        sliderDotspanel = rootView.findViewById(R.id.SliderDots);

        HomeSliderAdapter viewPagerAdapter = new HomeSliderAdapter(getContext(), images);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint("UseRequireInsteadOfGet")
            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.non_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(requireNonNull(getContext()), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        scheduleSlider();

        show_tv1 = rootView.findViewById(R.id.show_tv1);
        show_tv2 = rootView.findViewById(R.id.show_tv2);

        que_btn1 = rootView.findViewById(R.id.que_btn1);
        que_btn2 = rootView.findViewById(R.id.que_btn2);

        personName = rootView.findViewById(R.id.person);
        Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.text);
        personName.startAnimation(a);

        que_btn1.setOnClickListener(new View.OnClickListener() {
            boolean visible;

            @Override
            public void onClick(View view) {

                visible = !visible;
                show_tv1.setVisibility(visible ? View.VISIBLE : View.GONE);
                // TODO: Animation
                Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.text);
                a.reset();
                if (visible) {
                    show_tv1.startAnimation(a);
                } else {
                    show_tv1.clearAnimation();
                }
            }
        });

        que_btn2.setOnClickListener(new View.OnClickListener() {
            boolean visible;

            @Override
            public void onClick(View view) {
                visible = !visible;
                show_tv2.setVisibility(visible ? View.VISIBLE : View.GONE);
                // TODO: Animation
                Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.text);
                a.reset();
                if (visible) {
                    show_tv2.startAnimation(a);
                } else {
                    show_tv2.clearAnimation();
                }
            }
        });
        return rootView;
    }

    public void scheduleSlider() {

        final Handler handler = new Handler();

        final Runnable update = () -> {
            if (page_position == dotscount) {
                page_position = 0;
            } else {
                page_position = page_position + 1;
            }
            viewPager.setCurrentItem(page_position, true);
        };

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 4000);
    }

    @Override
    public void onStop() {
        timer.cancel();
        super.onStop();
    }

    @Override
    public void onPause() {
        timer.cancel();
        super.onPause();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.home);
    }
}