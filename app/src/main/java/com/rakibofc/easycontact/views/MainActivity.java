package com.rakibofc.easycontact.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import android.util.Pair;
import android.view.View;
import android.view.ViewTreeObserver;

import com.rakibofc.easycontact.R;
import com.rakibofc.easycontact.adapters.ContactItemAdapter;
import com.rakibofc.easycontact.databinding.ActivityMainBinding;
import com.rakibofc.easycontact.models.ContactData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // Test RecyclerView
        List<ContactData> contactDataLists = new ArrayList<>();
        contactDataLists.add(new ContactData(getContactImage(), "Rakibul Islam", "+8801232323233"));
        contactDataLists.add(new ContactData(getContactImage(), "Sakib Al Hasan", "+8801232323234"));
        contactDataLists.add(new ContactData(getContactImage(), "Yamin Hasan", "+8801232323232"));
        contactDataLists.add(new ContactData(getContactImage(), "Mamun Al Hasan", "+8801232523232"));
        contactDataLists.add(new ContactData(getContactImage(), "Rakibul Islam", "+8801232323233"));
        contactDataLists.add(new ContactData(getContactImage(), "Sakib Al Hasan", "+8801232323234"));
        contactDataLists.add(new ContactData(getContactImage(), "Yamin Hasan", "+8801232323232"));
        contactDataLists.add(new ContactData(getContactImage(), "Mamun Al Hasan", "+8801232523232"));
        contactDataLists.add(new ContactData(getContactImage(), "Rakibul Islam", "+8801232323233"));
        contactDataLists.add(new ContactData(getContactImage(), "Sakib Al Hasan", "+8801232323234"));
        contactDataLists.add(new ContactData(getContactImage(), "Yamin Hasan", "+8801232323232"));
        contactDataLists.add(new ContactData(getContactImage(), "Mamun Al Hasan", "+8801232523232"));

        ContactItemAdapter itemAdapter = new ContactItemAdapter(this, contactDataLists);
        mBinding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvContacts.setAdapter(itemAdapter);

        /* *
        mBinding.rvContacts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                // Shrink FAB button when scrolling top to bottom, otherwise extend
                if (dy < 0 || dy == 0) {
                    mBinding.fabBtnAdd.extend();
                } else {
                    mBinding.fabBtnAdd.shrink();
                }
            }
        });
        */

        mBinding.nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            private int oldScrollY = 0;

            @Override
            public void onScrollChanged() {

                int scrollY = mBinding.nestedScrollView.getScrollY();

                // Shrink FAB button when scrolling top to bottom, otherwise extend
                if (scrollY > oldScrollY) {

                    // Scrolling down
                    mBinding.fabBtnAdd.shrink();

                } else if (scrollY < oldScrollY) {

                    // Scrolling up
                    mBinding.fabBtnAdd.extend();
                }
                oldScrollY = scrollY;
            }
        });

        // Fab button click listener
        mBinding.fabBtnAdd.setOnClickListener(v -> {

            Intent contactIntent = new Intent(this, ContactActivity.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Pair<View, String> pairs = new Pair<>(mBinding.fabBtnAdd, getString(R.string.fab_to_contact_trans));
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(contactIntent, options.toBundle());

            } else startActivity(contactIntent);
        });
    }

    private Bitmap getContactImage() {

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.user_256dp, null);
        return ((BitmapDrawable) Objects.requireNonNull(drawable)).getBitmap();
    }
}