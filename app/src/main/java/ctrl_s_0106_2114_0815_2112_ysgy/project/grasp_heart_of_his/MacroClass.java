package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import android.database.sqlite.SQLiteDatabase;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MacroClass extends AppCompatActivity {
    public void storyMacro(InputStream inputText, ImageView characterImg, Button nameBox, Button chatBox){
        Scanner sc = new Scanner(inputText, "UTF-8");
        nameBox.setText(sc.next());
        chatBox.setText(sc.next());
         chatBox.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(sc.hasNextLine()){
                     nameBox.setText(sc.next());
                     chatBox.setText(sc.next());
                 }
             }
         });
    }

//    public static void loadingStoryMacro(InputStream inputText, Button tipBtn){
//        Scanner sc = new Scanner(inputText, "UTF-8");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}
