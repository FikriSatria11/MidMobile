package com.example.midmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);

        try {
            realm = Realm.getDefaultInstance();
        } catch (RealmMigrationNeededException r) {
            Realm.deleteRealm(realm.getDefaultConfiguration());
            realm = Realm.getDefaultInstance();
        }
    }

    public void loginButton(View view) {
        String input_phone = ((TextView)findViewById(R.id.phone)).getText().toString();
        String input_password = ((TextView)findViewById(R.id.password)).getText().toString();
        UserModel user = realm.where(UserModel.class).equalTo("id", input_phone).findFirst();
        if (user != null) {
            String user_password = user.getPassword();
            if (user_password.equals(input_password)) {
                Intent intent = new Intent(this, ProfilViewActivity.class);
                intent.putExtra("id", input_phone);
                startActivity(intent);
                this.finish();
            } else {
                Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Phone number not registered", Toast.LENGTH_SHORT).show();
        }
//        realm.beginTransaction();
//        user.deleteAllFromRealm();
//        realm.commitTransaction();
    }

    public void registerPage(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        this.finish();
    }
}
