package com.example.ghazanfarali.piggyland.Views.Fragments.PeopleInPiggyLand.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ghazanfarali.piggyland.CustomViews.RoundedImageView;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Fragments.PeopleInPiggyLand.Beans.Contact;
import com.gvillani.pinnedlist.GroupListWrapper;
import com.gvillani.pinnedlist.PinnedAdapter;
import com.gvillani.pinnedlist.PinnedListLayout;
import com.gvillani.pinnedlist.PinnedViewHolder;

/**
 * Created by Giuseppe on 09/04/2016.
 */
public class ContactAdapter extends PinnedAdapter {
    private final LayoutInflater mLayoutInflater;
    static com.example.ghazanfarali.piggyland.Controls.OnItemClickListener onItemClickListener;

    public ContactAdapter(Context context, GroupListWrapper listGroup, PinnedListLayout layout) {
        super(listGroup, layout);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public PinnedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_contact, parent, false);
        PinnedViewHolder pinnedViewHolder = new ViewHolderContact(getRowLayout(view));
        return pinnedViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        final Contact contact = (Contact) mListWrapper.getItem(position);

        ((ViewHolderContact) holder).tvName.setText(contact.getName());
        ((ViewHolderContact) holder).tvSurname.setText(contact.getSurname());
        ((ViewHolderContact) holder).ivPhoto.setImageResource(contact.getPhoto());
    }

    static class ViewHolderContact extends PinnedViewHolder implements View.OnClickListener{
        private RoundedImageView ivPhoto;
        private TextView tvName;
        private TextView tvSurname;

        public ViewHolderContact(View rowLayout) {
            super(rowLayout);
            ivPhoto = (RoundedImageView) rowLayout.findViewById(R.id.photo);
            tvName = (TextView) rowLayout.findViewById(R.id.name);
            tvSurname = (TextView) rowLayout.findViewById(R.id.surname);
            rowLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getPosition());
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final com.example.ghazanfarali.piggyland.Controls.OnItemClickListener mItemClickListener) {
        this.onItemClickListener = mItemClickListener;
    }
}

