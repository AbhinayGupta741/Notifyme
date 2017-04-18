package com.example.abhinaygupta.notifyme;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class categories extends ListActivity {
    private Button submit;


    /** String array used as the datasource for the ArrayAdapter of the listview **/
    String[] list = new String[] {
            "Hollywood Movies",
            "Bollywood Movies",
            "Youtube India",
            "Tech News",
            "Trending Topics",
            "TV Series",
            "Web Series"

    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        submit = (Button)findViewById(R.id.id_submit);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Toast.makeText(categories.this, "Thanks For Selecting Categories",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(categories.this, MainActivity.class));

            }
        });



        if (AppStatus.getInstance(this).isOnline()) {



        } else {

            Toast.makeText(categories.this, "Please Check Your Internet Connection",
                    Toast.LENGTH_SHORT).show();
            Log.v("Home", "############################You are not online!!!!");
        }


        /** Defining array adapter to store items for the listview **/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, list);

        /** Setting the arrayadapter for this listview  **/
        getListView().setAdapter(adapter);

        /** Defining checkbox click event listener **/
        OnClickListener clickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckBox chk = (CheckBox) v;
                int itemCount = getListView().getCount();
                for(int i=0 ; i < itemCount ; i++){
                    getListView().setItemChecked(i, chk.isChecked());
                }
            }
        };

        /** Defining click event listener for the listitem checkbox */
        OnItemClickListener itemClickListener = new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                CheckBox chk = (CheckBox) findViewById(R.id.chkAll);
                int checkedItemCount = getCheckedItemCount();

                if(getListView().getCount()==checkedItemCount)
                    chk.setChecked(true);
                else
                    chk.setChecked(false);
            }
        };

        /** Getting reference to checkbox available in the main.xml layout */
        CheckBox chkAll =  ( CheckBox ) findViewById(R.id.chkAll);

        /** Setting a click listener for the checkbox **/
        chkAll.setOnClickListener(clickListener);

        /** Setting a click listener for the listitem checkbox **/
        getListView().setOnItemClickListener(itemClickListener);

    }

    /**
     *
     * Returns the number of checked items
     */
    private int getCheckedItemCount(){
        int cnt = 0;
        SparseBooleanArray positions = getListView().getCheckedItemPositions();
        int itemCount = getListView().getCount();

        for(int i=0;i<itemCount;i++){
            if(positions.get(i))
                cnt++;
        }
        return cnt;
    }
}