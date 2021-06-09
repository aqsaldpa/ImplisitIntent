package com.example.implisit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnShare;
    Button btnAlarm;
    EditText etJam,etMenit,etDesc;

    final int REQUEST_CODE =111;
    Uri imageUri;
    private Button btnFoto;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        //SET LISTENER BUTTON SHARE

        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, "Praktikum Mobile Implicit Intent");
                startActivity(i.createChooser(i, "Share"));
            }
        });

        //SET LISTENER ALARM
        etJam = findViewById(R.id.etJam);
        etMenit = findViewById(R.id.etMenit);
        etDesc = findViewById(R.id.etDesc);

        btnAlarm = findViewById(R.id.btnAlarm);
        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jam = Integer.parseInt(etJam.getText().toString());
                int menit = Integer.parseInt(etMenit.getText().toString());
                String desc = String.valueOf(etDesc.getText());

                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_HOUR, jam);
                i.putExtra(AlarmClock.EXTRA_MINUTES, menit);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, desc);

                if (jam <= 24 && menit <= 60) {
                    startActivity(i);
                } else
                    Toast.makeText(getApplicationContext(), "Waktu Salah", Toast.LENGTH_SHORT).show();

            }
        });

        //SET LISTENER Ambil Foto

        btnFoto = findViewById(R.id.btnFoto);
        imageView = findViewById(R.id.hasilFoto);

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

}
