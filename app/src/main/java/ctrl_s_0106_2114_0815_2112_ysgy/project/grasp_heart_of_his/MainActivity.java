package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView storyBtn, diaryBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storyBtn = findViewById(R.id.story_btn);
        diaryBtn = findViewById(R.id.diary_btn);
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
    }
}