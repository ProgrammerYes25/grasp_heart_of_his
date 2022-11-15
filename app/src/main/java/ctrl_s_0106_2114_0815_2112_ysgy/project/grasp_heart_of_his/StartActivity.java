package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {
    ImageView startImg;
    DBHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startImg = findViewById(R.id.start_img);
        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();
        startImg.setOnClickListener(startClickListener);
        //데이터 베이스 close
        db.close();
        dbHelper.close();
    }
    View.OnClickListener startClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainIntene = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainIntene);

        }
    };
}