package com.example.ghazanfarali.piggyland.Views.Fragments.PeopleInPiggyLand;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.Controls.OnItemClickListener;
import com.example.ghazanfarali.piggyland.EndPoint.ApiClient;
import com.example.ghazanfarali.piggyland.EndPoint.ApiInterface;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.LoginResponse;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.Profile;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.Login.LoginActivity;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.PeopleInPiggyLand.Beans.Contact;
import com.example.ghazanfarali.piggyland.Views.Fragments.PeopleInPiggyLand.Beans.ResponseUser;
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
public class PeopleInPiggyLandFragment extends MasterFragment{
    private View view;
    private PinnedListLayout mPinnedListLayout;
    private ContactAdapter mListAdapter;
    private RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.activity_members, container, false);
            // mContext.getApplicationContext();
            initUI();

//            startService();
        } else {
            if (view != null)
                //  userProfileActivity.hideHeaderLayout();
                userProfileActivity.setHeaderTitle("");
        }
        return view;

    }

    @Override
    public void initUI() {
        super.initUI();
        initLayout();
    }

    private void initLayout() {

        try {
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);




            Call<List<ResponseUser>> call = apiService.getUsers();//getLogin(tie_username.getText().toString(), tei_password.getText().toString(), address);
            call.enqueue(new retrofit2.Callback<List<ResponseUser>>() {
                @Override
                public void onResponse(Call<List<ResponseUser>> call, Response<List<ResponseUser>> response) {
                    List<ResponseUser> statusCode = response.body();
                }

                @Override
                public void onFailure(Call<List<ResponseUser>> call, Throwable t) {
                    // Log error here since request failed
                    Log.e("", t.toString());
                }
            });
        } catch (Exception e) {
            e.getLocalizedMessage();
        }



        mPinnedListLayout = (PinnedListLayout) view.findViewById(R.id.pinned_layout);
        mRecyclerView = mPinnedListLayout.getRecyclerView();

        Contact c1 = new Contact("Ben", "Weber", R.drawable.contact1);
        Contact c2 = new Contact("Emma", "Hartmann", R.drawable.contact9);
        Contact c3 = new Contact("Gustav", "Eriksson", R.drawable.contact7);
        Contact c4 = new Contact("Lucas", "Nillson", R.drawable.contact4);
        Contact c5 = new Contact("Maia", "Andersson", R.drawable.contact11);
        Contact c6 = new Contact("Oscar", "Karlsson", R.drawable.contact6);
        Contact c7 = new Contact("Williams", "Johansson", R.drawable.contact5);
        Contact c8 = new Contact("Jayden", "De Jong", R.drawable.contact4);
        Contact c9 = new Contact("Julia", "Meijer", R.drawable.contact11);
        Contact c10 = new Contact("Lieke", "Visser", R.drawable.contact2);
        Contact c11 = new Contact("Luuk", "Tassi", R.drawable.contact3);
        Contact c12 = new Contact("Sam", "Van den Berg", R.drawable.contact1);
        Contact c13 = new Contact("Stijn", "De Vries", R.drawable.contact6);
        Contact c14 = new Contact("Thomas", "Van Dijk", R.drawable.contact5);
        Contact c15 = new Contact("Franco", "Bianchi", R.drawable.contact3);
        Contact c16 = new Contact("Giuseppe", "Villani", R.drawable.me);
        Contact c17 = new Contact("Lisa", "Verdi", R.drawable.contact9);
        Contact c18 = new Contact("Maria", "Rossi", R.drawable.contact10);
        Contact c19 = new Contact("Leonor", "Santos", R.drawable.contact9);
        Contact c20 = new Contact("Santiago", "Ferreira", R.drawable.contact1);
        Contact c21 = new Contact("Maria", "Pereira", R.drawable.contact9);
        Contact c22 = new Contact("Francisco", "Sousa", R.drawable.contact4);
        Contact c23 = new Contact("Rodrigo", "Martins", R.drawable.contact5);
        Contact c24 = new Contact("Duarte", "Gomes", R.drawable.contact6);
        Contact c25 = new Contact("Lucia", "Garcia", R.drawable.contact9);
        Contact c26 = new Contact("Pedro", "Fernandez", R.drawable.contact1);
        Contact c27 = new Contact("Daniela", "Gonzales", R.drawable.contact9);
        Contact c28 = new Contact("Alvaro", "Perez", R.drawable.contact7);
        Contact c29 = new Contact("David", "Martin", R.drawable.contact3);
        Contact c30 = new Contact("Camille", "Bernard", R.drawable.contact9);
        Contact c31 = new Contact("Louis", "Robert", R.drawable.contact1);
        Contact c32 = new Contact("Ghazanfar", "Ali", R.drawable.contact4);
        Contact c33 = new Contact("Daniel", "Kimani", R.drawable.contact8);
        Contact c34 = new Contact("Faith", "Mwangi", R.drawable.contact9);
        Contact c35 = new Contact("Jamesohn", "Kamau", R.drawable.contact1);
        Contact c36 = new Contact("Jonson", "Kariuki", R.drawable.contact3);
        Contact c37 = new Contact("Victor", "Ochieng", R.drawable.contact6);
        Contact c38 = new Contact("Aziza", "Elzarak", R.drawable.contact9);
        Contact c39 = new Contact("Youssef", "Jamil", R.drawable.contact1);
        Contact c40 = new Contact("Bob", "Miller", R.drawable.contact4);
        Contact c41 = new Contact("Carl", "Mayer", R.drawable.contact6);
        Contact c42 = new Contact("Bill", "Carson", R.drawable.contact5);
        Contact c43 = new Contact("Thom", "Sayer", R.drawable.contact1);
        Contact c44 = new Contact("Jim", "Hall", R.drawable.contact6);
        Contact c45 = new Contact("Paul", "Stroustrup", R.drawable.contact3);
        Contact c46 = new Contact("Ghazanfar", "Ali", R.drawable.me);

        List<GroupListWrapper.Selector> contacts = new ArrayList<>();

        contacts.add(c1);
        contacts.add(c2);
        contacts.add(c3);
        contacts.add(c4);
        contacts.add(c5);
        contacts.add(c6);
        contacts.add(c7);
        contacts.add(c8);
        contacts.add(c9);
        contacts.add(c10);
        contacts.add(c11);
        contacts.add(c12);
        contacts.add(c13);
        contacts.add(c14);
        contacts.add(c15);
        contacts.add(c16);
        contacts.add(c17);
        contacts.add(c18);
        contacts.add(c19);
        contacts.add(c20);
        contacts.add(c21);
        contacts.add(c22);
        contacts.add(c23);
        contacts.add(c24);
        contacts.add(c25);
        contacts.add(c26);
        contacts.add(c27);
        contacts.add(c28);
        contacts.add(c29);
        contacts.add(c30);
        contacts.add(c31);
        contacts.add(c32);
        //contacts.add(c32);
        contacts.add(c33);
        contacts.add(c34);
        contacts.add(c35);
        contacts.add(c36);
        contacts.add(c37);
        contacts.add(c38);
        contacts.add(c39);
        contacts.add(c40);
        contacts.add(c41);
        contacts.add(c42);
        contacts.add(c43);
        contacts.add(c44);
        contacts.add(c45);
        contacts.add(c46);

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
