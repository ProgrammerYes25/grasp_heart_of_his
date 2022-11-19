package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView storyBtn, diaryBtn;
    DBHelper dbHelper;
    SQLiteDatabase db;
    View nameDlog;
    Button saveNameBtn;
    EditText editName;
    TextView likabilityText;
    AlertDialog.Builder checkDlg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storyBtn = findViewById(R.id.story_btn);
        diaryBtn = findViewById(R.id.diary_btn);
        likabilityText = findViewById(R.id.likability_text);
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM userTable;", null);
        setUsername(cursor);
        likabilityText.setText(Integer.toString(cursor.getInt(1)));
        storyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent storyListIntene = new Intent(getApplicationContext(), storyListActivity.class);
                startActivity(storyListIntene);
            }
        });
        diaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diaryListIntene = new Intent(getApplicationContext(), diaryListActivity.class);
                startActivity(diaryListIntene);
            }
        });
        //데이터 베이스 close
//        cursor.close();
//        db.close();
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
                    checkDlg.setTitle("정말 이 이름으로 하시겠습니까?");
                    checkDlg.setMessage("한번 정하면 바꾸지 못합니다.");
                    checkDlg.setPositiveButton("계속하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.execSQL("INSERT INTO userTable VALUES('"+editName.getText().toString()+"',"+ 0+","+ 0+");");
                            Intent mainIntene = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainIntene);
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
            dlg.show();
        }
    }
}