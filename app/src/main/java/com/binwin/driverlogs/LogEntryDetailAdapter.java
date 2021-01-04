package com.binwin.driverlogs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LogEntryDetailAdapter extends RecyclerView.Adapter<LogEntryDetailAdapter.LogEntryDetailViewHolder> {

    private final ArrayList<DriverLogs> items;
    private  TextView driverName, regoNumber, startTime, firstEntry, secondEntry, endTime;

    public LogEntryDetailAdapter(Context context, ArrayList<DriverLogs> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public LogEntryDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_log_entry_detail, parent, false);
        return new LogEntryDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogEntryDetailViewHolder holder, int position) {
        holder.bind(items.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public final class LogEntryDetailViewHolder extends RecyclerView.ViewHolder {
        public LogEntryDetailViewHolder(View view) {
            super(view);
        }

        public final void bind(DriverLogs item) {
            driverName = itemView.findViewById(R.id.driver_name);
            regoNumber = itemView.findViewById(R.id.rego_number);
            startTime = itemView.findViewById(R.id.start_time);
            firstEntry = itemView.findViewById(R.id.first_entry);
            secondEntry = itemView.findViewById(R.id.second_entry);
            endTime = itemView.findViewById(R.id.end_time);

            Log.d("check",item.getmDriverName());

            driverName.setText(item.getmDriverName());
            regoNumber.setText(item.getmRego());
            startTime.setText(item.getmStartTime());
            firstEntry.setText(item.getmFirstBreak());
            secondEntry.setText(item.getmSecondBreak());
            endTime.setText(item.getmEndTime());
        }
    }
}
