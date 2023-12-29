import java.util.Scanner;
import java.util.ArrayList;

class Contact {
    private String fname;
    private String lname;
    private String address;
    private String city;
    private String zip;
    private String state;
    private String phone;
    private String email;

    public Contact(String fname, String lname, String address, String city, String zip, String state, String phone,
            String email) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getaddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void updateContact(String address, String city, String state, String zip, String phone, String email) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String toString() {
        return String.format(
                "NAME : %s %s\nADDRESS : %s\nCITY : %s\nSTATE :%s\nZIP : %s\nPHONE NUMBER : %s\nEMAIL : %s\n",
                fname, lname, address, city, state, zip, phone, email);
    }

}

class Address {
    private ArrayList<Contact> contacts;

    public Address() {
        contacts = new ArrayList<Contact>();
    }

    public void addAddress(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added Successfully!!");
    }

    public void display() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts in the address book.");
        } else {
            System.out.println("Address book\n");
            for (Contact c : contacts) {
                System.out.println(c);
                System.out.println();

            }
        }
    }

    public void editAddress(String firstName, String lastName, Scanner scanner) {
        for (Contact contact : contacts) {
            if (contact.getFname().equalsIgnoreCase(firstName) && contact.getLname().equalsIgnoreCase(lastName)) {
                System.out.println("Enter the updated details :");

                System.out.print("Enter address: ");
                String address = scanner.nextLine();

                System.out.print("Enter city: ");
                String city = scanner.nextLine();

                System.out.print("Enter state: ");
                String state = scanner.nextLine();

                System.out.print("Enter zip: ");
                String zip = scanner.nextLine();

                System.out.print("Enter phone number: ");
                String phone = scanner.nextLine();

                System.out.print("Enter email: ");
                String email = scanner.nextLine();

                contact.updateContact(address, city, state, zip, phone, email);
                System.out.println("Contact updated successfully\n.");
                return;

            }
        }
        System.out.println("CONTACT NOT AVAILABLE");
    }

    public void deleteAddress(String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.getFname().equalsIgnoreCase(firstName) && contact.getLname().equalsIgnoreCase(lastName)) {
                contacts.remove(contact);
                System.out.println("Contact deleted successfully\n.");
                return;
            } else {
                System.out.println("CONTACT NOT AVAILABLE");
            }
        }
    }
}

public class AddressBook {
    public static void main(String[] args) {
        System.out.println("-------WELCOME TO ADDRESS BOOK PROGRAM-------");

        Address address = new Address();
        Scanner scanner = new Scanner(System.in);

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

                    System.out.print("Enter address: ");
                    String addressStr = scanner.nextLine();

                    System.out.print("Enter city: ");
                    String city = scanner.nextLine();

                    System.out.print("Enter state: ");
                    String state = scanner.nextLine();

                    System.out.print("Enter zip: ");
                    String zip = scanner.nextLine();

                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    Contact newContact = new Contact(fname, lname, addressStr, city, state, zip, phone, email);
                    address.addAddress(newContact);

                    address.display();
                    break;
                case 2:
                    System.out.println("Editing an existing contact:");

                    System.out.print("Enter first name of the contact to edit: ");
                    String editFirstName = scanner.nextLine();

                    System.out.print("Enter last name of the contact to edit: ");
                    String editLastName = scanner.nextLine();

                    address.editAddress(editFirstName, editLastName, scanner);
                    break;

                case 3:
                    System.out.println("Deleting an existing contact:");

                    System.out.print("Enter first name of the contact to edit: ");
                    String delFName = scanner.nextLine();

                    System.out.print("Enter last name of the contact to edit: ");
                    String delLName = scanner.nextLine();

                    address.deleteAddress(delFName, delLName);
                case 4:
                    address.display();
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
