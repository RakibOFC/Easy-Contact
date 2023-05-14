package com.rakibofc.easycontact.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.rakibofc.easycontact.R;
import com.rakibofc.easycontact.databinding.ActivityAddContactBinding;
import com.rakibofc.easycontact.model.ContactDbTable;
import com.rakibofc.easycontact.viewmodel.AddContactViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddContactActivity extends AppCompatActivity {

    private AddContactViewModel addContactViewModel;
    private Bitmap contactImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAddContactBinding binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        addContactViewModel = new ViewModelProvider(this).get(AddContactViewModel.class);

        // Set content view
        setContentView(binding.getRoot());

        addContactViewModel.getContactImage().observe(this, bitmap -> {

            contactImageBitmap = bitmap != null ?
                    bitmap :
                    BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.add_photo);

            // Set image in ImageView
            binding.ivAddPhoto.setImageBitmap(contactImageBitmap);

            Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show();
        });

        binding.toolbar.setNavigationOnClickListener(v -> {

            // Back to the MainActivity
            this.onBackPressed();
        });

        binding.ivAddPhoto.setOnClickListener(v -> {

            Intent imagePickerIntent = new Intent(Intent.ACTION_PICK);
            imagePickerIntent.setType("image/*");
            someActivityResultLauncher.launch(imagePickerIntent);
        });

        binding.btnCancel.setOnClickListener(v -> {

            // Back to the MainActivity
            this.onBackPressed();
        });

        binding.btnSave.setOnClickListener(v -> {

            String name = binding.etAddName.getText().toString();
            String phone = binding.etAddPhone.getText().toString();

            if (!name.isEmpty() && !phone.isEmpty()) {

                // Save contact
                addContactViewModel.addContact(new ContactDbTable(getContactImage(contactImageBitmap), name, phone));
                finish();

            } else {
                Snackbar.make(binding.btnSave, "Please enter name and phone", Snackbar.LENGTH_SHORT).show();
            }

        });
    }

    private byte[] getContactImage(Bitmap contactImage) {

        if (contactImage == null) {
            return null;
        } else {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            contactImage.compress(Bitmap.CompressFormat.PNG, 100, stream);

            return stream.toByteArray();
        }
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    private final ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {

                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    assert data != null;
                    Uri imageUri = data.getData();

                    try {

                        InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        addContactViewModel.setContactImage(selectedImage);

                    } catch (FileNotFoundException e) {
                        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
}