package fpoly.andoid.extest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public List<DTO> list;
    public Context context;
    public DAO dao;
    public DTO dto;

    String[] title = {"Danh sách", "Thêm"};

    public Adapter(Context context, List<DTO> list){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        dto = list.get(position);

        holder.tv_title.setText(dto.getTitle() + ": ");
        holder.tv_time.setText(dto.getTime());
        holder.tv_content.setText(dto.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View viewDl = ((MainActivity) context).getLayoutInflater().inflate(R.layout.show_content, null);
                builder.setView(viewDl);
                TextView tv_show_content = viewDl.findViewById(R.id.tv_show_content);
                TextView tv_show_title = viewDl.findViewById(R.id.tv_show_title);
                TextView tv_show_date = viewDl.findViewById(R.id.tv_show_date);

                tv_show_title.setText(dto.getTitle());
                tv_show_content.setText(dto.getContent());
                tv_show_date.setText(dto.getTime());
                builder.show();

                Toast.makeText(context, "Test " + position, Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("Delete");
//                builder.setMessage("Bạn có chắc chắn muốn xóa?");
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        del();
//                    }
//                });
//                builder.create();
//                builder.show();
                del();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_title, tv_time, tv_content;
        public ImageView imgDel;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            imgDel = itemView.findViewById(R.id.imgDel);

        }
    }

    public void del(){
        dao = new DAO(context);
        dao.open();
        int i = dao.Del(dto);
        if (i > 0) {
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            list.clear();
            list.addAll(dao.GetAll());
            notifyDataSetChanged();
        } else {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
        dao.close();
    }

    public void resetData(){
        notifyDataSetChanged();
    }
}

