package fr.leftac.listify.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fr.leftac.listify.R;
import fr.leftac.listify.controller.Controller;
import fr.leftac.listify.model.api.TokenManager;
import fr.leftac.listify.model.pojo.Track;


public class MainActivity extends AppCompatActivity implements Controller.TrackCallbackListener {
    private Button tokenButton, searchButton;
    private Controller controller;

    private List<Track> tracks;

    //TODO: use fragments instead of activities
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
        tokenButton = findViewById(R.id.tokenButton);
        searchButton = findViewById(R.id.searchButton);

        // Init variables
        tracks = new ArrayList<>();

        // Buttons
        if (TokenManager.getToken() == null) {
            searchButton.setEnabled(false);
        }

        tokenButton.setOnClickListener(v -> {
            TokenManager.generateToken();
            searchButton.setEnabled(true);
        });

        searchButton.setOnClickListener(v -> {
            tracks = new ArrayList<>();
            controller = new Controller(MainActivity.this);
            controller.searchTracks("orelsan");
        });

    }

    @Override
    public void onFetchProgress(Track track) {
        tracks.add(track);
    }

    @Override
    public void onFetchComplete() {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < tracks.size(); i++) {
            msg.append("Track n°").append(i + 1).append(" : ").append(tracks.get(i).getName()).append('\n');
        }
        Toast.makeText(this, msg.toString(), Toast.LENGTH_LONG).show();
    }
}