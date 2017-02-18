package com.example.ghazanfarali.piggyland.Views.Fragments.MessageforYou;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.MessageforYou.Bean.MessagesComments;
import com.example.ghazanfarali.piggyland.Views.Fragments.MessageforYou.adapter.MessageCommentAdapter;

import java.util.ArrayList;

/**
 * Created by Amir.jehangir on 2/18/2017.
 */
public class Message_Comments_fragment extends MasterFragment {

    private View view;
    TextView number_of_comments_box;
    RecyclerView M_comment_recycler_view;
    ArrayList<MessagesComments> commentsArryList;
    MessageCommentAdapter messageCommentAdapter;
    MasterFragment mContext;
    private LinearLayoutManager lLayout;
    String UserNAme = "";
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.messageforme_comment_fragment, container, false);
//            userProfileActivity.setHeaderTitle("Comment");
            initUI();
//            startService();
        } else {
            if (view != null)
                userProfileActivity.hideHeaderLayout();


        }
        return view;

    }

    @Override
    public void initUI() {
        super.initUI();
        commentsArryList = new ArrayList<>();
        UserNAme = sharedPrefrencesManger.getEmail();
        number_of_comments_box = (TextView) view.findViewById(R.id.number_of_comments_box);
        M_comment_recycler_view = (RecyclerView) view.findViewById(R.id.M_comment_recycler_view);

        commentcount();
        commentsArryList = prepareListData();
        messageCommentAdapter = new MessageCommentAdapter(mContext,commentsArryList);
        M_comment_recycler_view.setAdapter(messageCommentAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        lLayout = new LinearLayoutManager(getActivity());
        M_comment_recycler_view.setHasFixedSize(true);
        M_comment_recycler_view.setLayoutManager(lLayout);

    }

    private void commentcount(){
        int count = commentsArryList.size();
        number_of_comments_box.setText("Total comments : " + count);
    }


    private ArrayList<MessagesComments> prepareListData() {

        ArrayList<MessagesComments> temp = new ArrayList<>();

        MessagesComments mModel = new MessagesComments();
        mModel.setcommentsId("1");
        mModel.setcommentsMessages("very very nice picture");
        mModel.setcommentsUserName(UserNAme);
        mModel.setcommentsImageURL(R.drawable.piggylandbg);
        temp.add(mModel);

        mModel = new MessagesComments();
        mModel.setcommentsId("2");
        mModel.setcommentsMessages("nice drawing");
        mModel.setcommentsUserName(UserNAme);
        mModel.setcommentsImageURL(R.drawable.photodefault);
        temp.add(mModel);

        mModel = new MessagesComments();
        mModel.setcommentsId("3");
        mModel.setcommentsMessages("fab.");
        mModel.setcommentsUserName(UserNAme);
        mModel.setcommentsImageURL(R.drawable.car_1);
        temp.add(mModel);

        mModel = new MessagesComments();
        mModel.setcommentsId("4");
        mModel.setcommentsMessages("owsume");
        mModel.setcommentsUserName(UserNAme);
        mModel.setcommentsImageURL(R.drawable.piggylandbg);
        temp.add(mModel);

        mModel = new MessagesComments();
        mModel.setcommentsId("5");
        mModel.setcommentsMessages("great work");
        mModel.setcommentsUserName(UserNAme);
        mModel.setcommentsImageURL(R.drawable.photodefault);
        temp.add(mModel);

        mModel = new MessagesComments();
        mModel.setcommentsId("6");
        mModel.setcommentsMessages("mean to say");
        mModel.setcommentsUserName(UserNAme);
        mModel.setcommentsImageURL(R.drawable.car_1);
        temp.add(mModel);

        mModel = new MessagesComments();
        mModel.setcommentsId("7");
        mModel.setcommentsMessages("nice paint");
        mModel.setcommentsUserName(UserNAme);
        mModel.setcommentsImageURL(R.drawable.piggylandbg);
        temp.add(mModel);

        return temp;
    }

}
