package sg.edu.np.mad.mad_recyclerview;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView tasks;
    CheckBox check;

    public viewHolder(View view){
        super(view);
        tasks = view.findViewById(R.id.task);
        check = view.findViewById(R.id.check);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showAlertDialogButtonClicked(this.getLayoutPosition());
        Log.v("item", "Item on click");
    }

    //Show custom alert dialog builder
    public void showAlertDialogButtonClicked(final int position) {
        final String task = viewsList.get(position).getTask();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        LayoutInflater inflater = LayoutInflater.from(context);
        View customLayout = inflater.inflate(R.layout.custom_delete_layout, null);
        staskName = customLayout.findViewById(R.id.taskName);
        staskName.setText(task);
        builder.setView(customLayout);
        builder.setNegativeButton("No", null);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v("AlertDialog", "Delete Item " + task);
                remove(position);
            }
        });

        // create and show the alert dialog
        final AlertDialog dialog = builder.create();
        dialog.show();
    }
}