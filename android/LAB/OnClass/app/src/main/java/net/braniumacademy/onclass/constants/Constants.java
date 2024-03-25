package net.braniumacademy.onclass.constants;

import net.braniumacademy.onclass.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static String imagePath = "";
    public static int contact_position = -1;
    public static List<Contact> contactList = new ArrayList<>();

/* FOR DATABSE */
//        myDB = new MyDB(this, "ContactDB", null, 1);
//        myDB.addContact(new Contact(0, "Trinh Han", "098988899", "img1"));
//        myDB.addContact(new Contact(0, "Trinh Hang", "098988899", "img2"));
//        contactList = myDB.getAllContacts();
//        contactAdapter = new ContactAdapter(this, contactList);
//        mainBinding.recycleViewContact.setLayoutManager(new LinearLayoutManager(this));
//        mainBinding.recycleViewContact.setAdapter(contactAdapter);

    private Constants() {
    }
}
