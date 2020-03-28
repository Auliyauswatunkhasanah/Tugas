package com.example.tugass;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Calendar myCalendar;
    Button btnDaftar, btnKembali;
    DatePickerDialog.OnDateSetListener date;
    EditText nama_depan,
            nama_belakang,
            tempat_lahir,
            tanggal_lahir,
            alamat,
            telepon,
            email,
            password,
            konfirmasi_password;

    AwesomeValidation awesomeValidation;

    private Button getBtnDaftar;
    private Button showDialogButton;

    private boolean validateEmail(EditText textInputEmail) {
        String emailInput = textInputEmail.getText().toString();

        if (emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(this, "Email Valid", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Email Tidak Valid", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tanggal_lahir.setText(sdf.format(myCalendar.getTime()));
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //Set Title Dialog
        alertDialogBuilder.setTitle("Konfirmasi...");

        //Set Pesan Dialog
        alertDialogBuilder
                .setMessage("Apakah data yang Anda masukkan sudah benar?")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Jika tombol diklik, maka akan menutup activity
                        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Jika tombol diklik, akan menutup dialog dan tak terjadi apa-apa
                        dialog.cancel();
                    }
                });

        //Membuat Alert Dialog dari Builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        //Menampilkan Alert Dialog
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama_depan = findViewById(R.id.nama_depan);
        nama_belakang = findViewById(R.id.nama_belakang);
        tempat_lahir = findViewById(R.id.tempat_lahir);
        alamat = findViewById(R.id.alamat);
        telepon = findViewById(R.id.telepon);
        tanggal_lahir = findViewById(R.id.tanggal_lahir);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        konfirmasi_password = findViewById(R.id.konfirmasi_password);

        btnDaftar = findViewById(R.id.btnDaftar);
        btnKembali = findViewById(R.id.btnKembali);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //NamaDepan
        awesomeValidation.addValidation(this, R.id.nama_depan,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);

        //NamaBelakang
        awesomeValidation.addValidation(this, R.id.nama_belakang,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);

        //TempatLahir
        awesomeValidation.addValidation(this, R.id.tempat_lahir,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);

        //Alamat
        awesomeValidation.addValidation(this, R.id.alamat,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);

        //NoTelp
        awesomeValidation.addValidation(this, R.id.telepon,
                ".{12,}", R.string.invalid_phone);

        //Email
        awesomeValidation.addValidation(this, R.id.email,
                Patterns.EMAIL_ADDRESS, R.string.invalid_email);

        //Password
        awesomeValidation.addValidation(this, R.id.password,
                ".{9,}", R.string.invalid_password);

        //Repasword
        awesomeValidation.addValidation(this, R.id.konfirmasi_password,
                R.id.password, R.string.invalid_confirm);

    }
}
