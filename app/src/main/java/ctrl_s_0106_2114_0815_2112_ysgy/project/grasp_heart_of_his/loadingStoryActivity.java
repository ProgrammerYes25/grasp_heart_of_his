package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;

public class loadingStoryActivity extends AppCompatActivity {
    TextView chapterText;
    Button tipBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_story);
        chapterText = findViewById(R.id.chapter_text);
        tipBtn = findViewById(R.id.tip_btn);
        //test code
        MacroClass macroClass = new MacroClass();
        InputStream inputText = getResources().openRawResource(R.raw.test);
        macroClass.loadingStoryMacro(inputText, tipBtn);
    }
}