package cn.renyuzhuo.viewer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class ShowDataActivity extends AppCompatActivity {

    StringBuilder stringBuilder;
    ArrayList<Box> boxStore;
    ArrayList<String> tableName;
    private TextView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rview_main);

        viewById = (TextView) findViewById(R.id.text);
        boxStore = ObjectViewBoxManager.getInstance().getBoxes();
        ArrayList<Class> classes = ObjectViewBoxManager.getInstance().getClasses();
        tableName = new ArrayList<>();
        for (int i = 0; i < classes.size(); i++) {
            tableName.add(classes.get(i).getSimpleName());
        }

        if (boxStore.size() > 0) {
            Box box = boxStore.get(0);
            doShowBoxData(box);
        } else {
            viewById.setText("no tables");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu) {
            showDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.all_tables));
        builder.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tableName) {


        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showTableData(which);
            }
        });

        builder.show();
    }

    private void showTableData(int which) {
        if (boxStore != null && which < boxStore.size()) {
            Box box = boxStore.get(which);
            doShowBoxData(box);
        }
    }

    private void doShowBoxData(Box box) {

        List all = box.getAll();
        stringBuilder = new StringBuilder();
        if (all != null) {
            for (int i = 0; i < all.size(); i++) {
                Object o = all.get(i);
                stringBuilder.append(o.toString()).append("\n");
            }
        }
        viewById.setText(stringBuilder.toString());
    }
}
