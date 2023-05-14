package com.rakibofc.easycontact.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.rakibofc.easycontact.R;
import com.rakibofc.easycontact.adapter.ContactItemAdapter;
import com.rakibofc.easycontact.databinding.ActivityMainBinding;
import com.rakibofc.easycontact.model.ContactData;
import com.rakibofc.easycontact.model.ContactDbTable;
import com.rakibofc.easycontact.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactItemAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflates the layout XML file for the main activity and creates an instance of the binding class
        ActivityMainBinding mBinding = ActivityMainBinding.inflate(getLayoutInflater());

        // Initializes the ViewModel for the main activity using the ViewModelProvider
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Set content view
        setContentView(mBinding.getRoot());

        mainViewModel.getAllContacts().observe(this, contactDbTables -> {

            List<ContactData> contactDataItems = new ArrayList<>();

            for (ContactDbTable contactDbTable : contactDbTables) {
                contactDataItems.add(new ContactData(
                        contactDbTable.getContactId(),
                        getContactImage(contactDbTable.getContactPhoto()),
                        contactDbTable.getContactName(),
                        contactDbTable.getContactNo()));

            }

            ContactItemAdapter contactItemAdapter = new ContactItemAdapter(this, contactDataItems, this);
            mBinding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
            mBinding.rvContacts.setAdapter(contactItemAdapter);
        });

        // Menu bar item click listener
        mBinding.toolbar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.delete_all) {

                mainViewModel.deleteAllContact();
                Toast.makeText(this, R.string.all_contacts_delete_msg, Toast.LENGTH_SHORT).show();

            } else if (item.getItemId() == R.id.about) {
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            }
            return false;
        });

        // mainViewModel.addContact(new ContactDbTable(null, "Yamin Hasan", "+8801231232232"));

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

    private Bitmap getContactImage(byte[] contactPhoto) {

        return contactPhoto != null ?
                BitmapFactory.decodeByteArray(contactPhoto, 0, contactPhoto.length) :
                BitmapFactory.decodeResource(this.getResources(), R.drawable.user_64dp);
    }

    @Override
    public void onItemClick(ContactData item) {
        Log.e("ID", item.getContactId() + "\nName: " + item.getContactName() + ", Phone: " + item.getContactNumber());
    }
}