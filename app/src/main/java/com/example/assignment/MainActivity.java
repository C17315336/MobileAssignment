package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.assignment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.assignment.db.AppDatabase;
import com.example.assignment.db.MemberListDAO;
import com.example.assignment.models.MemberList;

// This week using arraylist instead of linkedlist
import java.util.ArrayList;

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

        // Depending on the category of member we want to give it a different colour
        int colors[] = {ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorAccent1),
                ContextCompat.getColor(this, R.color.colorAccent2),
                ContextCompat.getColor(this, R.color.colorAccent3)};

        //Configure RecyclerView
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

        // Include a FAB
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

        // really just refreshing the screen after data changes
        if (requestCode == RC_CREATE_MEMBERLIST && resultCode == RESULT_OK) {
            loadMemberLists();
        } else if (requestCode == RC_UPDATE_MEMBERLIST && resultCode == RESULT_OK) {
            loadMemberLists();
        }
    }
}
