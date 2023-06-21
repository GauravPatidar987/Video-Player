package com.app;
import androidx.recyclerview.widget.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import com.bumptech.glide.*;
import android.content.*;
import android.net.*;
import java.io.*;
import android.graphics.*;
import android.media.*;
import android.provider.*;
import android.graphics.drawable.*;
import android.database.*;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.Vholder>
{



	ArrayList<Vfile> al;
	Context c;
	public VideoAdapter(ArrayList<Vfile> al, Context c)
	{
		this.al = al;
		this.c = c;
	}
	@Override
	public VideoAdapter.Vholder onCreateViewHolder(ViewGroup p1, int p2)
	{
		View v=LayoutInflater.from(p1.getContext()).inflate(R.layout.video_item, p1, false);	
		return new Vholder(v);
	}


	@Override
	public void onBindViewHolder(VideoAdapter.Vholder holder, int p2)
	{
		Drawable thumb = getVideoThumbnail(c, Integer.parseInt(al.get(p2).getId()));
		holder.img.setImageDrawable(thumb);
		holder.txt.setText(al.get(p2).getTitle());
	}
	

	public static Drawable getVideoThumbnail(Context context, int videoID)
	{
        try
		{
            String[] projection = {
				MediaStore.Video.Thumbnails.DATA,
            };
            ContentResolver cr = context.getContentResolver();
            Cursor cursor = cr.query(
				MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
				projection, 
				MediaStore.Video.Thumbnails.VIDEO_ID + "=?", 
				new String[] { String.valueOf(videoID) }, 
				null);
            cursor.moveToFirst();
            return Drawable.createFromPath(cursor.getString(0));
        }
		catch (Exception e)
		{
        }
        return null;
    }

	@Override
	public int getItemCount()
	{

		return al.size();
	}

	public class Vholder extends RecyclerView.ViewHolder implements View.OnClickListener
	{
		ImageView img;
		public TextView txt;
		public Vholder(View v)
		{
			super(v);
			img = v.findViewById(R.id.imageView);
			txt = v.findViewById(R.id.textView);
			txt.setOnClickListener(this);

		}
		@Override
		public void onClick(View p1)
		{
			Intent i=new Intent(c, MainActivity.class);
			String path=al.get(getAdapterPosition()).getUrl();
			i.putExtra("path", path);
			c.startActivity(i);
		}
	}
}
