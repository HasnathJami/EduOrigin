package com.example.eduorigin.adminpanel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eduorigin.R;
import com.example.eduorigin.controllers.ApiController;
import com.example.eduorigin.models.ResponseModelBookLibraryBackup;
import com.example.eduorigin.registration.SignInActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPanelBookUploadActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;


    private Button adminLogOut;
    private TextView adminResponseWarning;
    private Toolbar toolbar;
    private Bitmap bitmap;

    private EditText adminBookName,adminBookDescription;
    private Button adminBrowseImageButton,adminUploadImageButton;
    private ImageView adminBookImage;
    private String encodeImageString,encodePdfString,pdfNameExtract;
    private Button adminBrowsePdfButton;
    private TextView adminPDFFileSelectionShowText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setTitle("Admin Panel");
        toolbar=findViewById(R.id.toolBarId);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_admin_panel_book_upload);

        bottomNavigationView = findViewById(R.id.bottomNavigationId);
        //frameLayout = findViewById(R.id.frameLayoutContainerId);


        adminBookName=findViewById(R.id.adminBookNameId);
        adminBookDescription=findViewById(R.id.adminBookDescriptionId);
        adminBookImage=findViewById(R.id.adminBookImageId);
        adminBrowseImageButton=findViewById(R.id.adminBrowseImageId);
        adminUploadImageButton=findViewById(R.id.adminBookUploadId);
        adminBrowsePdfButton=findViewById(R.id.adminBookPdfBrowseId);
        adminPDFFileSelectionShowText=findViewById(R.id.adminPDFFileSelectionShowTextId);




        bottomNavigationView.setSelectedItemId(R.id.menu_bookUploadAdmin);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            //Fragment store = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
//                    case R.id.menu_bookUploadAdmin:
//                        // store = new AdminBookUploadFragment();
//                        //  getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainerId, store).commit();
//                        break;


                    case R.id.menu_quizCreationAdmin:
                        // store = new AdminQuizCreationFragment();
                        // getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainerId, store).commit();

                        startActivity(new Intent(getApplicationContext(), AdminPanelCreateQuizActivity.class));
                        break;

                    case R.id.menu_Logout_admin:

                        SharedPreferences sp = getSharedPreferences("credential", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.remove("email");
                        editor.remove("password");
                        editor.commit();
                        editor.apply();

                        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                        finish();
                        break;


                }

                // getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainerId,store).commit();
                return true;

            }
        });



        // userExistenceCheck();

        adminBrowseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(AdminPanelBookUploadActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Browse Image"),1);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });






        adminBrowsePdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(AdminPanelBookUploadActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent=new Intent(Intent.ACTION_PICK);
                                //intent.setType("pdf/*");
                                intent.setType("application/pdf");
                                startActivityForResult(Intent.createChooser(intent,"Browse PDF"),2);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();

            }
        });



        adminUploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    if(!adminBookDescription.getText().toString().isEmpty() && !adminBookName
                            .getText().toString().isEmpty() && !encodeImageString.isEmpty() && !pdfNameExtract.isEmpty())
                        uploadDataToDB();

                    else
                    Toast.makeText(AdminPanelBookUploadActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();


                }catch (Exception e)
                {
                    //Log.d("excep",e.toString());
                    Toast.makeText(AdminPanelBookUploadActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();

                }



            }


        });








    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            Uri filepath=data.getData();
            try{

                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                adminBookImage.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);

            }catch (Exception e)
            {

            }
        }


        if(requestCode==2 && resultCode==RESULT_OK)
        {

            Uri filepath=data.getData();
            try{

//                InputStream inputStream=getContentResolver().openInputStream(filepath);
//                bitmap= BitmapFactory.decodeStream(inputStream);
//                adminBookImage.setImageBitmap(bitmap);
//                encodeBitmapImage(bitmap);
                  InputStream inputStream= AdminPanelBookUploadActivity.this.getContentResolver().openInputStream(filepath);
                  byte[] pdfInBytes=new byte[inputStream.available()];
                  inputStream.read(pdfInBytes);
                  encodePdfString=Base64.encodeToString(pdfInBytes,Base64.DEFAULT);

                  String pdfName=filepath.toString();
                  pdfNameExtract=pdfName.substring(pdfName.lastIndexOf("/")+1);
                  adminPDFFileSelectionShowText.setText(pdfNameExtract);

            }catch (Exception e)
            {

            }


        }

        super.onActivityResult(requestCode, resultCode, data);
    }



    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytesofImage=byteArrayOutputStream.toByteArray();
        encodeImageString=android.util.Base64.encodeToString(bytesofImage, Base64.DEFAULT);

    }

    // private void  userExistenceCheck()
    //{
    //  SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
    // if(sp.contains("email"))
    //  {
    //works like navigation drawer username appears when user is login
    //adminResponseWarning.setText(sp.getString("email",""));
    //startActivity(new Intent(getApplicationContext(),));
    //   }
    //else{
    //    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
    // }
    //}







    private void uploadDataToDB() {

        String name=adminBookName.getText().toString().trim();
        String description=adminBookDescription.getText().toString().trim();
        String image=encodeImageString.trim();
        String pdf=encodePdfString.trim();

        Call<ResponseModelBookLibraryBackup> call= ApiController.getInstance().getapi().sendBookDataFromAdminBackup(name,description,image,pdf);

        call.enqueue(new Callback<ResponseModelBookLibraryBackup>() {
            @Override
            public void onResponse(Call<ResponseModelBookLibraryBackup> call, Response<ResponseModelBookLibraryBackup> response) {

                Toast.makeText(getApplicationContext(), "Book uploaded successfully", Toast.LENGTH_SHORT).show();

                adminBookName.setFocusableInTouchMode(true);
                adminBookDescription.setFocusable(false);
                adminBookDescription.setFocusableInTouchMode(true);
                adminBookName.setText("");
                adminBookDescription.setText("");
                adminBookImage.setImageBitmap(null);
                adminBookImage.setImageResource(R.drawable.ic_upload_photo);
                adminPDFFileSelectionShowText.setText("");

            }

            @Override
            public void onFailure(Call<ResponseModelBookLibraryBackup> call, Throwable t) {

                //Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                adminBookName.setFocusableInTouchMode(true);
                adminBookDescription.setFocusable(false);
                adminBookDescription.setFocusableInTouchMode(true);
                adminBookName.setText("");
                adminBookDescription.setText("");
                adminBookImage.setImageBitmap(null);
                adminBookImage.setImageResource(R.drawable.ic_upload_photo);
                adminPDFFileSelectionShowText.setText("");

                Toast.makeText(getApplicationContext(), "Book uploaded successfully", Toast.LENGTH_SHORT).show();

            }
        });


    }



}