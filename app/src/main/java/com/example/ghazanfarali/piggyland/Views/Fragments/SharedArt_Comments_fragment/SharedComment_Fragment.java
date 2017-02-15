package com.example.ghazanfarali.piggyland.Views.Fragments.SharedArt_Comments_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Utils.SharedPrefrencesManger;
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
    private LinearLayoutManager lLayout;
    EditText commentEdittext;
    ImageView send_comment;
    ArrayList<CommentsMsgs> commentsArryList;
    commentsAdapter commentsAdapter;
    MasterFragment mContext;
    public SharedPrefrencesManger sharedPrefrencesManger;
    String UserNAme = "";
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.comment_like_fragment, container, false);
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
        sharedPrefrencesManger = new SharedPrefrencesManger(getActivity());
        UserNAme = sharedPrefrencesManger.getEmail();
        commentsArryList = new ArrayList<>();
        number_of_like_box = (TextView)view.findViewById(R.id.number_of_like_box);
        comment_recycler_view = (RecyclerView)view.findViewById(R.id.comment_recycler_view);
        commentEdittext = (EditText)view.findViewById(R.id.commentEdittext);
        commentEdittext.requestFocus();
        send_comment = (ImageView)view.findViewById(R.id.send_comment);


        commentsArryList = prepareListData();
        commentsAdapter = new commentsAdapter(mContext,commentsArryList);
        comment_recycler_view.setAdapter(commentsAdapter);



        commentcount();

        send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(commentEdittext.getText().toString() != null && !commentEdittext.getText().toString().isEmpty()){
                    String newComment = commentEdittext.getText().toString();
                    CommentsMsgs mModel = new CommentsMsgs();
                    mModel.setcommentsId("-1");
                    mModel.setcommentsMessages(newComment);
                    mModel.setcommentsUserName(UserNAme);
                    mModel.setcommentsImageURL(R.drawable.piggylandbg);
                   // temp.add(mModel);
                    commentsArryList.add(mModel);

                    commentsAdapter.notifyDataSetChanged();
                    commentcount();
                    commentEdittext.setText("");
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    comment_recycler_view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            comment_recycler_view.scrollToPosition(comment_recycler_view.getAdapter().getItemCount() - 1);
                        }
                    }, 100);

                }

            }
        });
    }



    private void commentcount(){
        int count = commentsArryList.size();
        number_of_like_box.setText("Total comments : " + count);
    }

    @Override
    public void onResume() {
        super.onResume();
        lLayout = new LinearLayoutManager(getActivity());
        comment_recycler_view.setHasFixedSize(true);
        comment_recycler_view.setLayoutManager(lLayout);

    }

    private ArrayList<CommentsMsgs> prepareListData() {

        ArrayList<CommentsMsgs> temp = new ArrayList<>();

        CommentsMsgs mModel = new CommentsMsgs();
        mModel.setcommentsId("1");
        mModel.setcommentsMessages("very very nice picture");
        mModel.setcommentsUserName("Test1");
        mModel.setcommentsImageURL(R.drawable.piggylandbg);
        temp.add(mModel);

        mModel = new CommentsMsgs();
        mModel.setcommentsId("2");
        mModel.setcommentsMessages("very very nice picture nice");
        mModel.setcommentsUserName("Test2");
        mModel.setcommentsImageURL(R.drawable.photodefault);
        temp.add(mModel);

        mModel = new CommentsMsgs();
        mModel.setcommentsId("3");
        mModel.setcommentsMessages("very very nice picture very very nice");
        mModel.setcommentsUserName("Test3");
        mModel.setcommentsImageURL(R.drawable.car_1);
        temp.add(mModel);

        mModel = new CommentsMsgs();
        mModel.setcommentsId("4");
        mModel.setcommentsMessages("very very nice picture very very");
        mModel.setcommentsUserName("Test4");
        mModel.setcommentsImageURL(R.drawable.piggylandbg);
        temp.add(mModel);

        mModel = new CommentsMsgs();
        mModel.setcommentsId("5");
        mModel.setcommentsMessages("very very nice picture and this");
        mModel.setcommentsUserName("Test5");
        mModel.setcommentsImageURL(R.drawable.photodefault);
        temp.add(mModel);

        mModel = new CommentsMsgs();
        mModel.setcommentsId("6");
        mModel.setcommentsMessages("very very nice picture piggy ");
        mModel.setcommentsUserName("Test7");
        mModel.setcommentsImageURL(R.drawable.car_1);
        temp.add(mModel);

        mModel = new CommentsMsgs();
        mModel.setcommentsId("7");
        mModel.setcommentsMessages("very very nice picture piggy land");
        mModel.setcommentsUserName("Test9");
        mModel.setcommentsImageURL(R.drawable.piggylandbg);
        temp.add(mModel);

        return temp;
    }


}
