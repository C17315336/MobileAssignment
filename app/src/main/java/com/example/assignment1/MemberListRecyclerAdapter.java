package com.example.assignment1;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.assignment1.models.MemberList;

import java.util.List;


public class MemberListRecyclerAdapter extends RecyclerView.Adapter<MemberListRecyclerAdapter.ViewHolder> {

    //Interface for callbacks
    interface ActionCallback {
        void onLongClickListener(MemberList memberList);
    }

    private int[] colors;
    private Context context;
    private List<MemberList> memberListList;
    private ActionCallback mActionCallbacks;

    MemberListRecyclerAdapter(Context context, List<MemberList> contactList, int[] colors) {
        this.context = context;
        this.memberListList = memberListList;
        this.colors = colors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.memberlist_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return memberListList.size();
    }

    void updateData(List<MemberList> memberLists) {
        this.memberListList = memberLists;
        notifyDataSetChanged();
    }

    //View Holder
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private TextView mNameTextView;
        private TextView mCategoryTextView;
        private GradientDrawable mBackground;

        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnLongClickListener(this);
            mNameTextView = itemView.findViewById(R.id.label);
            mCategoryTextView = itemView.findViewById(R.id.cat);
            mBackground = (GradientDrawable) mCategoryTextView.getBackground();
        }

        void bindData(int position) {
            MemberList memberList = memberListList.get(position);

            String name = memberList.getName();
            mNameTextView.setText(name);

            String category = memberList.getCategory();
            if (category.equalsIgnoreCase("Scout")) {
                mBackground.setColor(colors[0]);
                mCategoryTextView.setText("S");
            } else if (category.equalsIgnoreCase("Troop Leader")) {
                mBackground.setColor(colors[1]);
                mCategoryTextView.setText("T");
            } else if (category.equalsIgnoreCase("IST")) {
                mBackground.setColor(colors[2]);
                mCategoryTextView.setText("I");
            } else {
                mBackground.setColor(colors[3]);
                mCategoryTextView.setText("O");
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mActionCallbacks != null) {
                mActionCallbacks.onLongClickListener(memberListList.get(getAdapterPosition()));
            }
            return true;
        }
    }

    void addActionCallback(ActionCallback actionCallbacks) {
        mActionCallbacks = actionCallbacks;
    }
}
