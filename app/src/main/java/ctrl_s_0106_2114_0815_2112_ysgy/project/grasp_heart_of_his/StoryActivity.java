package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {
    TextView chapterText;
    ImageView characterImg;
    Button chatBox, nameBox;
    DBHelper dbHelper;
    SQLiteDatabase database;
    int chapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        chapterText = findViewById(R.id.chapter_text);
        characterImg = findViewById(R.id.character_img);
        chatBox = findViewById(R.id.chat_box);
        nameBox = findViewById(R.id.name_box);
//        this.chapter = storyListActivity.chapter;
        chapter = 1;

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        database.rawQuery("SELECT * FROM userTable WHERE chapter_no ="+chapter+";",null);

    }
}