package com.example.mareu_oc_projet4.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu_oc_projet4.R;
import com.example.mareu_oc_projet4.event.DeleteMeetingEvent;
import com.example.mareu_oc_projet4.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static butterknife.ButterKnife.bind;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder>  {

    private List<Meeting> meetingList;
    private List<Meeting> meetingListFull;
    List<Meeting> meetingStorage;

    public MeetingAdapter(List<Meeting> items) {

        meetingList = items;
        meetingListFull = items;
    }

    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reunion_item, parent, false);
        meetingStorage = saveList();
        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MeetingViewHolder viewHolder, int position) {
        Meeting meeting = meetingList.get(position);
        viewHolder.txtTopicMeeting.setText(meeting.getTopic());
        viewHolder.txtMeetingRoom.setText(meeting.getNameRoom());
        viewHolder.txtMeetingTime.setText(meeting.getHourMeeting() + "h" + meeting.getMinMeeting());
        viewHolder.txtMeetingDate.setText(meeting.getDate());
        viewHolder.txtMeetingParticipants.setText(meeting.getParticipants());
        viewHolder.t1.setText("-");
        viewHolder.t2.setText("-");

        ((GradientDrawable) viewHolder.iconRoom.getBackground()).setColor(meeting.getColor());

        viewHolder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {

        return meetingList.size();
    }

    public List<Meeting> saveList() {
        List<Meeting> saveList = new ArrayList<>();
        for(Meeting item : meetingList)
        {
            saveList.add(item);
        }
        return saveList;
    }

    public void dateFilter(String dateCompare) {
        meetingStorage = saveList();
        List<Meeting> filteredList = new ArrayList<>();
        for(Meeting item : meetingListFull)
        {
            if(item.getDate().equals(dateCompare))
            {
                filteredList.add(item);
            }
        }
        meetingList.clear();
        meetingList.addAll(filteredList);
    }

    public void roomFilter(String roomCompare) {
        meetingStorage = saveList();
        List<Meeting> filteredList = new ArrayList<>();
        for(Meeting item : meetingListFull)
        {
            if(item.getNameRoom().equals(roomCompare))
            {
                filteredList.add(item);
            }
        }
        meetingList.clear();
        meetingList.addAll(filteredList);
    }

    public void resetFilter() {
        meetingList.clear();
        meetingList.addAll(meetingStorage);
        notifyDataSetChanged();
    }


    /*@Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Meeting> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(meetingListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Meeting item : meetingListFull) {
                    if(item.getParticipants().toLowerCase().contains(filterPattern) || item.getNameRoom().toLowerCase().contains(filterPattern) || item.getTopic().toLowerCase().contains(filterPattern) || item.getDate().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            meetingList.clear();
            meetingList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };*/

    public class MeetingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTopicMeeting)
        public TextView txtTopicMeeting;
        @BindView(R.id.txtReunionSalle)
        public TextView txtMeetingRoom;
        @BindView(R.id.txtReunionHoraire)
        public TextView txtMeetingTime;
        @BindView(R.id.txtReunionParticipants)
        public TextView txtMeetingParticipants;
        @BindView(R.id.btnDelete)
        public ImageButton mDeleteButton;
        @BindView(R.id.txtReunionDate)
        public TextView txtMeetingDate;
        @BindView(R.id.iconSalle)
        public TextView iconRoom;
        @BindView(R.id.layoutPrincipal)
        public LinearLayout mainLayout;
        @BindView(R.id.t1)
        public TextView t1;
        @BindView(R.id.t2)
        public TextView t2;


        public MeetingViewHolder(View view) {
            super(view);
            bind(this, view);
        }
    }

}
