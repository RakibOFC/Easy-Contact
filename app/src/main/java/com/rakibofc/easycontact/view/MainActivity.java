package com.rakibofc.easycontact.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Pair;
import android.view.View;
import android.view.ViewTreeObserver;

import com.rakibofc.easycontact.R;
import com.rakibofc.easycontact.adapter.ContactItemAdapter;
import com.rakibofc.easycontact.databinding.ActivityMainBinding;
import com.rakibofc.easycontact.model.ContactDbTable;
import com.rakibofc.easycontact.util.RoomDbHelper;
import com.rakibofc.easycontact.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflates the layout XML file for the main activity and creates an instance of the binding class
        ActivityMainBinding mBinding = ActivityMainBinding.inflate(getLayoutInflater());

        // Initializes the ViewModel for the main activity using the ViewModelProvider
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Set content view
        setContentView(mBinding.getRoot());

        mainViewModel.getContactList().observe(this, contactList -> {

            ContactItemAdapter contactItemAdapter = new ContactItemAdapter(this, contactList);
            mBinding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
            mBinding.rvContacts.setAdapter(contactItemAdapter);
        });

        /* * // This code section for FAB Button interact with RecyclerView
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

            Intent contactIntent = new Intent(this, AddContactActivity.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Pair<View, String> pairs = new Pair<>(mBinding.fabBtnAdd, getString(R.string.fab_to_contact_trans));
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(contactIntent, options.toBundle());

            } else startActivity(contactIntent);
        });
    }
}