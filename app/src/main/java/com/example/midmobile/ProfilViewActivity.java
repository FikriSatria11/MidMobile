package com.example.midmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.exceptions.RealmMigrationNeededException;

public class ProfilViewActivity extends AppCompatActivity {
    String id;
    String name;
    String address;
    String ttl_place, ttl_date, ttl_month, ttl_year;
    String religion;
    String email;
    boolean isMale;

    TextView tv_name, tv_phone, tv_address, tv_birthday, tv_religion, tv_email, tv_gender;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_view);

        tv_name = findViewById(R.id.name);
        tv_phone = findViewById(R.id.phone);
        tv_address = findViewById(R.id.address);
        tv_religion = findViewById(R.id.religion);
        tv_email = findViewById(R.id.email);
        tv_birthday = findViewById(R.id.birthday);
        tv_gender = findViewById(R.id.gender);

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
        }
        //Toast.makeText(this, id, Toast.LENGTH_LONG).show();

        try {
            realm = Realm.getDefaultInstance();
        } catch (RealmMigrationNeededException r) {
            Realm.deleteRealm(realm.getDefaultConfiguration());
            realm = Realm.getDefaultInstance();
        }

        UserModel user = realm.where(UserModel.class).equalTo("id", id).findFirst();
        if (user != null) {
            name = user.getName();
            isMale = user.getGender();
            address = user.getAddress();
            religion = user.getReligion();
            ttl_place = user.getTtl_place();
            ttl_date = user.getTtl_date();
            ttl_month = user.getTtl_month();
            ttl_year = user.getTtl_year();
            email = user.getEmail();
            String gender = isMale ? "Pria" : "Wanita";

            tv_name.setText(name);
            tv_phone.setText(id);
            tv_address.setText(address);
            tv_religion.setText(religion);
            tv_email.setText(email);
            tv_birthday.setText(ttl_place+" "+ttl_date+" "+ttl_month+" "+ttl_year);
            tv_gender.setText(gender);
        }
    }

    public void editProfil(View view) {
        Intent intent = new Intent(this, EditProfilActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
        this.finish();
    }

    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void delete(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        realm.beginTransaction();
        UserModel user = realm.where(UserModel.class).equalTo("id", id).findFirst();
        user.deleteFromRealm();
        realm.commitTransaction();
        this.finish();
        Toast.makeText(this, id+" has delete from database", Toast.LENGTH_LONG).show();
    }


}
