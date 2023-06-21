package com.app;

import android.app.*;
import android.os.*;
import com.google.android.exoplayer2.*;
import com.google.android.exoplayer2.ui.*;
import com.google.android.exoplayer2.upstream.*;
import com.google.android.exoplayer2.trackselection.*;
import android.net.*;
import com.google.android.exoplayer2.extractor.*;
import com.google.android.exoplayer2.source.*;
import android.widget.*;
import com.google.android.exoplayer2.util.*;

public class MainActivity extends Activity 
{
	SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    String videoURL ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		videoURL = getIntent().getStringExtra("path");
		exoPlayerView = findViewById(R.id.exoPlayerView);
        try
		{

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            Uri videouri = Uri.parse(videoURL);
			String userAgent = Util.getUserAgent(this, getPackageName());
			DataSource.Factory dsf = new DefaultDataSourceFactory(this, userAgent);
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videouri, dsf, extractorsFactory, null, null);
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }
		catch (Exception e)
		{
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
