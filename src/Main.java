import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Jav", "Japan", 120, "ok"));
        products.add(new Product(2, "Kav", "Korea", 100, "ok"));
        products.add(new Product(3, "Aav", "America", 115, "ok"));
        products.add(new Product(4, "Vav", "Viet Nam", 118, "ok"));
        products.add(new Product(5, "Tav", "ThaiLand", 199, "ok"));
        String file = "Product.txt";
        saveFile(file, products);

        List<Product> products1 = readFile(file);
        for (Product product : products1) {
            System.out.println(product);
        }

        add(file, new Product(6, "Tav", "ThaiLan", 111, "ok"));
        render(file);
        System.out.println(search(5, file));


    }

    public static void saveFile(String file, List<Product> products) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(products);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Product> readFile(String file) {
        List<Product> products = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            products = (List<Product>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void add(String file, Product product) {
        List<Product> products = readFile(file);
        products.add(product);
        saveFile(file, products);
    }

    public static void render(String file) {
        List<Product> products = readFile(file);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static Product search(int id, String file) {
        List<Product> products = readFile(file);
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return products.get(i);
            }
        }
        return null;
    }
}
