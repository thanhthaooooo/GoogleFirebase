package com.example.learnfirebase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;


public class DetailContactActivity extends AppCompatActivity {
    EditText edtId, edtName, edtPhone, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);
        addViews();
        getContactDetail();
    }

    private void addViews() {
        edtId = findViewById(R.id.edtContactId);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
    }

    private void getContactDetail() {
        Intent intent = getIntent();
        final String key = intent.getStringExtra("KEY");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("contacts");
        myRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    HashMap<String, Object> hashMap = (HashMap<String, Object>) dataSnapshot.getValue();
                    edtId.setText(key);
                    edtName.setText(hashMap.get("name").toString());
                    edtEmail.setText(hashMap.get("email").toString());
                    edtPhone.setText(hashMap.get("phone").toString());
                } catch (Exception ex) {
                    Log.e("MY_ERROR", ex.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("MY_ERROR", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    public void processUpdateContact(View view) {
        String key = edtId.getText().toString();
        String phone = edtPhone.getText().toString();
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("contacts");
        myRef.child(key).child("phone").setValue(phone);
        myRef.child(key).child("email").setValue(email);
        myRef.child(key).child("name").setValue(name);
        finish();
    }

    public void processDeleteContact(View view) {
        String key = edtId.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("contacts");
        myRef.child(key).removeValue();
        finish();
    }
    public void backToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}





