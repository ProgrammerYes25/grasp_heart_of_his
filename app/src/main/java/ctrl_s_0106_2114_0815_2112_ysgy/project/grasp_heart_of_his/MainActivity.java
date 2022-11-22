package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView storyBtn, diaryBtn;
    MediaPlayer mainPlayer;
    DBHelper dbHelper;
    SQLiteDatabase db;
    View nameDlog, setDlog;
    Button saveNameBtn, settingBtn;
    EditText editName;
    TextView likabilityText;
    AlertDialog.Builder checkDlg, setDlg;
    AlertDialog alertDialog, setDialog;

    private long backBtnTime = 0l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storyBtn = findViewById(R.id.story_btn);
        diaryBtn = findViewById(R.id.diary_btn);
        settingBtn = findViewById(R.id.setting_btn);
        settingBtn.setOnClickListener(settingOnClickListener);
        mainPlayer = MediaPlayer.create(this, R.raw.main_mp3);
        mainPlayer.setLooping(true);
        mainPlayer.start();
        likabilityText = findViewById(R.id.likability_text);
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM userTable;", null);
        setUsername(cursor);
        storyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPlayer.stop();
                Intent storyListIntene = new Intent(getApplicationContext(), storyListActivity.class);
                startActivity(storyListIntene);
                finish();
            }
        });
        diaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPlayer.stop();
                Intent diaryListIntene = new Intent(getApplicationContext(), diaryListActivity.class);
                startActivity(diaryListIntene);
                finish();
            }
        });
        //데이터 베이스 close
        //db.close();
    }
    public void setUsername(Cursor cursor){
        if(!(cursor.moveToNext())){
            nameDlog = View.inflate(MainActivity.this, R.layout.name_dlog, null);
            saveNameBtn = nameDlog.findViewById(R.id.save_name_btn);
            saveNameBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editName = nameDlog.findViewById(R.id.edit_name);
                    checkDlg = new AlertDialog.Builder(MainActivity.this);
                    checkDlg.setTitle("이 이름으로 하시겠습니까?");
                    checkDlg.setMessage("추후에 변경 가능합니다.");
                    checkDlg.setPositiveButton("계속하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String name = editName.getText().toString();
                            if(name.equals("")){
                                name = "백희진";
                            }
                            db.execSQL("INSERT INTO userTable VALUES('"+name+"',"+ 0+","+ 0+");");
                            alertDialog.dismiss();
                        }
                    });
                    checkDlg.setNegativeButton("다시확인", null);
                    checkDlg.setCancelable(false);
                    checkDlg.show();
                }
            });
            AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
            dlg.setView(nameDlog);
            dlg.setCancelable(false);
            alertDialog = dlg.create();
            alertDialog.show();
        }else {
            cursor.moveToFirst();
            likabilityText.setText(Integer.toString(cursor.getInt(1)));
        }
    }
    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
            finish();
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }


    }
    View.OnClickListener settingOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM userTable;",null);
            setDlog = View.inflate(MainActivity.this, R.layout.setting_dlog, null);
            setDlg = new AlertDialog.Builder(MainActivity.this);
            TextView nameText = setDlog.findViewById(R.id.name_text);
            TextView chapterText = setDlog.findViewById(R.id.chapter_text);
            Button resetNameBtn = setDlog.findViewById(R.id.reset_name_btn);
            Button resetBtn = setDlog.findViewById(R.id.reset_btn);
            Button backBtn =setDlog.findViewById(R.id.back_btn);
            cursor.moveToFirst();
            nameText.setText("이름: "+cursor.getString(0));
            chapterText.setText("이 세계에 온지 D+"+cursor.getInt(2)+"Day");
            setDlg.setView(setDlog);
            setDlg.setCancelable(false);
            setDialog = setDlg.create();
            setDialog.show();
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDialog.dismiss();
                }
            });
            resetBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editName = nameDlog.findViewById(R.id.edit_name);
                    checkDlg = new AlertDialog.Builder(MainActivity.this);
                    checkDlg.setTitle("정말 리셋 하시겠습니까?");
                    checkDlg.setPositiveButton("계속하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbHelper.onUpgrade(db, db.getVersion(), db.getVersion()+1);
                            Intent mainIntene = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainIntene);
                            Toast.makeText(MainActivity.this, "리셋 되었습니다.",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    checkDlg.setNegativeButton("다시확인", null);
                    checkDlg.setCancelable(false);
                    checkDlg.show();
                }
            });
            resetNameBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        nameDlog = View.inflate(MainActivity.this, R.layout.name_dlog, null);
                        saveNameBtn = nameDlog.findViewById(R.id.save_name_btn);
                        saveNameBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editName = nameDlog.findViewById(R.id.edit_name);
                                checkDlg = new AlertDialog.Builder(MainActivity.this);
                                checkDlg.setTitle("이 이름으로 하시겠습니까?");
                                checkDlg.setPositiveButton("계속하기", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String name = editName.getText().toString();
                                        if(name.equals("")){
                                            name = "백희진";
                                        }
                                        db.execSQL("UPDATE userTable SET user_name = '"+name+"';");
                                        Toast.makeText(MainActivity.this, "이름이 변경 되었습니다.",Toast.LENGTH_SHORT).show();
                                        alertDialog.dismiss();
                                        setDialog.dismiss();
                                    }
                                });
                                checkDlg.setNegativeButton("다시확인", null);
                                checkDlg.setCancelable(false);
                                checkDlg.show();
                            }
                        });
                        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setView(nameDlog);
                        dlg.setCancelable(false);
                        alertDialog = dlg.create();
                        alertDialog.show();
                }
            });
        }
    };

}