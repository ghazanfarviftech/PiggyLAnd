package com.example.ghazanfarali.piggyland.Views.Fragments.PeopleInPiggyLand;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ghazanfarali.piggyland.Controls.OnItemClickListener;
import com.example.ghazanfarali.piggyland.EndPoint.ApiClient;
import com.example.ghazanfarali.piggyland.EndPoint.ApiInterface;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.GetUsers.GetUsersResponse;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.PeopleInPiggyLand.Beans.Contact;
import com.example.ghazanfarali.piggyland.Views.Fragments.PeopleInPiggyLand.adapters.ContactAdapter;
import com.gvillani.pinnedlist.GroupListWrapper;
import com.gvillani.pinnedlist.PinnedListLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Amir.jehangir on 2/5/2017.
 */
public class PeopleInPiggyLandFragment extends MasterFragment {
    public View view;
    private PinnedListLayout mPinnedListLayout;
    private ContactAdapter mListAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.activity_members, container, false);
//            mPinnedListLayout = (PinnedListLayout) view.findViewById(R.id.pinned_layout);
//            mRecyclerView = mPinnedListLayout.getRecyclerView();
            initLayout();

//            startService();
        } else {
            if (view != null){

            }
                //  userProfileActivity.hideHeaderLayout();
              //  userProfileActivity.setHeaderTitle("");
        }
        return view;

    }


    @Override
    public void initUI() {
      //  super.initUI();
       // initLayout();
    }

    private void initLayout() {



        try {
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<GetUsersResponse> call = apiService.getUsers();//getLogin(tie_username.getText().toString(), tei_password.getText().toString(), address);
            call.enqueue(new retrofit2.Callback<GetUsersResponse>() {
                @Override
                public void onResponse(Call<GetUsersResponse> call, Response<GetUsersResponse> response) {
                    GetUsersResponse statusCode = response.body();
                    UserList(response.body());
                }

                @Override
                public void onFailure(Call<GetUsersResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e("", t.toString());
                }
            });
        } catch (Exception e) {
            e.getLocalizedMessage();
        }


        mPinnedListLayout = (PinnedListLayout) view.findViewById(R.id.pinned_layout);
        mRecyclerView = mPinnedListLayout.getRecyclerView();


        Contact c1 = new Contact("Loading...", "Loading...", R.drawable.contact1);


        List<GroupListWrapper.Selector> contacts = new ArrayList<>();

        contacts.add(c1);


        GroupListWrapper listGroup = GroupListWrapper.createAlphabeticList(contacts, GroupListWrapper.ASCENDING);

        mListAdapter = new ContactAdapter(getActivity(), listGroup, mPinnedListLayout);
        mListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(getActivity(),"Members"+position,Toast.LENGTH_LONG).show();
            }
        });
        mRecyclerView.setAdapter(mListAdapter);
        mRecyclerView.setVisibility(view.GONE);

    }


    private void UserList(GetUsersResponse result) {
        mRecyclerView.setVisibility(view.VISIBLE);
        mPinnedListLayout = (PinnedListLayout) view.findViewById(R.id.pinned_layout);
        mRecyclerView = mPinnedListLayout.getRecyclerView();
        List<GroupListWrapper.Selector> contacts = new ArrayList<>();
        for (int i = 0; i < result.getUsers().length; i++) {
            Contact contactAdd;
            if(result.getUsers()[i].getUsername().contentEquals("")){
                contactAdd = new Contact("NONAME","NONAME",R.drawable.contact1);
            }else{
                contactAdd = new Contact(result.getUsers()[i].getUsername(),result.getUsers()[i].getUsername(),R.drawable.contact1);
            }
            contacts.add(contactAdd);

        }

        GroupListWrapper listGroup = GroupListWrapper.createAlphabeticList(contacts, GroupListWrapper.ASCENDING);

        mListAdapter = new ContactAdapter(getActivity(), listGroup, mPinnedListLayout);
        mListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(getActivity(),"Members"+position,Toast.LENGTH_LONG).show();
            }
        });
        mRecyclerView.setAdapter(mListAdapter);
    }


}
