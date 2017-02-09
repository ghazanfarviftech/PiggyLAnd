package com.example.ghazanfarali.piggyland.Views.Fragments.SharedArt_Comments_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.SharedArt_Comments_fragment.Bean.CommentsMsgs;
import com.example.ghazanfarali.piggyland.Views.Fragments.SharedArt_Comments_fragment.adapter.commentsAdapter;

import java.util.ArrayList;

/**
 * Created by Amir.jehangir on 2/8/2017.
 */
public class SharedComment_Fragment extends MasterFragment {
    private View view;
    TextView number_of_like_box;
    RecyclerView comment_recycler_view;
    EditText commentEdittext;
    ImageView send_comment;
    ArrayList<CommentsMsgs> commentsArryList;
    commentsAdapter commentsAdapter;
    MasterFragment mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.comment_like_fragment, container, false);

//            startService();
        } else {
            if (view != null)
                //  userProfileActivity.hideHeaderLayout();
                userProfileActivity.setHeaderTitle("Comment");
            initUI();

        }
        return view;

    }
    @Override
    public void initUI() {
        super.initUI();
        commentsArryList = new ArrayList<>();
        number_of_like_box = (TextView)view.findViewById(R.id.number_of_like_box);
        comment_recycler_view = (RecyclerView)view.findViewById(R.id.comment_recycler_view);
        commentEdittext = (EditText)view.findViewById(R.id.commentEdittext);
        send_comment = (ImageView)view.findViewById(R.id.send_comment);


      //  commentsArryList = prepareListData();
        commentsAdapter = new commentsAdapter(mContext,commentsArryList);
        comment_recycler_view.setAdapter(commentsAdapter);
    }
}
