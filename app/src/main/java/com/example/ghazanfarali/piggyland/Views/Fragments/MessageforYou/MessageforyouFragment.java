package com.example.ghazanfarali.piggyland.Views.Fragments.MessageforYou;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.Controls.OnItemClickListener;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans.mygallarylist;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.MessageforYou.adapter.messageForYouAdapter;
import com.example.ghazanfarali.piggyland.Views.Fragments.MessageforYou.adapter.messagesDetailsAdapter;

import java.util.ArrayList;

/**
 * Created by amir.jehangir on 1/30/2017.
 */
public class MessageforyouFragment extends MasterFragment {
    private View view;
    RecyclerView rv_list_gallary;
    private LinearLayoutManager lLayout;
    ArrayList<mygallarylist> smartToolsList;
    messageForYouAdapter mygallaryAdapter;
   messagesDetailsAdapter mymessagesdetailsAdapter;
    MasterFragment mContext;
  //  Context mContext;

    public static MessageforyouFragment newInstance() {

        return new MessageforyouFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.messageforyou_fragment, container, false);
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

        smartToolsList = new ArrayList<>();
        rv_list_gallary = (RecyclerView)view.findViewById(R.id.rv_listview_mygallary);
//        rv_list_gallary.addItemDecoration(new MyGallaryITemDecor(getActivity()));

        smartToolsList = prepareListData();
        mygallaryAdapter = new messageForYouAdapter(mContext,smartToolsList);
        rv_list_gallary.setAdapter(mygallaryAdapter);

        mygallaryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(getActivity(),""+position,Toast.LENGTH_LONG).show();
               // showRelatedDocumentsDialog();
            }

        });
    }
    @Override
    public void onResume() {
        super.onResume();
        lLayout = new LinearLayoutManager(getActivity());
        rv_list_gallary.setHasFixedSize(true);
        rv_list_gallary.setLayoutManager(lLayout);
//        int viewHeight = (int) (getResources().getDimension(R.dimen.x150) * 2);
//        rv_list_gallary.getLayoutParams().height = viewHeight;

//        if (prefHelper.getApplicationLanguage().equalsIgnoreCase(CommonConstants.LANG_ARABIC)) {
//            lLayout.setReverseLayout(true);
//        }
    }

    private ArrayList<mygallarylist> prepareListData() {

        ArrayList<mygallarylist> temp = new ArrayList<>();

        mygallarylist mModel = new mygallarylist();
        mModel.setmygallaryId("1");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.piggylandbg);
        temp.add(mModel);

        mModel = new mygallarylist();
        mModel.setmygallaryId("2");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.photodefault);
        temp.add(mModel);

        mModel = new mygallarylist();
        mModel.setmygallaryId("3");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.car_1);
        temp.add(mModel);

        mModel = new mygallarylist();
        mModel.setmygallaryId("4");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.piggylandbg);
        temp.add(mModel);

        mModel = new mygallarylist();
        mModel.setmygallaryId("5");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.photodefault);
        temp.add(mModel);

        mModel = new mygallarylist();
        mModel.setmygallaryId("6");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.car_1);
        temp.add(mModel);

        mModel = new mygallarylist();
        mModel.setmygallaryId("7");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.piggylandbg);
        temp.add(mModel);

        return temp;
    }


    private void showRelatedDocumentsDialog() {
        Dialog dialog = new Dialog(getActivity());
        // remove dialog title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_messages_details);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));

        RecyclerView rvRelatedDocs = (RecyclerView) dialog.findViewById(R.id.rv_messages_details);

        LinearLayoutManager llManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvRelatedDocs.setLayoutManager(llManager);


        mymessagesdetailsAdapter = new messagesDetailsAdapter(mContext,smartToolsList);
        rvRelatedDocs.setAdapter(mymessagesdetailsAdapter);
      //  GeneralBaseAdapter<PadRelatedDocsCell> relatedDocsAdap = new GeneralBaseAdapter<PadRelatedDocsCell>(getActivity(), R.layout.list_item_pad_related_documents, PadRelatedDocsCell.class, templateDetailObj.getAttachments());
     //   rvRelatedDocs.setAdapter(relatedDocsAdap);

        dialog.show();
    }


}
