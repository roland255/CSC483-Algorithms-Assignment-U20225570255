import java.util.*;

// --- PRODUCT CLASS ---
class Product {
    private int productId;
    private String productName;
    private double price;

    public Product(int productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
}

// --- SEARCH ENGINE ---
class TechMartSearch {
    private Map<String, Product> nameIndex = new HashMap<>();

    public Product sequentialSearchById(Product[] products, int targetId) {
        for (Product p : products) {
            if (p.getProductId() == targetId) return p;
        }
        return null;
    }

    public Product binarySearchById(Product[] products, int targetId) {
        int low = 0, high = products.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (products[mid].getProductId() == targetId) return products[mid];
            if (products[mid].getProductId() < targetId) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    public void buildIndex(Product[] products) {
        for (Product p : products) nameIndex.put(p.getProductName(), p);
    }
}

// --- MAIN EXECUTION (JDoodle requires the class to be 'MyClass' or 'Main') ---
public class Main {
    public static void main(String[] args) {
        int n = 100000;
        Product[] products = new Product[n];
        TechMartSearch engine = new TechMartSearch();

        for (int i = 0; i < n; i++) {
            products[i] = new Product(i * 2, "Product" + i, 100.0);
        }
        engine.buildIndex(products);

        System.out.println("================================================================");
        System.out.println("TECHMART SEARCH PERFORMANCE ANALYSIS (n=" + n + " products)");
        System.out.println("STUDENT: EMEREJE ROLAND | MATNO: U2022/5570255");
        System.out.println("================================================================");

        int targetId = products[n - 1].getProductId(); 

        long start = System.nanoTime();
        engine.sequentialSearchById(products, targetId);
        long end = System.nanoTime();
        System.out.printf("SEQUENTIAL SEARCH: %.3f ms\n", (end - start) / 1e6);

        start = System.nanoTime();
        engine.binarySearchById(products, targetId);
        end = System.nanoTime();
        System.out.printf("BINARY SEARCH: %.3f ms\n", (end - start) / 1e6);
        System.out.println("================================================================");
    }
                                      }
