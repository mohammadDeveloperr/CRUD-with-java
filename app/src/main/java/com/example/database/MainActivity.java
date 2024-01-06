package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            DataBaseManager dbm=new DataBaseManager(this);
            SQLiteDatabase sld=dbm.getWritableDatabase();

            if(sld.isOpen()){

                Log.i("DBLog","OpenDB");
                sld.close();
            }


            Button insert=(Button) findViewById(R.id.insert);
            insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        EditText editId=(EditText) findViewById(R.id.id) ;
                        EditText editFirstName=(EditText) findViewById(R.id.firstName);
                        EditText editLastName=(EditText) findViewById(R.id.lastName);

                        String Id=editId.getText().toString();
                        String firstName=editFirstName.getText().toString();
                        String lastName=editLastName.getText().toString();
                        Person p=new Person();
                        p.Id=Integer.parseInt(Id);
                        p.Name=firstName;
                        p.Family=lastName;
                        dbm.InsertPerson(p);
                    }catch (Exception e){
                        System.out.println(e);
                    }

                }
            });


            Button show=(Button) findViewById(R.id.show);

            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        EditText editId=(EditText) findViewById(R.id.id) ;
                        EditText editFirstName=(EditText) findViewById(R.id.firstName);
                        EditText editLastName=(EditText) findViewById(R.id.lastName);

                        String Id=editId.getText().toString();
                        String firstName=editFirstName.getText().toString();
                        String lastName=editLastName.getText().toString();
                        Toast.makeText(MainActivity.this,Id,Toast.LENGTH_LONG).show();

                        Person p=dbm.GetPerson(Integer.parseInt(Id));

                        editFirstName.setText(p.Name);
                        editLastName.setText(p.Family);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            });


            Button update=(Button) findViewById(R.id.update);

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        EditText editId=(EditText) findViewById(R.id.id) ;
                        EditText editFirstName=(EditText) findViewById(R.id.firstName);
                        EditText editLastName=(EditText) findViewById(R.id.lastName);
                        String Id=editId.getText().toString();
                        String firstName=editFirstName.getText().toString();
                        String lastName=editLastName.getText().toString();
                        Person p=new Person();
                        p.Id=Integer.valueOf(Id);
                        p.Name=firstName;
                        p.Family=lastName;
                        dbm.UpdatePerson(p);
                    }catch (Exception e){
                        System.out.println(e);
                    }

                }
            });

            Button delete=(Button) findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        EditText editId=(EditText) findViewById(R.id.id) ;
                        String Id=editId.getText().toString();
                        dbm.DeletePerson(Integer.valueOf(Id));
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            });
        }

}