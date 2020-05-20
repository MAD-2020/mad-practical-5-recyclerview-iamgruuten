package sg.edu.np.mad.mad_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<viewHolder> {

    private List<views> viewsList;
    private Context context;

    public adapter (List<views> adapters, Context context){
        viewsList = adapters;
        this.context = context;
    }

    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.viewholder, parent, false);
        return new viewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.tasks.setText(viewsList.get(position).getTask());
        holder.check.setChecked(viewsList.get(position).isCompleted());
    }

    @Override //get the item id based on positon
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    //Removing of To-do
    @Override
    public void remove(int position){
        viewsList.remove(position);
        this.notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return viewsList.size(); //Get Item Count that is not used
    }
}
