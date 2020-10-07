package fr.leftac.listify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.helloButton);
        TextView tv = findViewById(R.id.textView);

        button.setOnClickListener(v -> {
            tv.setText("Salut les amis");
        });



    }
}