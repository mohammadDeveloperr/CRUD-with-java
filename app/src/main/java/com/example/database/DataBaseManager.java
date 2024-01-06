package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseManager extends SQLiteOpenHelper {
    final static String DBName="Mydp.dp";
    final static int Version=1;

    private  static  final  String tableName="tbl_person";
    private  static  final  String dID="id";
    private  static  final  String dName="name";
    private  static  final  String dFamily="family";




    public DataBaseManager(@Nullable Context context) {
        super(context, DBName, null, Version);
        System.out.println("--------------------------------------------------");
        System.out.println("database created");
        Toast.makeText(context, "database created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
String CreateTable=
        "create TABLE "+tableName+"("+dID+" int PRIMARY KEY ,"+dName+" varchar(100),"+dFamily+" varchar(100))";
sqLiteDatabase.execSQL(CreateTable);
        Log.i("DBLog","table"+tableName+"Is Create!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void InsertPerson(Person person){
        try{
            System.out.println("========================================================================");
            System.out.println("hello in first method insert person");
            SQLiteDatabase sld=this.getWritableDatabase();
            ContentValues cv=new ContentValues();
            System.out.println("========================================================================");
            System.out.println("hello in between method insert person");

            cv.put(dID,person.Id);
            cv.put(dName,person.Name);
            cv.put(dFamily,person.Family);
            sld.insert(tableName,null,cv);
            sld.close();
            System.out.println("========================================================================");
            System.out.println("hello in the end method insert person");
        }catch (Exception e){
            System.out.println("========================================================================");
            System.out.println("========================================================================");
            System.out.println("========================================================================");
            System.out.println("========================================================================");
            System.out.println("========================================================================");
            System.out.println("hello in error method insert user");
            System.out.println(e);
        }


    }

    public Person GetPerson(int Id) {
        Person p = new Person();

        try {
            SQLiteDatabase sld = this.getReadableDatabase();

            String Query = "Select * from " + tableName + " where " + dID + " = " + Id;
            Cursor cur = sld.rawQuery(Query, null);

            if (cur.moveToFirst()) {
                p.Name = cur.getString(1);
                p.Family = cur.getString(2);

            }
            return p;
        } catch (Exception e) {
            System.out.println("``````````````````````````````````````````````````````````````````````````````````````");
            System.out.println("``````````````````````````````````````````````````````````````````````````````````````");

            System.out.println("``````````````````````````````````````````````````````````````````````````````````````");

            System.out.println(e);

        }

return p;
    }

    public void UpdatePerson(Person person){
        try{
            SQLiteDatabase sld=this.getWritableDatabase();
            ContentValues cv=new ContentValues();

            cv.put(dID,person.Id);
            cv.put(dName,person.Name);
            cv.put(dFamily,person.Family);

            sld.update(tableName,cv,dID+ " = "+person.Id,null);
            sld.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void DeletePerson(int personId){

        try{
            SQLiteDatabase sld=this.getWritableDatabase();

            sld.delete(tableName,dID+" = "+String.valueOf(personId),null);
            sld.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }
}

