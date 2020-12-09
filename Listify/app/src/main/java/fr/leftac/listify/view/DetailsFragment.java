package fr.leftac.listify.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;

import fr.leftac.listify.R;
import fr.leftac.listify.controller.Controller;
import fr.leftac.listify.model.pojo.Track;

public class DetailsFragment extends Fragment {

    private Track track;

    private ImageView image;
    private TextView name, artist, album, duration, popularity;
    private ImageButton favButton;
    private Controller controller;
    private ViewPager2 vp;


    public DetailsFragment(Track track, Controller controller, ViewPager2 vp) {
        this.track = track;
        this.controller = controller;
        this.vp = vp;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Init

        image = view.findViewById(R.id.image);
        name = view.findViewById(R.id.name);
        artist = view.findViewById(R.id.artist);
        album = view.findViewById(R.id.album);
        duration = view.findViewById(R.id.duration);
        popularity = view.findViewById(R.id.popularity);
        favButton = view.findViewById(R.id.imageButton);

        if (controller.isFavorite(track)) {
            favButton.setImageResource(R.drawable.ic_baseline_star_24);
        } else {
            favButton.setImageResource(R.drawable.ic_baseline_star_border_24);
        }

        favButton.setOnClickListener(v -> {
            if (controller.isFavorite(track)) {
                controller.removeTrackFromBDD(track);
                favButton.setImageResource(R.drawable.ic_baseline_star_border_24);
            } else {
                controller.saveTrackToBDD(track);
                favButton.setImageResource(R.drawable.ic_baseline_star_24);
            }
        });

        artist.setOnClickListener(v -> {
            vp.setCurrentItem(1, true);
        });

        album.setOnClickListener(v -> {
            vp.setCurrentItem(2, true);
        });


//        Set

        Glide.with(getContext()).load(track.getAlbum().getImage()).fitCenter().into(image);

        name.setText(track.getName());
        artist.setText(track.getArtist().getName());
        album.setText(track.getAlbum().getName());
        int durationValue = track.getDuration() / 1000;
        String durationText = durationValue / 60 + ":" + (durationValue % 60 < 10 ? "0" : "") + durationValue % 60;
        duration.setText(durationText);
        String popularityText = track.getPopularity()+" %";
        popularity.setText(popularityText);
        popularity.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);






    }
}