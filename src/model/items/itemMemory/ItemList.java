package model.items.itemMemory;

import model.items.Book;
import model.items.LibraryItem;
import model.items.Magazine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class ItemList implements Iterable<LibraryItem> {
    private List<LibraryItem> listItems = new ArrayList<>();

    public List<LibraryItem> getListItems() {return Collections.unmodifiableList(this.listItems);}

    @Override
    public Iterator<LibraryItem> iterator() {
        return listItems.iterator();
    }

    public void addItem(LibraryItem item){
        listItems.add(item);
    }

    public void displayListItems(){
        for (LibraryItem i:this.listItems){
            System.out.println(i.getDisplayInfo());
        }
    }

    public void save(String path) {
        try {
            FileWriter writer = new FileWriter(path);
            for (LibraryItem item : listItems) {
                if (item instanceof Book) {
                    Book book = (Book) item;
                    writer.write("BOOK," + book.getStableId() + "," + book.getTitle() + ","
                            + book.getPublicationData().getYear() + "," + book.getPublicationData().getMonthValue() + "," + book.getPublicationData().getDayOfMonth() + ","
                            + book.getStatus() + "," + book.getAuthor() + "," + book.getIsbn() + "," + book.getGenre() + "," + book.getNombreDePages() + "\n");
                } else if (item instanceof Magazine) {
                    Magazine magazine = (Magazine) item;
                    writer.write("MAGAZINE," + magazine.getStableId() + "," + magazine.getTitle() + ","
                            + magazine.getPublicationData().getYear() + "," + magazine.getPublicationData().getMonthValue() + "," + magazine.getPublicationData().getDayOfMonth() + ","
                            + magazine.getStatus() + "," + magazine.getNombreDePages() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.print("Failed to save item catalogue.\n");
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
                ///System.out.print("Read entry successfully.\n");
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
