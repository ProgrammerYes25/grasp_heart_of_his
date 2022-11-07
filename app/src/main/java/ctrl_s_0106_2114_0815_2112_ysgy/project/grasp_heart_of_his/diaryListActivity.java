package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class diaryListActivity extends AppCompatActivity {
    ImageView backBtn;
    DBHelper dbHelper;
    SQLiteDatabase db;
    ArrayList diaryList;
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
            }
        });

        diaryListView = findViewById(R.id.diary_list_view);
        diaryList = new ArrayList();
        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();
        db.execSQL("UPDATE userTable SET total_likability = 0 WHERE chapter_no = 1");
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM userTable WHERE total_likability != 0", null);
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
        //diaryListView.setOnItemClickListener();
    }
    public void setDiaryList(Cursor cursor){
        while(cursor.moveToNext()){
            diaryList.add("D+Day"+cursor.getInt(0));
        }
    }
}