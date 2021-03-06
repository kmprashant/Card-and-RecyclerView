package prashantkumar.com.cardandrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Prashant on 29-12-2018.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {
    private Context mContext;
    private List<Album> albumList;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title,count;
        public ImageView thumbnail,overflow;

        public MyViewHolder(View view){
            super(view);
            title =  view.findViewById(R.id.title);
            count = view.findViewById(R.id.count);
            thumbnail = view.findViewById(R.id.thumbnail);
            overflow = view.findViewById(R.id.overflow);
        }
    }
    public AlbumsAdapter(Context mcontext, List<Album> albumList){
        this.mContext = mcontext;
        this.albumList = albumList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,int position){

        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + "Songs");

        //Loading album cover using glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });


    }

    private void showPopupMenu(View view){
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album,popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();

    }


    class MyMenuItemClickListener implements  PopupMenu.OnMenuItemClickListener{
        public MyMenuItemClickListener(){}

        @Override
        public boolean onMenuItemClick(MenuItem menuItem){
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to Favourite", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play Next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return  false;
        }

    }
    @Override
    public int getItemCount(){
        return albumList.size();
    }


}
