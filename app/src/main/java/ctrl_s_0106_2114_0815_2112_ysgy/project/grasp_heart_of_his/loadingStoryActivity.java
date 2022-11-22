package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;

public class loadingStoryActivity extends AppCompatActivity {
    TextView chapterText;
    Button tipBtn;
    Scanner sc;
    int chapter;
    int[] tip = {R.raw.prologue01_tip,R.raw.chapter01_tip};
    Class[] chapterList = new Class[]{Prologue.class, Chapter01.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_story);
        chapterText = findViewById(R.id.chapter_text);
        tipBtn = findViewById(R.id.tip_btn);
        //test code
        chapter = storyListActivity.chapter;

        if (chapter ==0){
            chapterText.setText("Prologue");
        }
        else {
            chapterText.setText("Chapter "+chapter);
        }
        InputStream inputText = getResources().openRawResource(tip[chapter]);
        sc = new Scanner(inputText, "UTF-8");
        tipBtn.setText(sc.nextLine());
        tipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent storyIntense = new Intent(getApplicationContext(), chapterList[chapter]);
                startActivity(storyIntense);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}