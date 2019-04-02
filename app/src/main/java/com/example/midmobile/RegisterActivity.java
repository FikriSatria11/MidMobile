package com.example.midmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

public class RegisterActivity extends AppCompatActivity {
    Realm realm;

    RealmResults<UserModel> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        try {
            realm = Realm.getDefaultInstance();
        } catch (RealmMigrationNeededException r) {
            Realm.deleteRealm(realm.getDefaultConfiguration());
            realm = Realm.getDefaultInstance();
        }
    }

    public void register(View view) {
        String phone = ((TextView)findViewById(R.id.phone)).getText().toString();
        String name = ((TextView)findViewById(R.id.name)).getText().toString();
        String password = ((TextView)findViewById(R.id.password)).getText().toString();
        RadioGroup genderRadio = findViewById(R.id.radio_group);
        RadioButton radio = (RadioButton) findViewById(genderRadio.getCheckedRadioButtonId());
        boolean isMale = radio.getText().toString().equals("Pria");

        UserModel model = new UserModel(phone, name, password);
        model.setGender(isMale);

        UserModel user = realm.where(UserModel.class).equalTo("id", phone).findFirst();
        if (user == null) {
            realm.beginTransaction();
            realm.copyToRealm(model);
            realm.commitTransaction();
            Toast.makeText(this, phone+" registered", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, phone+" already exists, try other phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void cek(View view) {
        user = realm.where(UserModel.class).findAll();
        String size = String.valueOf(user.size());
        Toast.makeText(this, size, Toast.LENGTH_LONG).show();
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
