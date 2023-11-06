package org.example.Service;

import org.example.Model.Product;
import org.example.Model.RemoveToString;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class productService implements IProductservices {
    private List<Product> productList;
    private int id = 1;

    public List<Product> getProductList() {
        return productList;
    }

    public productService() {
        productList = new ArrayList<>();
    }
    public List<Product> obtenerProductos() {
        return productList;
    }

    @Override
    public void agregarProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese un nuevo producto: ");
        String name = scanner.next();
        System.out.print("Ingrese una descripcion ");
        String description = scanner.next();
        System.out.print("Ingrese la categoria ");
        String category = scanner.next();
        System.out.print("Ingrese  la etiqueta: ");
        String label = scanner.next();
        System.out.print("Ingrese el precio: ");
        Double price = scanner.nextDouble();
        System.out.print("Ingrese  la imagen del producto: ");
        String url = scanner.next();
        productList.add(new Product(id, name, description, category, label, price, url));
        productList.stream().forEach(x -> System.out.println(x));
        System.out.println(toString());


    }
    public void cargarProductosDesdeCSV(String archivoCSV) {
        try {
            File file = new File(archivoCSV);
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");

                if (fields.length == 7) {
                    int codigo = Integer.parseInt(fields[0].trim());
                    String nombre = fields[1].trim();
                    String descripcion = fields[2].trim();
                    String categoria = fields[3].trim();
                    String etiqueta = fields[4].trim();
                    double precio = Double.parseDouble(fields[5].trim());
                    String url = fields[6].trim();
                    Product product = new Product(codigo, nombre, descripcion, categoria, etiqueta, precio, url);
                    productList.add(product);
                    RemoveToString remove = new RemoveToString(codigo, nombre, descripcion, categoria, etiqueta, precio, url);
                    System.out.println(remove);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
