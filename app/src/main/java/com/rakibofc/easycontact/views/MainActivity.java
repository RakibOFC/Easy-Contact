package com.rakibofc.easycontact.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.rakibofc.easycontact.R;
import com.rakibofc.easycontact.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.fabBtnAdd.setOnClickListener(v -> {

            Intent contactIntent = new Intent(this, ContactActivity.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Pair<View, String> pairs = new Pair<>(mBinding.fabBtnAdd, getString(R.string.fab_to_contact_trans));
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(contactIntent, options.toBundle());

            } else startActivity(contactIntent);
        });
    }
}