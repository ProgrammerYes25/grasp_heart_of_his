package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    LinearLayout story;
    InputStream inputText;
    String userName;
    Scanner sc, scA;
    AlertDialog.Builder qdlg;
    AlertDialog dialog;
    Toast toast;
    int chapter, acount=0, resNum=0, questionNum=0, nowChapter = 1,likabilityPoint, getLikabilityPoint = 0, playChapter;
    int[] backgroundList = {R.drawable.main_background,R.drawable.school_background, R.drawable.book_background};
    int[] chapter01List = {R.raw.chapter1_1, R.raw.chapter1_2, R.raw.chapter1_3, R.raw.chapter1_3q1, R.raw.chapter1_3q2, R.raw.chapter1_4a3, R.raw.chapter1_4a2, R.raw.chapter1_4a1};
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
        story.setBackgroundResource(R.drawable.main_background);
        this.chapter = storyListActivity.chapter;
        chapterText.setText("Chapter "+chapter);
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        db.rawQuery("SELECT * FROM chapterTable WHERE chapter_no ="+chapter+";",null);
        Cursor cursor;
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
                    chatBox.setText(sc.nextLine());
                }
                else if(resNum < chapter01List.length){
                    if(resNum > 2 && resNum<5){
                        questionDlog = View.inflate(Chapter01.this, R.layout.question_dlog, null);
                        qdlg = new AlertDialog.Builder(Chapter01.this);
                        inputText = getResources().openRawResource(chapter01List[resNum++]);
                        scA = new Scanner(inputText, "UTF-8");
                        TextView questionText = questionDlog.findViewById(R.id.question_text);
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
                                    toast = Toast.makeText(getApplicationContext(),"정답입니다! ♡+"+answerLsit[1][questionNum++],Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
                                    toast.show();
                                    nameBox.setText(scA.nextLine());
                                    chatBox.setText(scA.nextLine());
                                    acount++;
                                    dialog.dismiss();
                                }
                                else{
                                    toast = Toast.makeText(getApplicationContext(),"틀렸습니다.. (답 : "+(answerLsit[0][questionNum++])+") ♡+"+0,Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
                                    toast.show();
                                    String in;
                                    in = scA.nextLine();
                                    in = scA.nextLine();
                                    dialog.dismiss();
                                }
                            }
                        });
                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(answerLsit[0][questionNum] == 2){
                                    getLikabilityPoint += answerLsit[1][questionNum];
                                    toast = Toast.makeText(getApplicationContext(),"정답입니다! ♡+"+answerLsit[1][questionNum++],Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
                                    toast.show();
                                    nameBox.setText(scA.nextLine());
                                    chatBox.setText(scA.nextLine());
                                    acount++;
                                    dialog.dismiss();
                                }
                                else{
                                    toast = Toast.makeText(getApplicationContext(),"틀렸습니다.. (답 : "+(answerLsit[0][questionNum++])+") ♡+"+0,Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
                                    toast.show();
                                    String in;
                                    in = scA.nextLine();
                                    in = scA.nextLine();
                                    dialog.dismiss();
                                }
                            }
                        });
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(answerLsit[0][questionNum] == 3){
                                    getLikabilityPoint += answerLsit[1][questionNum];
                                    toast = Toast.makeText(getApplicationContext(),"정답입니다! ♡+"+answerLsit[1][questionNum++],Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
                                    toast.show();
                                    nameBox.setText(scA.nextLine());
                                    chatBox.setText(scA.nextLine());
                                    acount++;
                                    dialog.dismiss();
                                }
                                else{
                                    toast = Toast.makeText(getApplicationContext(),"틀렸습니다.. (답 : "+(answerLsit[0][questionNum++])+") ♡+"+0,Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);
                                    toast.show();
                                    String in;
                                    in = scA.nextLine();
                                    in = scA.nextLine();
                                    nameBox.setText(scA.nextLine());
                                    chatBox.setText(scA.nextLine());
                                    dialog.dismiss();
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
                            Intent mainIntene = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainIntene);
                        }
                    });

                    endTitle = endDlog.findViewById(R.id.end_title);
                    endLikability = endDlog.findViewById(R.id.end_likability);
                    endTotal = endDlog.findViewById(R.id.end_total);
                    db.execSQL("UPDATE userTable SET likability = '"+(likabilityPoint+getLikabilityPoint)+"';");
                    db.execSQL("UPDATE chapterTable SET total_likability = '"+getLikabilityPoint+"' WHERE chapter_no = '"+nowChapter+"';");
                    endTitle.setText("♠Prologue 클리어♠");
                    endLikability.setText("♡ 받은 호감도: "+getLikabilityPoint);
                    endTotal.setText("♥ 총합 호감도: "+(likabilityPoint+getLikabilityPoint));

                    AlertDialog.Builder dlg = new AlertDialog.Builder(Chapter01.this);
                    dlg.setView(endDlog);
                    dlg.setCancelable(false);
                    dlg.show();
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
            case "한자바":
                characterImg.setImageResource(R.drawable.java);
                nameBox.setText("한자바");
                break;
            case "박시언":
                characterImg.setImageResource(R.drawable.c);
                nameBox.setText("박시언");
                break;
            case "박시은":
                characterImg.setImageResource(R.drawable.cpp);
                nameBox.setText("박시은");
                break;
            case "박시샤":
                characterImg.setImageResource(R.drawable.cs);
                nameBox.setText("박시샤");
                break;
            case "파이썬":
                characterImg.setImageResource(R.drawable.py);
                nameBox.setText("파이썬");
                break;
            case "백희진": case "???":
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
            dlg.setTitle("확인");
            dlg.setMessage("지금 나가면 현재 진행 사항이 저장 되지 않습니다.");
            dlg.setPositiveButton("나가기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(nowChapter<playChapter){
                        db.execSQL("UPDATE userTable SET likability = '"+0+"';");
                        Cursor cursorPoint= db.rawQuery("SELECT * FROM chapterTable WHERE chapter_no = "+nowChapter+";", null);
                        cursorPoint.moveToFirst();
                        int likability = cursorPoint.getInt(1);
                        db.execSQL("UPDATE userTable SET likability = '"+likability+"';");
                    }
                    Intent backIntent = new Intent(getApplicationContext(),storyListActivity.class);
                    startActivity(backIntent);
                }
            });
            dlg.setNegativeButton("게임으로 돌아가기",null);
            dlg.show();
        }
    };
}