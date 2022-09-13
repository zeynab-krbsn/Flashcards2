package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> collection;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createGroupList();
        createCollection();

        expandableListView = findViewById(R.id.expandableListView1);
        expandableListAdapter = new MyExpandableListAdapter(this, groupList, collection);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;

            @Override
            public void onGroupExpand(int i) {
                if (lastExpandedPosition != -1 && i != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected = expandableListAdapter.getChild(i, i1).toString();
                Toast.makeText(getApplicationContext(), "selected"+selected, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void createCollection() {
        String[] Computer={"دهم","یازدهم","دوازدهم"};
        String[] Accounting={"دهم","یازدهم","دوازدهم"};

        collection=new HashMap<String,List<String>>();
        for (String group : groupList) {
            if (group.equals("شبکه و نرم افزار")){
                loadChild(Computer);
            }
            else if (group.equals("حسابداری")){
                loadChild(Accounting);
            }
            collection.put(group,childList);
        }
    }

    private void loadChild(String[] Collection) {
        childList=new ArrayList<>();
        for (String filed: Collection) {
            childList.add(filed);
        }
    }

    private void createGroupList() {
        groupList = new ArrayList<>();
        groupList.add("شبکه و نرم افزار");
        groupList.add("حسابداری");
    }
}