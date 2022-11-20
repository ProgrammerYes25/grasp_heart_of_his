package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;

public class DiaryActivity extends AppCompatActivity {
    Button backBtn;
    EditText diaryText;
    InputStream inputText;
    MediaPlayer diaryPlayer;
    Scanner sc;
    String diaryString;
    int[] diaryList = {R.raw.chapter01_diary};
    int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        backBtn = findViewById(R.id.back_btn);
        diaryText = findViewById(R.id.diary_text);
        diaryPlayer = MediaPlayer.create(this, R.raw.elgar_salut_damour_op12_elgar1929);
        diaryPlayer.start();
        backBtn.setOnClickListener(backOnClickListener);
        day = diaryListActivity.day;
        inputText =getResources().openRawResource(diaryList[day]);
        sc = new Scanner(inputText, "UTF-8");
        diaryString = "";
        while (sc.hasNextLine()){
            diaryString += (sc.nextLine()+"\n");
        }
        diaryText.setText(diaryString);

    }
    View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(DiaryActivity.this);
            dlg.setMessage("일기 보는 것을 종료 하시겠습니까?");
            dlg.setPositiveButton("나가기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    diaryPlayer.stop();
                    Intent backIntent = new Intent(getApplicationContext(), diaryListActivity.class);
                    setResult(RESULT_OK, backIntent);
                    finish();
                }
            });
            dlg.setNegativeButton("일기 계속보기",null);
            dlg.show();
        }
    };
}