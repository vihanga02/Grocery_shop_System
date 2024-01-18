package org.example.myproject;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WestministerShoppingManager implements ShopingManager{
    static List<Product> productList1 = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    public  WestministerShoppingManager(List<Product> productList ){
        this.productList1 = productList;
    }

    @Override
    public void displayMenu() {

        System.out.println("Welcome to Westminister SHoping Manager\n" +
                "1.Add a new product.\n" +
                "2.Delete a product.\n" +
                "3.Print the list of product.\n" +
                "4.Save a file.\n" +
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
        if (productList1.size() < 50) {
            System.out.println("Enter the type of the product: ");
            String type = scanner.next();

            System.out.println("Enter the product ID: ");
            String id = scanner.next();

            System.out.println("Enter item count: ");
            int itemCount = scanner.nextInt();

            for (Product product: productList1){
                if ((product.getId()).equals(id)){
                    product.setNumberOfProducts(itemCount);
                    break;
                }
                else{
                    System.out.println("Enter the product name: ");
                    String name = scanner.next();

                    System.out.println("Enter the price: ");
                    double price = scanner.nextDouble();

                    if (type.equals("Electronics")) {
                        System.out.println("Enter the warrenty period: ");
                        int warrant = scanner.nextInt();

                        System.out.println("Enter the brand name: ");
                        String brand = scanner.next();

                        productList1.add(new Electronics(id, name, itemCount, price, brand, warrant));
                        System.out.println("Successfully added.");
                    } else if (type.equals("Clothing")) {
                        System.out.println("Enter the color: ");
                        String color = scanner.next();

                        System.out.println("Enter the size: ");
                        int size = scanner.nextInt();

                        productList1.add(new Clothing(id, name, itemCount, price, color, size));
                        System.out.println("Successfully added.");
                    } else {
                        System.out.println("Wrong product.");
                        addAProduct();
                    }
                }
            }
        }
        else{
            System.out.println("Product count exided!!");
        }
    }

    @Override
    public void deleteProduct() {
        if (productList1.size() > 0) {
            System.out.println("Enter the ID of the deleting element");
            String idToDelete = scanner.next();

            int flag = 0;

            for (Product product : productList1) {
                if ((product.getId().equals(idToDelete))) {
                    productList1.remove(product);
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
            obj.writeObject(productList1);
            obj.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    @Override
    public void printTheProductList() {
        List<String> idNumbers = new ArrayList<>();
        for (Product product: productList1){
            idNumbers.add(product.getId());
        }

        Collections.sort(idNumbers);

        System.out.println("#---------------PRODUCT LIST---------------#");

        for (String id: idNumbers){
            for (Product product: productList1){
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
        return productList1;
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