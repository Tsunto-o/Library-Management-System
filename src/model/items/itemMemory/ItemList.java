package model.items.itemMemory;

import model.items.Book;
import model.items.LibraryItem;
import model.items.Magazine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ItemList {
    private List<LibraryItem> listItems = new ArrayList<>();

    public void addItem(LibraryItem item){
        listItems.add(item);
    }

    public void displayListItems(){
        for (LibraryItem i:this.listItems){
            System.out.println(i.getDisplayInfo());
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
                    if (entryType.equals("BOOK")) {
                        try {
                            Book entry = new Book(lineScanner.nextInt(), lineScanner.next(),LocalDate.of(lineScanner.nextInt(), lineScanner.nextInt(), lineScanner.nextInt()), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.nextInt());
                            this.listItems.add(entry);
                        } catch (Exception e){
                            System.out.print("Failed to read entry. Skipped.\n");
                            continue;
                        }
                    }
                    else if (entryType.equals("MAGAZINE")) {
                        try {
                            Magazine entry = new Magazine(lineScanner.nextInt(), lineScanner.next(),LocalDate.of(lineScanner.nextInt(), lineScanner.nextInt(), lineScanner.nextInt()),lineScanner.next(), lineScanner.nextInt());
                            this.listItems.add(entry);
                        } catch (Exception e){
                            System.out.print("Failed to read entry. Skipped.\n");
                            continue;
                        }
                    }
                    else {
                        System.out.print("Invalid entry. Skipped.\n");
                        continue;
                    }
                }
                System.out.print("Read entry successfully.\n");
                ///Book test = new Book(17,"Cyrano de Bergerac", LocalDate.of(1897, 12, 28), "available", "Edmond Rostand", "jsp123", "Comedy", 389);
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("Item list save not found. Creating new one...");
            try {
                File csvFile = new File(csvFilePath);
                csvFile.createNewFile();

            }
            catch (IOException e2) {
                System.out.print("Failed creating a new item list save file. Program working without memory.");
            }
        }


    }

}
