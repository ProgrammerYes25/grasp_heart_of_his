package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Scanner;

public class Chapter01 extends AppCompatActivity {
    TextView chapterText, endTitle, endLikability, endTotal;
    ImageView characterImg;
    Button chatBox, nameBox, backBtn, endBtn;
    DBHelper dbHelper;
    SQLiteDatabase db;
    View endDlog, questionDlog;
    MediaPlayer chapter01Player;
    LinearLayout story;
    InputStream inputText;
    String userName;
    Scanner sc, scA;
    AlertDialog.Builder qdlg;
    AlertDialog dialog;
    Toast toast;
    int chapter, acount=0, resNum=0, questionNum=0, nowChapter = 1,likabilityPoint, getLikabilityPoint = 0, playChapter;
    int[] backgroundList = {R.drawable.main_background,R.drawable.school_background, R.drawable.book_background};
    int[] chapter01List = {R.raw.chapter01_1, R.raw.chapter01_2, R.raw.chapter01_3, R.raw.chapter01_3q1, R.raw.chapter01_3q2, R.raw.chapter01_3a3, R.raw.chapter01_3a2, R.raw.chapter01_3a1};
    int[][] answerLsit = {{2, 3},{3, 3}};

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
        chapter01Player = MediaPlayer.create(this, R.raw.chat_mp3);
        chapter01Player.setLooping(true);
        chapter01Player.start();
        story.setBackgroundResource(R.drawable.main_background);
        this.chapter = storyListActivity.chapter;
        chapterText.setText("Chapter "+chapter);
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cursor;
        //cursor = db.rawQuery("SELECT * FROM chapterTable WHERE chapter_no ="+chapter+";",null);
        cursor = db.rawQuery("SELECT * FROM userTable;", null);
        cursor.moveToFirst();
        userName = cursor.getString(0);
        likabilityPoint = cursor.getInt(1);
        playChapter = cursor.getInt(2);
        backBtn.setOnClickListener(backOnClickListener);
        inputText = getResources().openRawResource(chapter01List[resNum++]);
        sc = new Scanner(inputText, "UTF-8");
        nameBox.setText(sc.nextLine());
        chatBox.setText(sc.nextLine());
        chatBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sc.hasNextLine()){
                    String name = sc.nextLine();
                    storyMacro(name);
                    String text = sc.nextLine();
                    if(text.equals("????????????")){
                       text =  "???? ???? ?????? "+userName+"(???)?????? ??? ?????????!!";
                    }
                    chatBox.setText(text);
                }
                else if(resNum < chapter01List.length){
                    if(resNum > 2 && resNum<5){
                        chatBox.setClickable(false);
                        chapter01Player.stop();
                        chapter01Player = MediaPlayer.create(Chapter01.this, R.raw.question_mp3);
                        chapter01Player.setLooping(true);
                        chapter01Player.start();
                        questionDlog = View.inflate(Chapter01.this, R.layout.question_dlog, null);
                        qdlg = new AlertDialog.Builder(Chapter01.this);
                        inputText = getResources().openRawResource(chapter01List[resNum++]);
                        scA = new Scanner(inputText, "UTF-8");
                        TextView questionText = questionDlog.findViewById(R.id.name_text);
                        Button btn1 = questionDlog.findViewById(R.id.answer_1);
                        Button btn2 = questionDlog.findViewById(R.id.answer_2);
                        Button btn3 = questionDlog.findViewById(R.id.answer_3);
                        questionText.setText(scA.nextLine());
                        btn1.setText(scA.nextLine());
                        btn2.setText(scA.nextLine());
                        btn3.setText(scA.nextLine());
                        qdlg.setView(questionDlog);
                        qdlg.setCancelable(false);
                        dialog = qdlg.create();
                        dialog.show();
                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(answerLsit[0][questionNum] == 1){
                                    getLikabilityPoint += answerLsit[1][questionNum];
                                    toast = Toast.makeText(getApplicationContext(),"???????????????! ???+"+answerLsit[1][questionNum++],Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
                                    toast.show();
                                    nameBox.setText(scA.nextLine());
                                    chatBox.setText(scA.nextLine());
                                    acount++;
                                    dialog.dismiss();
                                    chatBox.setClickable(true);
                                    chapter01Player.stop();
                                    chapter01Player = MediaPlayer.create(Chapter01.this, R.raw.chat_mp3);
                                    chapter01Player.setLooping(true);
                                    chapter01Player.start();
                                }
                                else{
                                    toast = Toast.makeText(getApplicationContext(),"???????????????.. (??? : "+(answerLsit[0][questionNum++])+") ???+"+0,Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
                                    toast.show();
                                    String in;
                                    in = scA.nextLine();
                                    in = scA.nextLine();
                                    nameBox.setText(scA.nextLine());
                                    chatBox.setText(scA.nextLine());
                                    dialog.dismiss();
                                    chatBox.setClickable(true);
                                    chapter01Player.stop();
                                    chapter01Player = MediaPlayer.create(Chapter01.this, R.raw.chat_mp3);
                                    chapter01Player.setLooping(true);
                                    chapter01Player.start();
                                }
                            }
                        });
                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(answerLsit[0][questionNum] == 2){
                                    getLikabilityPoint += answerLsit[1][questionNum];
                                    toast = Toast.makeText(getApplicationContext(),"???????????????! ???+"+answerLsit[1][questionNum++],Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
                                    toast.show();
                                    nameBox.setText(scA.nextLine());
                                    chatBox.setText(scA.nextLine());
                                    acount++;
                                    dialog.dismiss();
                                    chatBox.setClickable(true);
                                    chapter01Player.stop();
                                    chapter01Player = MediaPlayer.create(Chapter01.this, R.raw.chat_mp3);
                                    chapter01Player.setLooping(true);
                                    chapter01Player.start();
                                }
                                else{
                                    toast = Toast.makeText(getApplicationContext(),"???????????????.. (??? : "+(answerLsit[0][questionNum++])+") ???+"+0,Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
                                    toast.show();
                                    String in;
                                    in = scA.nextLine();
                                    in = scA.nextLine();
                                    nameBox.setText(scA.nextLine());
                                    chatBox.setText(scA.nextLine());
                                    dialog.dismiss();
                                    chatBox.setClickable(true);
                                    chapter01Player.stop();
                                    chapter01Player = MediaPlayer.create(Chapter01.this, R.raw.chat_mp3);
                                    chapter01Player.setLooping(true);
                                    chapter01Player.start();
                                }
                            }
                        });
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(answerLsit[0][questionNum] == 3){
                                    getLikabilityPoint += answerLsit[1][questionNum];
                                    toast = Toast.makeText(getApplicationContext(),"???????????????! ???+"+answerLsit[1][questionNum++],Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
                                    toast.show();
                                    nameBox.setText(scA.nextLine());
                                    chatBox.setText(scA.nextLine());
                                    acount++;
                                    dialog.dismiss();
                                    chatBox.setClickable(true);
                                    chapter01Player.stop();
                                    chapter01Player = MediaPlayer.create(Chapter01.this, R.raw.chat_mp3);
                                    chapter01Player.setLooping(true);
                                    chapter01Player.start();
                                }
                                else{
                                    toast = Toast.makeText(getApplicationContext(),"???????????????.. (??? : "+(answerLsit[0][questionNum++])+") ???+"+0,Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
                                    toast.show();
                                    String in;
                                    in = scA.nextLine();
                                    in = scA.nextLine();
                                    nameBox.setText(scA.nextLine());
                                    chatBox.setText(scA.nextLine());
                                    dialog.dismiss();
                                    chatBox.setClickable(true);
                                    chapter01Player.stop();
                                    chapter01Player = MediaPlayer.create(Chapter01.this, R.raw.chat_mp3);
                                    chapter01Player.setLooping(true);
                                    chapter01Player.start();
                                }
                            }
                        });
                    }
                    else {
                        if(resNum>=5){
                            inputText = getResources().openRawResource(chapter01List[resNum+acount]);
                            sc = setScanner(inputText);
                            resNum = chapter01List.length ;
                        }else{
                            story.setBackgroundResource(backgroundList[resNum]);
                            inputText = getResources().openRawResource(chapter01List[resNum++]);
                            sc = setScanner(inputText);
                        }
                    }
                }else {
                    db.execSQL("UPDATE userTable SET play_chapter = 2");
                    endDlog = View.inflate(Chapter01.this, R.layout.end_dlog, null);
                    endBtn = endDlog.findViewById(R.id.end_btn);
                    endBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chapter01Player.stop();
                            Intent mainIntene = new Intent(getApplicationContext(), MainActivity.class);
                            setResult(RESULT_OK, mainIntene);
                            finish();
                        }
                    });
                    chatBox.setClickable(false);
                    endTitle = endDlog.findViewById(R.id.end_title);
                    endLikability = endDlog.findViewById(R.id.end_likability);
                    endTotal = endDlog.findViewById(R.id.end_total);
                    db.execSQL("UPDATE userTable SET likability = '"+(likabilityPoint+getLikabilityPoint)+"';");
                    db.execSQL("UPDATE chapterTable SET total_likability = '"+getLikabilityPoint+"' WHERE chapter_no = '"+nowChapter+"';");
                    endTitle.setText("???Prologue ????????????");
                    endLikability.setText("??? ?????? ?????????: "+getLikabilityPoint);
                    endTotal.setText("??? ?????? ?????????: "+(likabilityPoint+getLikabilityPoint));
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Chapter01.this);
                    dlg.setView(endDlog);
                    dlg.setCancelable(false);
                    dlg.show();
                    //db.close();
                }
            }
        });

//        //test code
//        InputStream inputText = getResources().openRawResource(R.raw.test);
//        //macroClass.storyMacro(inputText, characterImg, nameBox, chatBox);
//        questionDlog = View.inflate(Chapter01.this, R.layout.question_dlog, null);
//        AlertDialog.Builder dlg = new AlertDialog.Builder(Chapter01.this);
    }

    private Scanner setScanner(InputStream inputText) {
        Scanner sc = new Scanner(inputText, "UTF-8");
        return sc;
    }

    public void storyMacro(String name){
        switch(name){
            case "?????????":
                characterImg.setImageResource(R.drawable.java1);
                nameBox.setText("?????????");
                break;
            case "?????????":
                characterImg.setImageResource(R.drawable.c);
                nameBox.setText("?????????");
                break;
            case "?????????":
                characterImg.setImageResource(R.drawable.cpp);
                nameBox.setText("?????????");
                break;
            case "?????????":
                characterImg.setImageResource(R.drawable.cs);
                nameBox.setText("?????????");
                break;
            case "?????????":
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
            AlertDialog.Builder dlg = new AlertDialog.Builder(Chapter01.this);
            dlg.setTitle("??????");
            dlg.setMessage("?????? ????????? ?????? ?????? ????????? ?????? ?????? ????????????.");
            dlg.setPositiveButton("?????????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(nowChapter<playChapter){
                        Cursor cursorPoint = db.rawQuery("SELECT * FROM userTable;", null);
                        cursorPoint.moveToFirst();
                        int Likability = cursorPoint.getInt(1);
                        cursorPoint = db.rawQuery("SELECT * FROM chapterTable WHERE chapter_no = "+nowChapter+";", null);
                        cursorPoint.moveToFirst();
                        db.execSQL("UPDATE userTable SET likability = '"+(Likability+cursorPoint.getInt(1))+"';");
                    }
                    chapter01Player.stop();
                    Intent backIntent = new Intent(getApplicationContext(),storyListActivity.class);
                    setResult(RESULT_OK, backIntent);
                    finish();
                }
            });
            dlg.setNegativeButton("???????????? ????????????",null);
            dlg.show();
        }
    };
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}