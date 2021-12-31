package vn.hust.edu.RecycleAssignment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class ItemJSONAdapter extends RecyclerView.Adapter<ItemJSONAdapter.ItemViewHolder> {

    JSONArray jsonArray;
    public Intent intent;
    public ItemJSONAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.textName.setText(jsonObject.getString("name"));
            holder.textEmail.setText(jsonObject.getString("email"));
            Picasso.get().load("https://lebavui.github.io"+jsonObject.getJSONObject("avatar").getString("thumbnail")).into(holder.imageAvatar);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(position);
                    intent= new Intent(view.getContext(),DetailActivity.class);
                    intent.putExtra("data",jsonObject.toString());
                    view.getContext().startActivity(intent);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        TextView textName;
        TextView textEmail;
        ImageView imageAvatar;
        private ItemClickListener itemClickListener;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAvatar=itemView.findViewById(R.id.img_avatar);
            textName = itemView.findViewById(R.id.text_name);
            textEmail = itemView.findViewById(R.id.text_email);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }
        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }

}
