package com.covid19project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.covid19project.Adapter.ContainmentListAdapter;
import com.covid19project.Adapter.PersonAdapter;
import com.covid19project.Models.ContainmentList;
import com.covid19project.Models.Persons;
import com.covid19project.OrphanageSupport.FreeFoodActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ContainmentListActivity extends AppCompatActivity implements ContainmentListAdapter.SearchAdapterListener {

    private RecyclerView mContainmentList;

    private ContainmentListAdapter containmentListAdapter;
    private List<ContainmentList> containmentLists;

    private DatabaseReference mContainmentZoneDatabase;

    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_containment_list);

        mContainmentList =findViewById(R.id.containment_list);

        mContainmentZoneDatabase = FirebaseDatabase.getInstance().getReference().child("containment_zone").child("data");
        mContainmentZoneDatabase.keepSynced(true);

        mContainmentList.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mContainmentList.setLayoutManager(mLayoutManager);
        containmentLists = new ArrayList<>();
        containmentListAdapter = new ContainmentListAdapter(this, containmentLists, this);
        mContainmentList.setAdapter(containmentListAdapter);

        read();
    }

    private void read() {
        mContainmentZoneDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                containmentLists.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ContainmentList myStatus = snapshot.getValue(ContainmentList.class);
                    containmentLists.add(myStatus);
                    containmentListAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                containmentListAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                containmentListAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSearchSelected(ContainmentList containmentList) {

    }
}
