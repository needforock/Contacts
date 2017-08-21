package ve.needforock.contacts.data;

import java.util.ArrayList;
import java.util.List;

import ve.needforock.contacts.models.Contact;

/**
 * Created by Soporte on 14-Aug-17.
 */

public class Queries {

    public List<Contact> contacts() {
        List<Contact> contacts = new ArrayList<>();

        List<Contact> contactList = Contact.findWithQuery(Contact.class, "SELECT * FROM Contact ORDER BY name ASC");

        if (contactList != null && contactList.size() > 0) {

            contacts.addAll(contactList);

            /*Collections.sort(contacts, new Comparator<Contact>() {
                @Override
                public int compare(Contact lhs, Contact rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });*/


        }
        return contacts;

    }

    public List<Contact> contactRemove(long id) {

        Contact contactToRemove = Contact.findById(Contact.class, id);
        if (contactToRemove != null) {
            contactToRemove.delete();
        }

        return contacts();
    }
}
