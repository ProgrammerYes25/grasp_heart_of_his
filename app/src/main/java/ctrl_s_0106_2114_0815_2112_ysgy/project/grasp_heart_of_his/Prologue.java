package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
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
    TextView chapterText, endTitle, endLikability, endTotal;
    ImageView characterImg;
    MediaPlayer prologuePlayer;
    Button chatBox, nameBox, backBtn, endBtn;
    DBHelper dbHelper;
    SQLiteDatabase db;
    int chapter, resNum=0;
    View endDlog;
    LinearLayout story;
    InputStream inputText;
    String userName;
    Scanner sc;
    int[] backgroundList = {R.drawable.school_background, R.drawable.book_background, R.drawable.the_messenger_of_god, R.drawable.mystery_background};
    int[] prologueList = {R.raw.prologue1, R.raw.prologue2, R.raw.prologue3, R.raw.prologue4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        chapterText = findViewById(R.id.chapter_text);
        characterImg = findViewById(R.id.character_img);
        chatBox = findViewById(R.id.chat_box);
        nameBox = findViewById(R.id.name_box);
        backBtn = findViewById(R.id.back_btn);
        story = findViewById(R.id.story_layout);
        prologuePlayer = MediaPlayer.create(this, R.raw.chat_mp3);
        prologuePlayer.setLooping(true);
        prologuePlayer.start();
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM userTable;", null);
        cursor.moveToFirst();
        userName = cursor.getString(0);

        story.setBackgroundResource(R.drawable.school_background);
        this.chapter = storyListActivity.chapter;
        chapterText.setText("Prologue");
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        db.rawQuery("SELECT * FROM chapterTable WHERE chapter_no ="+chapter+";",null);

        backBtn.setOnClickListener(backOnClickListener);
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
                    db.execSQL("UPDATE userTable SET play_chapter = 1");
                    endDlog = View.inflate(Prologue.this, R.layout.end_dlog, null);
                    endBtn = endDlog.findViewById(R.id.end_btn);
                    endBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            prologuePlayer.stop();
                            Intent mainIntene = new Intent(getApplicationContext(), MainActivity.class);
                            setResult(RESULT_OK, mainIntene);
                            finish();
                        }
                    });
                    chatBox.setClickable(false);
                    endTitle = endDlog.findViewById(R.id.end_title);
                    endLikability = endDlog.findViewById(R.id.end_likability);
                    endTotal = endDlog.findViewById(R.id.end_total);
                    endTitle.setText("???Prologue ????????????");
                    endLikability.setText("??? ?????? ?????????: 0");
                    endTotal.setText("??? ?????? ?????????: "+cursor.getString(1));
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Prologue.this);
                    dlg.setView(endDlog);
                    dlg.setCancelable(false);
                    dlg.show();
                    //db.close();
                }
            }
        });
        //????????? ????????? close
//        cursor.close();
//        dbHelper.close();
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
                nameBox.setText("?????????");
                break;
            case "C":
                characterImg.setImageResource(R.drawable.c);
                nameBox.setText("?????????");
                break;
            case "C++":
                characterImg.setImageResource(R.drawable.cpp);
                nameBox.setText("?????????");
                break;
            case "C#":
                characterImg.setImageResource(R.drawable.cs);
                nameBox.setText("?????????");
                break;
            case "Python":
                characterImg.setImageResource(R.drawable.py);
                nameBox.setText("?????????");
                break;
            case "?????????": case "???":
                characterImg.setImageResource(R.drawable.clear);
                nameBox.setText(userName);
                break;
            default:
                nameBox.setText(name);
                break;
        }
    }
    View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder backDlg = new AlertDialog.Builder(Prologue.this);
            backDlg.setTitle("??????");
            backDlg.setMessage("?????? ????????? ?????? ?????? ????????? ?????? ?????? ????????????.");
            backDlg.setPositiveButton("?????????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    prologuePlayer.stop();
                    Intent backIntent = new Intent(getApplicationContext(),storyListActivity.class);
                    setResult(RESULT_OK, backIntent);
                    finish();
                }
            });
            backDlg.setNegativeButton("???????????? ????????????",null);
            backDlg.show();
        }
    };
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
