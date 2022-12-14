package com.ualr.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.model.Inbox;
import com.ualr.recyclerviewassignment.model.InboxAdapter;

import java.util.ArrayList;

// TODO 05. Create a new Adapter class and the corresponding ViewHolder class in a different file. The adapter will be used to populate
//  the recyclerView and manage the interaction with the items in the list
// TODO 06. Detect click events on the list items. Implement a new method to toggle items' selection in response to click events
// TODO 07. Detect click events on the thumbnail located on the left of every list row when the corresponding item is selected.
//  Implement a new method to delete the corresponding item in the list
// TODO 08. Create a new method to add a new item on the top of the list. Use the DataGenerator class to create the new item to be added.

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mFAB;
    private ArrayList<Inbox> inboxList;
    private RecyclerView recyclerView;
    private InboxAdapter inboxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_multi_selection);
        initComponent();
    }

    private void initComponent() {
        // TODO 01. Generate the item list to be displayed using the DataGenerator class
        inboxList = new ArrayList<Inbox>();
        for (int i = 0; i < 5; i++) {
            inboxList.add(DataGenerator.getRandomInboxItem(getApplicationContext()));
        }

        // TODO 03. Do the setup of a new RecyclerView instance to display the item list properly\
        recyclerView = findViewById(R.id.inbox);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // TODO 04. Define the layout of each item in the list

        // TODO 09. Create a new instance of the created Adapter class and bind it to the RecyclerView instance created in step 03
        inboxAdapter = new InboxAdapter(inboxList);
        inboxAdapter.setOnItemClickListener(new InboxAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Inbox obj, int position) {
                int prev_selected_position = inboxAdapter.selected_position;
                if (position == prev_selected_position)
                    inboxAdapter.selected_position = -1;
                else inboxAdapter.selected_position = position;
                inboxAdapter.notifyItemChanged(prev_selected_position);
                inboxAdapter.notifyItemChanged(inboxAdapter.selected_position);
            }
        });
        recyclerView.setAdapter(inboxAdapter);

        mFAB = findViewById(R.id.fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO 10. Invoke the method created to a new item to the top of the list so it's
                //  triggered when the user taps the Floating Action Button
                inboxList.add(0, DataGenerator.getRandomInboxItem(getApplicationContext()));
                recyclerView.getAdapter().notifyItemInserted(0);
            }
        });
    }

}