package appcenternl.capgemini.com.simplesql.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import appcenternl.capgemini.com.simplesql.R;
import appcenternl.capgemini.com.simplesql.services.domain.User;

/**
 * Created by appiekap on 03/10/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {
    private ArrayList<User> mUsers;
    private static LayoutInflater mInflater = null;

    public UserAdapter (Activity activity, int textViewResourceId,ArrayList<User> users) {
        super(activity, textViewResourceId, users);
        try {
            this.mUsers = users;
            mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        catch (Exception e) {
        }
    }

    public int getCount() {
        return this.mUsers.size();
    }

    public User getItem(User position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        private TextView display_name;
        private TextView display_phone;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = mInflater.inflate(R.layout.item_user, null);
                holder = new ViewHolder();

                holder.display_name = (TextView) vi.findViewById(R.id.display_name);
                holder.display_phone = (TextView) vi.findViewById(R.id.display_phone);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            holder.display_name.setText(this.mUsers.get(position).getName());
            holder.display_phone.setText(this.mUsers.get(position).getPhone());


        }
        catch (Exception e) {
        }
        return vi;
    }
}
