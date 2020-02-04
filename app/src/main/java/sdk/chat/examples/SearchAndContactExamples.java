package sdk.chat.examples;

import android.content.Context;

import androidx.fragment.app.Fragment;

import java.util.List;

import co.chatsdk.core.dao.User;
import co.chatsdk.core.session.ChatSDK;
import co.chatsdk.core.types.ConnectionType;
import io.reactivex.functions.Action;

public class SearchAndContactExamples extends BaseExample {
    public SearchAndContactExamples(Context context) {

        // Get a user called ben searching by name and email
        dm.add(ChatSDK.search().usersForIndexes("Ben", 1, "name", "email").subscribe(user -> {

            // Add contact
            dm.add(ChatSDK.contact().addContact(user, ConnectionType.Contact).subscribe(new Action() {
                @Override
                public void run() throws Exception {
                    // Contact added
                }
            }, this));

            // Get contacts
            List<User> contacts = ChatSDK.contact().contacts();

            // Check to see if a contact exists
            boolean exists = ChatSDK.contact().exists(user);

            // Delete contacts
            dm.add(ChatSDK.contact().deleteContact(user, ConnectionType.Contact).subscribe(new Action() {
                @Override
                public void run() throws Exception {
                    // Contact deleted
                }
            }, this));

            // Get a fragment containing a list of contacts
            Fragment contactFragment = ChatSDK.ui().contactsFragment();

            // Start the search activity
            ChatSDK.ui().startSearchActivity(context);


        }, this));

    }
}