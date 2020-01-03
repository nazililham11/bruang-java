package com.bruang.bookingruang.Adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bruang.bookingruang.Model.ClassSchedule;
import com.bruang.bookingruang.R;
import com.bruang.bookingruang.Util.DateHelper;

import java.util.List;

public class AvailableRoomAdapter extends RecyclerView.Adapter<AvailableRoomAdapter.ViewHolder> {

    private List<ClassSchedule> rooms;
    private LayoutInflater inflater;
    private ItemClickListener itemClickListener;

    public AvailableRoomAdapter(Context context, List<ClassSchedule> rooms) {
        this.rooms = rooms;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.available_room_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClassSchedule room = rooms.get(position);
        holder.tvDay.setText(DateUtils.getDayOfWeekString(room.getDay(), DateUtils.LENGTH_MEDIUM));
        holder.tvDate.setText(DateHelper.getFormatedShortDate(room.getDate()));
        holder.tvYear.setText(String.valueOf(DateHelper.stringToDateConverter(room.getDate()).getYear()));
        holder.tvRoom.setText(room.getRoom().getFullRoomNumber());
        holder.tvTime.setText(room.getClassSession().getTimeLabel());

    }

    @Override
    public int getItemCount() {
        if (rooms == null) {
            Log.d("Log", "rooms is Null");
            return 0;
        }
        return rooms.size();
    }

    public ClassSchedule getItem(int index) {
        return rooms.get(index);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvDay, tvDate, tvYear;
        TextView tvRoom, tvTime;

        ViewHolder(View view) {
            super(view);
            tvDay  = view.findViewById(R.id.tvDay);
            tvDate  = view.findViewById(R.id.tvDate);
            tvYear  = view.findViewById(R.id.tvYear);
            tvRoom  = view.findViewById(R.id.tvRoom);
            tvTime  = view.findViewById(R.id.tvTime);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null)
                itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
