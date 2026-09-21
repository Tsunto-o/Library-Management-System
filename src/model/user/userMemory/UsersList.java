package model.user.userMemory;

import model.items.Book;
import model.user.*;
import model.items.Magazine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UsersList {
    private List<User> listUsers = new ArrayList<>();

    public List<User> getListUsers() {return this.listUsers;}

    public void addUser(User user){
        listUsers.add(user);
    }

    public void removeUser(String id) {
        Iterator<User> iterator = listUsers.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (id.equals(user.getStableId())) {
                iterator.remove();
                return;
            }
        }
    }

    public void displaylistUsers(){
        for (User i:this.listUsers){
            System.out.println(i.getDisplayInfo());
        }
    }

    public void save(String path) {
        try {
            FileWriter writer = new FileWriter(path);
            for (User i:this.listUsers){
                String entryType = i.getRolePermissions();
                if (entryType.equals("ADMIN")) {
                    writer.write(i.getRolePermissions() + "," + i.getStableId() + "," + i.getPassword() + "," + i.getFirstName() + "," + i.getLastName() + "," + i.getBirthdayDate().getYear() + "," + i.getBirthdayDate().getMonthValue() + "," + i.getBirthdayDate().getDayOfMonth() + "\n");
                }
                else if (entryType.equals("LIBRARIAN")) {
                    writer.write(i.getRolePermissions() + "," + i.getStableId() + "," + i.getPassword() + "," + i.getFirstName() + "," + i.getLastName() + "," + i.getBirthdayDate().getYear() + "," + i.getBirthdayDate().getMonthValue() + "," + i.getBirthdayDate().getDayOfMonth() + "\n");
                }
                if (entryType.equals("MEMBER")) {
                    writer.write(i.getRolePermissions() + "," + i.getStableId() + "," + i.getPassword() + "," + i.getFirstName() + "," + i.getLastName() + "," + i.getBirthdayDate().getYear() + "," + i.getBirthdayDate().getMonthValue() + "," + i.getBirthdayDate().getDayOfMonth() + "," + ((Member) i).getActiveLoanLimit() +  "\n");
                }
            }
            writer.close();
        }catch (IOException e){
            System.out.print("User list save not found. Creating new one...");
            try {
                File csvFile = new File(path);
                csvFile.createNewFile();
                FileWriter writer = new FileWriter(path);
                for (User i:this.listUsers){
                    writer.write(i.getRolePermissions() + "," + i.getStableId() + "," + i.getFirstName() + "," + i.getLastName() + "," + i.getBirthdayDate().getYear() + "," + i.getBirthdayDate().getMonth() + "," + i.getBirthdayDate().getDayOfMonth() + "\n");
                }
                writer.close();
            }
            catch (IOException e2) {
                System.out.print("Failed creating a new user list save file. Program working without memory.");
            }
        }
    }

    public void load(String csvFilePath) {
        try {
            File csvFile = new File(csvFilePath);
            Scanner fileScanner = new Scanner(csvFile);

            while (fileScanner.hasNextLine()){
                Scanner lineScanner = new Scanner(fileScanner.nextLine());
                lineScanner.useDelimiter(",");

                if (lineScanner.hasNext()){
                    String entryType = lineScanner.next();
                    if (entryType.equals("ADMIN")) {
                        try {
                            Admin entry = new Admin(lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), LocalDate.of(lineScanner.nextInt(), lineScanner.nextInt(), lineScanner.nextInt()));
                            this.listUsers.add(entry);
                        } catch (Exception e){
                            System.out.print("Failed to read entry. Skipped.\n");
                            continue;
                        }
                    }
                    else if (entryType.equals("LIBRARIAN")) {
                        try {
                            Librarian entry = new Librarian(lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), LocalDate.of(lineScanner.nextInt(), lineScanner.nextInt(), lineScanner.nextInt()));
                            this.listUsers.add(entry);
                        } catch (Exception e) {
                            System.out.print("Failed to read entry. Skipped.\n");
                            continue;
                        }
                    }
                    else if (entryType.equals("MEMBER")) {
                            try {
                                Member entry = new Member(lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), LocalDate.of(lineScanner.nextInt(), lineScanner.nextInt(), lineScanner.nextInt()), lineScanner.nextInt());
                                this.listUsers.add(entry);
                            } catch (Exception e){
                                System.out.print("Failed to read entry. Skipped...\n");
                                continue;
                            }
                    } else {
                        System.out.print("Invalid entry. Skipped.\n");
                        continue;
                    }
                }
                System.out.print("Read entry successfully.\n");
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("User list save not found. Creating new one...");
            try {
                File csvFile = new File(csvFilePath);
                csvFile.createNewFile();
            }
            catch (IOException e2) {
                System.out.print("Failed creating a new user list save file. Program working without memory.");
            }
        }


    }

}
