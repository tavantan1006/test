package controller;

import model.Contact;
import view.ContactMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManagement implements IGeneralManagement<Contact> {
    List<Contact> contacts = new ArrayList<>();

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public void addNew(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public void deleteInfo(int index) {
        contacts.remove(index);
    }

    @Override
    public void updateInfo(int index, Contact contact) {
        contacts.set(index, contact);
    }

    @Override
    public void displayAll() {
        int count = 0;
        for (Contact contact : contacts) {
            System.out.println(contact);
            count++;
            if (count == 5) {
                count = 0;
                System.out.println("ẤN ENTER ĐỂ XEM TIẾP");
                ContactMenu.scanner.nextLine();
            }
        }
    }

    @Override
    public List<Contact> readFile(String path) {
        try {
            InputStream is = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(is);
            contacts = (List<Contact>) ois.readObject();
            ois.close();
            is.close();
        } catch (EOFException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File đang trống");
        }
        return contacts;
    }

    @Override
    public void writeFile(String path) {
        try {
            OutputStream os = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(contacts);
            oos.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int findIndex(String numberPhone) {
        int index = -1;
        for (int i = 0; i < contacts.size(); i++) {
            if (numberPhone.equals(contacts.get(i).getNumberPhone())) {
                index = i;
                break;
            }
        }
        return index;
    }
}
