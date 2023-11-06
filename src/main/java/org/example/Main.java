
package org.example;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.Model.Product;
import org.example.Model.RemoveToString;
import org.example.Service.productService;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) {


            productService productService = new productService();

            List<Product> productList = productService.getProductList();

            try {

                FileReader fileReader = new FileReader("resources/inventory.csv");

                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

                boolean header = true;

                for (CSVRecord csvRecord : csvParser) {
                    if (header ) {

                        header  = false;
                        continue;
                    }

                    int codigo = Integer.parseInt(csvRecord.get(0));
                    String nombre = csvRecord.get(1);
                    String descripcion = csvRecord.get(2);
                    String categoria = csvRecord.get(3);
                    String etiqueta = csvRecord.get(4);
                    double precio = Double.parseDouble(csvRecord.get(5));
                    String url = csvRecord.get(6);

                    Product products = new Product(codigo, nombre, descripcion, categoria, etiqueta, precio, url);
                    productList.add(products);

                    RemoveToString remove = new RemoveToString(codigo, nombre, descripcion, categoria, etiqueta, precio, url);
                    System.out.println(remove);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
    }

