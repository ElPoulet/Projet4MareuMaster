package com.example.projet4_mareumaster.vues;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet4_mareumaster.R;
import com.example.projet4_mareumaster.event.DeleteReunionEvent;
import com.example.projet4_mareumaster.model.Reunion;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static butterknife.ButterKnife.bind;

public class ReunionAdapter extends RecyclerView.Adapter<ReunionAdapter.ReunionViewHolder>  {

    private List<Reunion> reunionList;
    private List<Reunion> reunionListFull;
    List<Reunion> reunionStorage;

    public ReunionAdapter(List<Reunion> items) {

        reunionList = items;
        reunionListFull = items;
    }

    @Override
    public ReunionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reunion_item, parent, false);
        reunionStorage = saveList();
        return new ReunionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReunionViewHolder viewHolder, int position) {
        Reunion reunion = reunionList.get(position);
        viewHolder.txtSujetReunion.setText(reunion.getSujet());
        viewHolder.txtReunionSalle.setText(reunion.getNomSalle());
        viewHolder.txtReunionHoraire.setText(reunion.getHeureReunion() + "h" + reunion.getMinReunion());
        viewHolder.txtReunionDate.setText(reunion.getDate());
        viewHolder.txtReunionParticipants.setText(reunion.getParticipants());
        viewHolder.t1.setText("-");
        viewHolder.t2.setText("-");

        ((GradientDrawable) viewHolder.iconSalle.getBackground()).setColor(reunion.getColor());

        viewHolder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteReunionEvent(reunion));
            }
        });
    }

    @Override
    public int getItemCount() {

        return reunionList.size();
    }

    public List<Reunion> saveList() {
        List<Reunion> saveList = new ArrayList<>();
        for(Reunion item : reunionList)
        {
            saveList.add(item);
        }
        return saveList;
    }

    public void dateFilter(String dateCompare) {
        reunionStorage = saveList();
        List<Reunion> filteredList = new ArrayList<>();
        for(Reunion item : reunionListFull)
        {
            if(item.getDate().equals(dateCompare))
            {
                filteredList.add(item);
            }
        }
        reunionList.clear();
        reunionList.addAll((List) filteredList);
    }

    public void salleFilter(String salleCompare) {
        reunionStorage = saveList();
        List<Reunion> filteredList = new ArrayList<>();
        for(Reunion item : reunionListFull)
        {
            if(item.getNomSalle().equals(salleCompare))
            {
                filteredList.add(item);
            }
        }
        reunionList.clear();
        reunionList.addAll((List) filteredList);
    }

    public void resetFilter() {
        reunionList.clear();
        reunionList.addAll(reunionStorage);
        notifyDataSetChanged();
    }


    /*@Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Reunion> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(reunionListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Reunion item : reunionListFull) {
                    if(item.getParticipants().toLowerCase().contains(filterPattern) || item.getNomSalle().toLowerCase().contains(filterPattern) || item.getSujet().toLowerCase().contains(filterPattern) || item.getDate().toLowerCase().contains(filterPattern)) {
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
            reunionList.clear();
            reunionList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };*/

    public class ReunionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtSujetReunion)
        public TextView txtSujetReunion;
        @BindView(R.id.txtReunionSalle)
        public TextView txtReunionSalle;
        @BindView(R.id.txtReunionHoraire)
        public TextView txtReunionHoraire;
        @BindView(R.id.txtReunionParticipants)
        public TextView txtReunionParticipants;
        @BindView(R.id.btnDelete)
        public ImageButton mDeleteButton;
        @BindView(R.id.txtReunionDate)
        public TextView txtReunionDate;
        @BindView(R.id.iconSalle)
        public TextView iconSalle;
        @BindView(R.id.layoutPrincipal)
        public LinearLayout layoutPrincipal;
        @BindView(R.id.t1)
        public TextView t1;
        @BindView(R.id.t2)
        public TextView t2;


        public ReunionViewHolder(View view) {
            super(view);
            bind(this, view);
        }
    }

}
