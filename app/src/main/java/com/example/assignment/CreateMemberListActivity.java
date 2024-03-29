package com.example.assignment;

// Imports Section
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import com.example.assignment.db.AppDatabase;
import com.example.assignment.db.MemberListDAO;
import com.example.assignment.models.MemberList;

/*************************************
 *
 * Assignment        : Mobile Software Development CA
 * Package           : com.example.assignment
 * File              : CreateMemberListActivity.java
 * Author            : Eoghan Byrne
 * Student number    : C17315336
 * Last modified     : 10th December 2019
 *
 *************************************/

public class CreateMemberListActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mDetailEditText;
    private Spinner mCategory;
    private Button mSaveButton;
    private MemberListDAO mMemberListDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get access to Room Database
        mMemberListDAO = Room.databaseBuilder(this, AppDatabase.class, "db-members")
                .allowMainThreadQueries()
                .build()
                .getMemberListDAO();

        // Create UI widget objects
        mCategory = findViewById(R.id.category);
        mNameEditText = findViewById(R.id.memberlist_edit_name);
        mDetailEditText = findViewById(R.id.memberlist_edit_detail);
        mSaveButton = findViewById(R.id.saveButton);

        // Add a listener to 'Save' button
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = mCategory.getSelectedItem().toString();
                String name = mNameEditText.getText().toString();
                String detail = mDetailEditText.getText().toString();

                // Check data was entered, if not pop toast and return
                if (name.length() == 0 || detail.length() == 0) {
                    Toast.makeText(CreateMemberListActivity.this, "Please make sure all details are entered correctly!", Toast.LENGTH_SHORT).show();
                    return;
                }

                MemberList memberList = new MemberList();
                memberList.setCategory(category);
                memberList.setName(name);
                memberList.setDetail(detail);

                // Insert to database
                try {
                    mMemberListDAO.insert(memberList);
                    setResult(RESULT_OK);
                    finish();
                } catch (SQLiteConstraintException e) {
                    Toast.makeText(CreateMemberListActivity.this, "Something went really wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}