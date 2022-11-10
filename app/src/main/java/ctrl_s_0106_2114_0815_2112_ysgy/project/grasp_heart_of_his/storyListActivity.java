package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AppCompatActivity;

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
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backIntent);
            }
        });
        storyListView = findViewById(R.id.story_list_view);
        storyList = new ArrayList();
        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM userTable;", null);
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
    }
    AdapterView.OnItemClickListener stListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int chapter, long id) {
            int finishCh = 1;
            if(chapter+1 > finishCh) {
                Toast.makeText(getApplicationContext(),"업데이트 예정 입니다.", Toast.LENGTH_SHORT).show();
            }
            else {
                storyListActivity.chapter = chapter+1;
                Intent loadingStoryIntene = new Intent(getApplicationContext(), loadingStoryActivity.class);
                startActivity(loadingStoryIntene);
            }
        }
    };
    public void setStoryList(Cursor cursor){
        while(cursor.moveToNext()){
            storyList.add("Chapter "+cursor.getInt(0));
        }
    }
}