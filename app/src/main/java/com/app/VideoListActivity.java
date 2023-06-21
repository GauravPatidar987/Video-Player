package com.app;
import android.app.*;
import android.os.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.*;
import android.net.*;
import android.provider.*;
import android.database.*;
import java.util.*;
import android.widget.Toast;
import java.io.*;

public class VideoListActivity extends Activity
{
	RecyclerView rec;
	LinearLayoutManager manager; 
	VideoAdapter adap;
	ArrayList<Vfile> l;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_list);
		rec = findViewById(R.id.rec_view);
		manager = new LinearLayoutManager(this);
		rec.setLayoutManager(manager);
		l = getAllVideoPath(this);
		adap = new VideoAdapter(l, this);
		rec.setAdapter(adap);
	}
	private  ArrayList<Vfile> getAllVideoPath(Context context)
	{

        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Video.VideoColumns.DATA,
			MediaStore.Video.VideoColumns.DISPLAY_NAME,MediaStore.Video.VideoColumns._ID,MediaStore.Video.VideoColumns.BUCKET_DISPLAY_NAME};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        ArrayList<Vfile> pathArrList = new ArrayList<Vfile>();
        if (cursor != null)
		{

            while (cursor.moveToNext())
			{
				Vfile ffi=new Vfile(cursor.getString(1), cursor.getString(0), cursor.getString(2), cursor.getString(3));
                pathArrList.add(ffi);
            }
            cursor.close();
        }
        return pathArrList;
    }
}
