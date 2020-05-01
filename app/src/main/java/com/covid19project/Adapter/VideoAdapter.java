package com.covid19project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.covid19project.Models.Video;
import com.covid19project.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Video> mVideo;

    public VideoAdapter(Context context, List<Video> list) {
        mContext = context;
        mVideo = list;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_video, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int position) {

        final Video video = mVideo.get(position);

        holder.Video.loadData(video.getVideo(), "text/html" , "utf-8");
    }

    @Override
    public int getItemCount() {
        return mVideo.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public WebView Video;
        public ImageViewHolder(View itemView) {
            super(itemView);

            Video = itemView.findViewById(R.id.video);

            Video.getSettings().setJavaScriptEnabled(true);
            Video.setWebChromeClient(new WebChromeClient() {

            } );
        }
    }

}