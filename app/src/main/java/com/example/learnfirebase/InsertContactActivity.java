package com.example.learnfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertContactActivity extends AppCompatActivity {
    EditText edtId,edtName,edtPhone,edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_contact);
        addViews();

    }
    private void addViews() {
        edtId=findViewById(R.id.edtContactId);
        edtName=findViewById(R.id.edtName);
        edtPhone=findViewById(R.id.edtPhone);
        edtEmail=findViewById(R.id.edtEmail);
    }
    public void processInsertContact(View view) {
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("contacts");
            String contactId=edtId.getText().toString();
            String name = edtName.getText().toString();
            String phone = edtPhone.getText().toString();
            String email = edtEmail.getText().toString();
            myRef.child(contactId).child("phone").setValue(phone);
            myRef.child(contactId).child("email").setValue(email);
            myRef.child(contactId).child("name").setValue(name);
            finish();
        }
        catch (Exception ex) {
            Toast.makeText(this,"Error:"+ex.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
