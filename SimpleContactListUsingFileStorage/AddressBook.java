
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Contact {
    private String fname;
    private String lname;
    private String phone;

    public Contact(String fname, String lname, String phone) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPhone() {
        return phone;
    }
}

public class AddressBook {
    private List<Contact> contacts;
    private String filePath;

    public AddressBook(String filePath) {
        this.filePath = filePath;
        this.contacts = new ArrayList<>();
        loadContactsFromFile();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContactsToFile();
    }

    public void updateContact(String oldPhone, Contact newContact) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getPhone().equals(oldPhone)) {
                iterator.remove();
                contacts.add(newContact);
                saveContactsToFile();
                return;
            }
        }
        System.out.println("Contact with phone " + oldPhone + " not found.");
    }

    public void deleteContact(String phone) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getPhone().equals(phone)) {
                iterator.remove();
                saveContactsToFile();
                return;
            }
        }
        System.out.println("Contact with phone " + phone + " not found.");
    }

    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact.getFname() + " " + contact.getLname() + ": " + contact.getPhone());
        }
    }

    private void loadContactsFromFile() {
        try (Scanner scanner = new Scanner(new File("contacts.txt"))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Contact contact = new Contact(parts[0], parts[1], parts[2]);
                    contacts.add(contact);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("The file doesnot exist.");
        }
    }

    private void saveContactsToFile() {
        try (PrintWriter writer = new PrintWriter("contacts.txt")) {

            for (Contact contact : contacts) {
                writer.println(contact.getFname() + "," + contact.getLname() + "," + contact.getPhone());
            }
            System.out.println("Contacts saved successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Error saving Contacts to File: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook("Contacts.txt");

        int choice;
        do {
            System.out.println("Choose an option:");
            System.out.println("1. Add a new contact");
            System.out.println("2. Edit an existing contact");
            System.out.println("3. Delete a contact");
            System.out.println("4. Display all contacts");
            System.out.println("0. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter first name: ");
                    String fname = scanner.nextLine();

                    System.out.print("Enter last name: ");
                    String lname = scanner.nextLine();

                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();

                    Contact newContact = new Contact(fname, lname, phone);
                    addressBook.addContact(newContact);

                    addressBook.displayContacts();
                    break;
                case 2:
                    System.out.println("Editing an existing contact:");

                    System.out.print("Enter phone number of the contact to edit: ");
                    String oldPhone = scanner.nextLine();
                    System.out.print("Enter new contact details : ");
                    System.out.print("Enter first name: ");
                    String fnm = scanner.nextLine();

                    System.out.print("Enter last name: ");
                    String lnm = scanner.nextLine();

                    System.out.print("Enter phone number: ");
                    String ph = scanner.nextLine();
                    Contact newContact2 = new Contact(fnm, lnm, ph);
                    addressBook.updateContact(oldPhone, newContact2);
                    break;

                case 3:
                    System.out.println("Deleting an existing contact:");

                    System.out.print("Enter phone number to delete contact: ");
                    String p = scanner.nextLine();

                    addressBook.deleteContact(p);
                case 4:
                    addressBook.displayContacts();
                    break;

                case 0:
                    System.out.println("---------THANK YOU FOR USING ADDRESS BOOK---------");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);
        scanner.close();
    }
}