package appcenternl.capgemini.com.simplesql.services.interfaces;

import com.android.volley.VolleyError;

import appcenternl.capgemini.com.simplesql.services.domain.User;

/**
 * Created by appiekap on 03/10/2017.
 */

public interface UserServiceListener {
    void onUsersLoaded(User[] users);
    void onUsersLoadedError(VolleyError error);
}
