package com.arcsoft.sdk_demo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SqlEdit extends Activity {

    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_edit);
        dbHelper =new MyDatabaseHelper(this,"PersonStroe.db",null,1);

        final EditText tv1 = (EditText)findViewById(R.id.name_input);
        final EditText tv2 = (EditText)findViewById(R.id.content_input);
        final EditText tv3 = (EditText)findViewById(R.id.name_query);
        final EditText tv4 = (EditText)findViewById(R.id.name_delete);



        Button addData = (Button)findViewById(R.id.button_add);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                String name = tv1.getText().toString();
                String content = tv2.getText().toString();
//                values.put("name","刘德华");
//                values.put("content","他是个歌手也是个演员");
//                db.insert("Person",null,values);
//                values.clear();
//                values.put("name","张学友");
//                values.put("content","他是歌神");
//                db.insert("Person",null,values);
//                values.clear();
//                values.put("name","郭富城");
//                values.put("content","他是不是歌神");
//                db.insert("Person",null,values);

                values.put("name",name);
                values.put("content",content);
                db.insert("Person",null,values);
                values.clear();
                Toast.makeText(SqlEdit.this,"ADD SQL",Toast.LENGTH_SHORT).show();
                if(tv1!=null){
                    tv1.getText().clear();
                }
                if(tv2!=null){
                    tv2.getText().clear();
                }
            }
        });

        Button updateData = (Button)findViewById(R.id.button_update);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                String nameChange = tv1.getText().toString();
                String contentChange = tv2.getText().toString();
                values.put("content",contentChange);
                db.update("Person", values,"name = ?",new String[]{nameChange});
                values.clear();
                Log.d("SqlEdit","更改成功" );
                Toast.makeText(SqlEdit.this,"CHANGE SQL",Toast.LENGTH_SHORT).show();
                if(tv1!=null){
                    tv1.getText().clear();
                }
                if(tv2!=null){
                    tv2.getText().clear();
                }
            }
        });

        Button deleteData = (Button)findViewById(R.id.button_delete);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String itemdelete = tv4.getText().toString();
                Log.d("SqlEdit",itemdelete);
                db.delete("Person","name = ?",new String[]{itemdelete});
                Toast.makeText(SqlEdit.this,"DELETE",Toast.LENGTH_SHORT).show();
                if(tv4!=null){
                    tv4.getText().clear();
                }

            }
        });

//        final EditText tv3 = (EditText)findViewById(R.id.name_query);
        Button queryData = (Button)findViewById(R.id.button_query);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String nameQuery = tv3.getText().toString();
                Cursor cursor = db.query("Person",new String[]{"name","content"},"name = ?",new String[]{nameQuery},null,null,null);
                //查询方法
//                Log.d("SqlEdit",nameQuery);

                Log.d("SqlEdit","开始查询" );
                if(cursor.moveToFirst()){
                    do{
                        final String name = cursor.getString(cursor.getColumnIndex("name"));
                        final String content = cursor.getString(cursor.getColumnIndex("content"));
//                        Log.d("SqlEdit","Person name is" + name);
//                        Log.d("SqlEdit","content is" + content);
//                        Button buttonin= (Button)findViewById(R.id.button_query);
//                        buttonin.setOnClickListener(new View.OnClickListener() {
//                                                        @Override
//                                                        public void onClick(View v) {
//                                                            Intent intent = new Intent(SqlEdit.this, ShowInformation.class);
//                                                            intent.putExtra("names", name);
//                                                            intent.putExtra("infor", content);
//                                                            startActivity(intent);
//                                                        }
//                        });


                        Intent intent = new Intent(SqlEdit.this, ShowInformation.class);
                        intent.putExtra("names", name);
                        intent.putExtra("infor", content);
                        startActivity(intent);
                        Toast.makeText(SqlEdit.this,"Query SQL",Toast.LENGTH_SHORT).show();

//                        String content = cursor.getString(cursor.getColumnIndex("content"));
//                        Log.d("SqlEdit","Person name is" + name);
//                        Log.d("SqlEdit","content is" + content);
                    }while(cursor.moveToNext());
                }
                else {
                    Toast.makeText(SqlEdit.this,"查询失败，数据不存在",Toast.LENGTH_SHORT).show();
                }

                if(tv3!=null){
                    tv3.getText().clear();
                }

                cursor.close();
            }
        });
    }
}
