package model.items.itemMemory;

import model.items.LibraryItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class itemList {
    private List<LibraryItem> listItems = new ArrayList<>();

    public void addItem(LibraryItem item){
        listItems.add(item);
    }

    public void load(String csvFilePath) throws FileNotFoundException {
        File csvFile = new File(csvFilePath);
        Scanner scanner = new Scanner(csvFile);
        System.out.print("test");

        while (scanner.hasNextLine()){
            Scanner lineScanner = new Scanner(scanner.nextLine());
            lineScanner.useDelimiter(",");
        }

    }

}
