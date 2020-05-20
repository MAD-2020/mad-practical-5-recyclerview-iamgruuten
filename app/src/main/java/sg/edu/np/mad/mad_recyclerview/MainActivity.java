package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    adapter mAdapter;
    List<views> tasklist = new ArrayList<>();
    Button add;
    EditText task;
    String sTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = findViewById(R.id.editTask);
        recyclerView = findViewById(R.id.recyclerview);
        add = findViewById(R.id.addButton);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mAdapter = new adapter(tasklist, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sTask = task.getText().toString();
                views view = new views(sTask, false);
                tasklist.add(view);
                mAdapter = new adapter(tasklist, MainActivity.this);
                recyclerView.setAdapter(mAdapter);
                showNewEntry(recyclerView, tasklist);
            }
        });

        //task is the input task, this allows to track the input of the task input
        task.setFocusable(true);
        task.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    ShowKeyboard();
                else
                    HideKeyboard();
            }
        });
    }

    private void showNewEntry(RecyclerView rv, List data) {
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        HideKeyboard();
    }

    private void ShowKeyboard() {
        InputMethodManager mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mImm.showSoftInput(task, InputMethodManager.SHOW_IMPLICIT);
        Log.v("Keyboard", "Show Keyboard");
    }

    private void HideKeyboard() {
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(task.getWindowToken(), 0);
        Log.v("Keyboard", "Hide Keyboard");

    }

}
