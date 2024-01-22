package org.example.myproject;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WestministerShoppingManager implements ShopingManager{
    List<Product> productList = loadFile();

    Scanner scanner = new Scanner(System.in);

    public  WestministerShoppingManager(){
    }

    @Override
    public void displayMenu() {

        System.out.println("Welcome to Westminister SHoping Manager\n" +
                "1.Add a new product.\n" +
                "2.Delete a product.\n" +
                "3.Print the list of product.\n" +
                "4.Save a file.\n" +
                "5.Exit.\n" +
                "Enter a option: ");

        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                addAProduct();
                break;
            case 2:
                deleteProduct();
                break;
            case 3:
                printTheProductList();
                break;
            case 4:
                saveInAFile();
                break;
        }
    }

    @Override
    public void addAProduct() {
        if (productList.size() < 50) {
            System.out.println("Enter the type of the product: 1.Electronics        2.Clothing");
            String type = "none";
            int choice = scanner.nextInt();
            if (choice == 1){
                type = "Electronics";
            } else if (choice == 2) {
                type = "Clothing";
            }
            else{
                type = type;
                addAProduct();
            }

            System.out.println("Enter the product ID: ");
            String id = scanner.next();

            System.out.println("Enter item count: ");
            int itemCount = scanner.nextInt();

            boolean in = false;

            for (Product product: productList) {
                if ((product.getId()).equals(id)) {
                    product.setNumberOfProducts(itemCount);
                    in = true;
                    break;
                }
            }
            if (!in) {
                System.out.println("Enter the product name: ");
                String name = scanner.next();

                System.out.println("Enter the price: ");
                double price = scanner.nextDouble();

                if (type.equals("Electronics")) {
                    System.out.println("Enter the warrenty period: ");
                    int warrant = scanner.nextInt();

                    System.out.println("Enter the brand name: ");
                    String brand = scanner.next();

                    productList.add(new Electronics(id, name, itemCount, price, brand, warrant));
                    System.out.println("Successfully added.");
                } else if (type.equals("Clothing")) {
                    System.out.println("Enter the color: ");
                    String color = scanner.next();

                    System.out.println("Enter the size: ");
                    int size = scanner.nextInt();

                    productList.add(new Clothing(id, name, itemCount, price, color, size));
                    System.out.println("Successfully added.");
                } else {
                    System.out.println("Wrong product.");
                    addAProduct();
                }
            }
        }
        else{
            System.out.println("Product count exided!!");
        }
    }

    @Override
    public void deleteProduct() {
        if (productList.size() > 0) {
            System.out.println("Enter the ID of the deleting element");
            String idToDelete = scanner.next();

            int flag = 0;

            for (Product product : productList) {
                if ((product.getId().equals(idToDelete))) {
                    productList.remove(product);
                    product.setNumberOfProducts(-1);
                    flag = 1;
                    System.out.println("Item Deleted");
                    break;
                }
            }

            if (flag == 0) {
                System.out.println("Enter a valid ID number");
                deleteProduct();
            }
        }
        else{
            System.out.println("No products for delete!");
        }
    }

    @Override
    public void saveInAFile() {
        try{
            FileOutputStream data = new FileOutputStream("Data1.ser");
            ObjectOutputStream obj = new ObjectOutputStream(data);
            obj.writeObject(productList);
            obj.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    @Override
    public void printTheProductList() {
        List<String> idNumbers = new ArrayList<>();
        for (Product product: productList){
            idNumbers.add(product.getId());
        }

        Collections.sort(idNumbers);

        System.out.println("#---------------PRODUCT LIST---------------#");

        for (String id: idNumbers){
            for (Product product: productList){
                if (product.getId() == null){
                    System.out.println("nullllllll");
                    break;
                }
                if (id.equals(product.getId())){
                    System.out.print("Product ID: " + product.getId() +
                            "    Product name: " + product.getProductName() +
                            "     Price: Rs." + product.getPrice());
                    if (product instanceof Electronics) {
                        System.out.print("    Product Type: Electronics    Brand; " + ((Electronics) product).getBrandName() +
                                "    Warranty Period: " + ((Electronics) product).getWarrantyPeriod());
                    }
                    else{
                        System.out.print("    Product Type: Clothing    Size: " + ((Clothing) product).getSize()  +
                                "    Color: " + ((Clothing) product).getColor());
                    }

                    System.out.println("\n");
                }
            }
        }
    }

    public  List<Product> getList(){
        return productList;
    }

    @Override
    public List<Product> loadFile() {
//        productList.add(new Electronics("abc", "MacbookAir", 2, 102, "Apple", 12));
//        productList.add(new Clothing("xyz", "MacbookAir", 1, 102, "red", 1));

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Data1.ser"))){
            List<Product> pr = (List<Product>) in.readObject();
            productList = pr;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("errorrrrrrrrrrrr");
            e.printStackTrace();
        }
        return productList;
    }

    public boolean getState(){
        System.out.println("1.Exit.\n" +
                "2.Go again.");
        int state = scanner.nextInt();
        if (state == 1){
            scanner.close();
            return false;
        } else if (state == 2) {
            return true;
        }else {
            return getState();
        }
    }
}