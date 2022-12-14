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

import java.io.InputStream;

public class StoryActivity extends AppCompatActivity {
    TextView chapterText;
    ImageView characterImg;
    Button chatBox, nameBox, backBtn;
    DBHelper dbHelper;
    SQLiteDatabase database;
    int chapter, answer;
    View questionDlog;

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
        chapterText.setText("Chapter "+chapter);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        database.rawQuery("SELECT * FROM phapterTable WHERE chapter_no ="+chapter+";",null);
        backBtn.setOnClickListener(backOnClickListener);
        //test code
        MacroClass macroClass = new MacroClass();
        InputStream inputText = getResources().openRawResource(R.raw.chapter01_tip);
        //macroClass.storyMacro(inputText, characterImg, nameBox, chatBox);
        questionDlog = View.inflate(StoryActivity.this, R.layout.question_dlog, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(StoryActivity.this);
    }

    //InputStream inputText,
    public void auestionDlogMacro(View questionDialog, AlertDialog.Builder dlg){
        Button btn1 = questionDialog.findViewById(R.id.answer_1);
        dlg.setView(questionDialog);
        dlg.show();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = 1;
            }
        });
    }

    View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(StoryActivity.this);
            dlg.setMessage("?????? ????????? ?????? ?????? ????????? ?????? ?????? ????????????.");
            dlg.setPositiveButton("?????????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent backIntent = new Intent(getApplicationContext(),storyListActivity.class);
                    startActivity(backIntent);
                }
            });
            dlg.setNegativeButton("???????????? ????????????",null);
            dlg.show();
        }
    };
}