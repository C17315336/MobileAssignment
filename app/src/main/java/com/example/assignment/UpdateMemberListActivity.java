package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.db.AppDatabase;
import com.example.assignment.db.MemberListDAO;
import com.example.assignment.models.MemberList;

public class UpdateMemberListActivity extends AppCompatActivity {

    public static String EXTRA_MEMBERLIST_ID = "member_id";

    private EditText mNameEditText;
    private EditText mDetailEditText;
    private TextView mCategory;
    private Button mUpdateButton;
    private Toolbar mToolbar;
    private MemberListDAO mMemberListDAO;
    private MemberList MEMBERLIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        // Get access to database
        mMemberListDAO = Room.databaseBuilder(this, AppDatabase.class, "db-members")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build()
                .getMemberListDAO();

        // Create UI widget objects
        mCategory = findViewById(R.id.updateCategory);
        mNameEditText = findViewById(R.id.memberlist_edit_name);
        mDetailEditText = findViewById(R.id.memberlist_edit_detail);
        mUpdateButton = findViewById(R.id.updateButton);
        mToolbar = findViewById(R.id.toolbar);

        // Retrieve the particular to-do from database using id
        MEMBERLIST = mMemberListDAO.getMemberListWithId(getIntent().getIntExtra(EXTRA_MEMBERLIST_ID, -1));

        // Populate screen with data from to-do
        initViews();
    }

    private void initViews() {
        setSupportActionBar(mToolbar);
        mCategory.setText(MEMBERLIST.getCategory());
        mNameEditText.setText(MEMBERLIST.getName());
        mDetailEditText.setText(MEMBERLIST.getDetail());

        final int id = MEMBERLIST.id;

        // Add listener to 'update' button
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = mCategory.getText().toString();
                String name = mNameEditText.getText().toString();
                String detail = mDetailEditText.getText().toString();

                // Check the user entered data, if not return
                if (name.length() == 0 || detail.length() == 0) {
                    Toast.makeText(UpdateMemberListActivity.this, "Please make sure all details are entered correctly!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create to-do object to store updated data
                MemberList memberList = new MemberList();
                memberList.setCategory(category);
                memberList.setName(name);
                memberList.setDetail(detail);
                memberList.id = id;
                // Update row in database table
                try {
                    mMemberListDAO.update(memberList);
                    setResult(RESULT_OK);
                    finish();
                } catch (SQLiteConstraintException e) {
                    Toast.makeText(UpdateMemberListActivity.this, "Something went really wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete: {
                // Delete row from table
                mMemberListDAO.delete(MEMBERLIST);
                setResult(RESULT_OK);
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}