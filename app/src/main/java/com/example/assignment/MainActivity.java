package com.example.assignment;

// Imports Section
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.assignment.db.AppDatabase;
import com.example.assignment.db.MemberListDAO;
import com.example.assignment.models.MemberList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

/*************************************
 *
 * Assignment        : Mobile Software Development CA
 * Package           : com.example.assignment
 * File              : MainActivity.java
 * Author            : Eoghan Byrne
 * Student number    : C17315336
 * Last modified     : 10th December 2019
 *
 *************************************/

/*
 * Code heavily based of Lab work on ToDo List
 */

public class MainActivity extends AppCompatActivity {

    private static final int RC_CREATE_MEMBERLIST = 1;
    private static final int RC_UPDATE_MEMBERLIST = 2;

    private RecyclerView mMemberListsRecyclerView;
    private MemberListRecyclerAdapter mMemberListRecyclerAdapter;
    private FloatingActionButton mAddMemberListFloatingActionButton;
    private MemberListDAO mMemberListDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get access to room database
        mMemberListDAO = Room.databaseBuilder(this, AppDatabase.class, "db-members")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build()
                .getMemberListDAO();

        // Depending on the category of a member we give it a different colour
        int colors[] = {ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorAccent1),
                ContextCompat.getColor(this, R.color.colorAccent2),
                ContextCompat.getColor(this, R.color.colorAccent3)};

        // Configure RecyclerView
        mMemberListsRecyclerView = findViewById(R.id.contactsRecyclerView);
        mMemberListsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create Adapter
        mMemberListRecyclerAdapter = new MemberListRecyclerAdapter(this, new ArrayList<MemberList>(), colors);

        // Launches update activity when user long-clicks item
        mMemberListRecyclerAdapter.addActionCallback(new MemberListRecyclerAdapter.ActionCallback() {
            @Override
            public void onLongClickListener(MemberList memberList) {
                Intent intent = new Intent(MainActivity.this, UpdateMemberListActivity.class);

                intent.putExtra(UpdateMemberListActivity.EXTRA_MEMBERLIST_ID, memberList.getId());

                startActivityForResult(intent, RC_UPDATE_MEMBERLIST);
            }
        });
        mMemberListsRecyclerView.setAdapter(mMemberListRecyclerAdapter);

        // Include a Button
        mAddMemberListFloatingActionButton = findViewById(R.id.addContactFloatingActionButton);

        // Launches create activity when user clicks on it
        mAddMemberListFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateMemberListActivity.class);
                startActivityForResult(intent, RC_CREATE_MEMBERLIST);
            }
        });

        // Update display with all members currently stored in database
        loadMemberLists();
    }

    private void loadMemberLists() {

        mMemberListRecyclerAdapter.updateData(mMemberListDAO.getMemberLists());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_CREATE_MEMBERLIST && resultCode == RESULT_OK) {
            loadMemberLists();
        } else if (requestCode == RC_UPDATE_MEMBERLIST && resultCode == RESULT_OK) {
            loadMemberLists();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_options, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
