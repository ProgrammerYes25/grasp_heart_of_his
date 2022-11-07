package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {
    TextView chapterText;
    ImageView characterImg;
    Button chatBox, nameBox, backBtn;
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
        backBtn = findViewById(R.id.back_btn);
//      this.chapter = storyListActivity.chapter;
        chapter = 1;
        chapterText.setText("Chapter "+chapter);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        database.rawQuery("SELECT * FROM userTable WHERE chapter_no ="+chapter+";",null);
        backBtn.setOnClickListener(backOnClickListener);

    }
    View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(StoryActivity.this);
            dlg.setTitle("확인");
            dlg.setMessage("지금 나가면 현재 진행 사항이 저장 되지 않습니다.");
            dlg.setPositiveButton("나가기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent backIntent = new Intent(getApplicationContext(),storyListActivity.class);
                    startActivity(backIntent);
                }
            });
            dlg.setNegativeButton("게임으로 돌아가기",null);
            dlg.show();
        }
    };
}