package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class storyListActivity extends AppCompatActivity {
    ImageView backBtn;
    DBHelper dbHelper;
    SQLiteDatabase db;
    ArrayList storyList;
    ArrayAdapter<String> storyAdapter;
    ListView storyListView;
    static int chapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_list);
        backBtn = findViewById(R.id.back_btn);
        storyListActivity.chapter = 0;
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backIntent);
                finish();
            }
        });
        storyListView = findViewById(R.id.story_list_view);
        storyList = new ArrayList();
        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM chapterTable;", null);
        setStoryList(cursor);
        storyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, storyList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(20.0f);
                tv.setBackgroundColor(Color.rgb(186,207,255));
                return view;
            }
        };
        storyListView.setAdapter(storyAdapter);
        storyListView.setOnItemClickListener(stListener);

        //????????? ????????? close
//        cursor.close();
//        db.close();
//        dbHelper.close();
    }
    AdapterView.OnItemClickListener stListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int itmeChapter, long id) {
            dbHelper = new DBHelper(storyListActivity.this);
            db = dbHelper.getReadableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM userTable;", null);
            cursor.moveToFirst();
            int finishCh = 1;
            if(itmeChapter <= finishCh && itmeChapter > cursor.getInt(2)) {
                Toast.makeText(getApplicationContext(),"?????? ????????? ???????????????. ?????? ???????????? ???????????????.", Toast.LENGTH_SHORT).show();
            }
            else if(itmeChapter > finishCh){
                Toast.makeText(getApplicationContext(),"???????????? ?????? ?????????.", Toast.LENGTH_SHORT).show();
            }
            else if(itmeChapter < cursor.getInt(2)) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(storyListActivity.this);
                dlg.setMessage("??? ???????????? ?????? ???????????? ???????????? ??? ???????????? ????????? ?????? ???????????? ?????????.");
                dlg.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Cursor cursorPoint = db.rawQuery("SELECT * FROM chapterTable WHERE chapter_no = " + itmeChapter + ";", null);
                        cursorPoint.moveToFirst();
                        int likability = cursorPoint.getInt(1);
                        cursorPoint = db.rawQuery("SELECT * FROM userTable;", null);
                        cursorPoint.moveToFirst();
                        int totLikability = cursorPoint.getInt(1);
                        db.execSQL("UPDATE userTable SET likability = '" + (totLikability - likability) + "';");
                        storyListActivity.chapter = itmeChapter;
                        Intent loadingStoryIntene = new Intent(getApplicationContext(), loadingStoryActivity.class);
                        startActivity(loadingStoryIntene);
                    }
                });
                dlg.setNegativeButton("??????", null);
                dlg.setCancelable(false);
                dlg.show();
            }
            else {
                storyListActivity.chapter = itmeChapter;
                Intent loadingStoryIntene = new Intent(getApplicationContext(), loadingStoryActivity.class);
                startActivity(loadingStoryIntene);
                //db.close();
            }

            //????????? ????????? close
//            cursor.close();
//            db.close();
//            dbHelper.close();
        }
    };
    public void setStoryList(Cursor cursor){
        while(cursor.moveToNext()){
            if(cursor.getInt(0)==0){
                storyList.add("Prologue");
                continue;
            }
            storyList.add("Chapter "+cursor.getInt(0));
        }
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}