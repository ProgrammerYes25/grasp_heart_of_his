package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
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

public class diaryListActivity extends AppCompatActivity {
    ImageView backBtn;
    DBHelper dbHelper;
    SQLiteDatabase db;
    TextView guideText;
    ArrayList diaryList;
    MediaPlayer diaryPlayer;
    ArrayAdapter<String> diaryAdapter;
    ListView diaryListView;
    static int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);
        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backIntent);
                finish();
            }
        });
        guideText = findViewById(R.id.guide_text);
        diaryListView = findViewById(R.id.diary_list_view);
        diaryList = new ArrayList();
        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();
//        db.execSQL("UPDATE userTable SET total_likability = 0 WHERE chapter_no = 1");
//        Cursor cursor;
//        cursor = db.rawQuery("SELECT * FROM userTable WHERE total_likability != 0", null);
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM chapterTable", null);
        setDiaryList(cursor);
        diaryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, diaryList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(20.0f);
                tv.setBackgroundColor(Color.rgb(186,207,255));
                return view;
            }
        };
        diaryListView.setAdapter(diaryAdapter);
        diaryListView.setOnItemClickListener(diItemClickListener);
    }
    AdapterView.OnItemClickListener diItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int day, long id) {
            dbHelper = new DBHelper(diaryListActivity.this);
            db = dbHelper.getReadableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM userTable;", null);
            cursor.moveToFirst();
            int finishDay = 1;
            if(day+1 <= finishDay && day+1> cursor.getInt(2)-1){
                Toast.makeText(getApplicationContext(),"?????? ????????? ???????????????. ?????? ???????????? ???????????????.", Toast.LENGTH_SHORT).show();
            }else if(day+1 > finishDay){
                Toast.makeText(getApplicationContext(),"???????????? ?????? ?????????.", Toast.LENGTH_SHORT).show();
            }
            else {
                diaryListActivity.day = day;
                Intent DiaryIntent = new Intent(getApplicationContext(), DiaryActivity.class);
                startActivity(DiaryIntent);
            }
        }
    };
    public void setDiaryList(Cursor cursor){
        cursor.moveToNext();
        while (cursor.moveToNext()) {
            diaryList.add("D+" + cursor.getInt(0)+"Day");
        }
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}