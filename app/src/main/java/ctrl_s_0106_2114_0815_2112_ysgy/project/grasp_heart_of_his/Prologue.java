package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.util.Scanner;

public class Prologue extends AppCompatActivity {
    TextView chapterText;
    ImageView characterImg;
    Button chatBox, nameBox, backBtn;
    DBHelper dbHelper;
    SQLiteDatabase database;
    int chapter, answer, resNum=0;
    View questionDlog;
    LinearLayout story;
    InputStream inputText;
    Scanner sc;
    int[] backgroundList = {R.drawable.school_background, R.drawable.book_background, R.drawable.the_messenger_of_god, R.drawable.mystery_background};
    int[] prologueList = {R.raw.prologue1, R.raw.prologue2, R.raw.prologue3, R.raw.prologue4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        MacroClass macroClass;
        chapterText = findViewById(R.id.chapter_text);
        characterImg = findViewById(R.id.character_img);
        chatBox = findViewById(R.id.chat_box);
        nameBox = findViewById(R.id.name_box);
        backBtn = findViewById(R.id.back_btn);
        story = findViewById(R.id.story_layout);
        story.setBackgroundResource(R.drawable.school_background);
        this.chapter = storyListActivity.chapter;
        chapterText.setText("Prologue");
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        database.rawQuery("SELECT * FROM chapterTable WHERE chapter_no ="+chapter+";",null);
        backBtn.setOnClickListener(backOnClickListener);
        //test code
        macroClass = new MacroClass();
        inputText = getResources().openRawResource(prologueList[resNum++]);
        sc = new Scanner(inputText, "UTF-8");
        nameBox.setText(sc.nextLine());
        chatBox.setText(sc.nextLine());
        chatBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sc.hasNextLine()){
                    String name = sc.nextLine();
                    storyMacro(name);
                    chatBox.setText(sc.nextLine());
                }
                else if(resNum < prologueList.length){
                    story.setBackgroundResource(backgroundList[resNum]);
                    inputText = getResources().openRawResource(prologueList[resNum++]);
                    sc = setScanner(inputText);
                }else{

                }
            }
        });
//        inputText = getResources().openRawResource(R.raw.prologue2);
//        macroClass.storyMacro(inputText, characterImg, nameBox, chatBox, R.drawable.book_background, story);
//        questionDlog = View.inflate(Prologue.this, R.layout.question_dlog, null);
//        AlertDialog.Builder dlg = new AlertDialog.Builder(Prologue.this);
    }
    public Scanner setScanner(InputStream inputText){
        Scanner sc = new Scanner(inputText, "UTF-8");
        return sc;
    }
    public void storyMacro(String name){
        switch(name){
            case "JAVA":
                characterImg.setImageResource(R.drawable.java);
                nameBox.setText("한자바");
                break;
            case "C":
                characterImg.setImageResource(R.drawable.c);
                nameBox.setText("박시언");
                break;
            case "C++":
                characterImg.setImageResource(R.drawable.cpp);
                nameBox.setText("박시플");
                break;
            case "C#":
                characterImg.setImageResource(R.drawable.cs);
                nameBox.setText("박시샵");
                break;
            case "Python":
                characterImg.setImageResource(R.drawable.py);
                nameBox.setText("파이썬");
                break;
            case "백히진":
                characterImg.setImageResource(R.drawable.clear);
                break;
            default:
                nameBox.setText(name);
                break;
        }
    }
    View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(Prologue.this);
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
