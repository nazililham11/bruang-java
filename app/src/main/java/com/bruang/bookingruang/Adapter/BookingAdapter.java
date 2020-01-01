package com.bruang.bookingruang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bruang.bookingruang.Application.App;
import com.bruang.bookingruang.Enum.BookingStatus;
import com.bruang.bookingruang.Model.Booking;
import com.bruang.bookingruang.R;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private List<Booking> bookings;
    private LayoutInflater inflater;
    private ItemClickListener itemClickListener;

    public BookingAdapter(Context context, List<Booking> bookings) {
        this.bookings = bookings;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.booking_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Booking booking = bookings.get(position);

        String roomName =
                String.format("%s %s", App.getResourses().getString(R.string.room_label), booking
                        .getClassSchedule()
                        .getRoom()
                        .getFullRoomNumber());

        holder.tvTitle.setText(booking.getTitle());
        holder.tvRoom.setText(roomName);
        holder.tvTime.setText(booking.getClassSchedule().getClassSession().getTimeLabel());
        holder.tvDate.setText(booking.getFormatedLongDate());
        holder.tvStatus.setText(booking.getStatus());
        holder.tvRejectMsg.setVisibility(View.GONE);
        holder.tvRejectMsg.setText("");

        BookingStatus status = BookingStatus.getBookingStatus(booking.getStatus());

        if (status.equals(BookingStatus.Rejected)){
            holder.tvRejectMsg.setVisibility(View.VISIBLE);
            holder.tvRejectMsg.setText(booking.getMessage());
        }

        holder.tvStatus.setBackgroundColor(status.getStatusColor());
        holder.statusIndicator.setBackgroundColor(status.getStatusColor());
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public Booking getItem(int index) {
        return bookings.get(index);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle, tvRoom, tvTime, tvDate, tvStatus, tvRejectMsg;
        View statusIndicator;

        ViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvRoom  = view.findViewById(R.id.tvRoomNumber);
            tvTime  = view.findViewById(R.id.tvTime);
            tvDate  = view.findViewById(R.id.tvDate);
            tvStatus = view.findViewById(R.id.tvStatus);
            tvRejectMsg = view.findViewById(R.id.tvRejectedMsg);
            statusIndicator = view.findViewById(R.id.indicatorView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null)
                itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}