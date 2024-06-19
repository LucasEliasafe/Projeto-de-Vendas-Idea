import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesManager {
    private Map<String, Product> productCatalog;
    private List<Sale> sales;

    public SalesManager() {
        productCatalog = new HashMap<>();
        sales = new ArrayList<>();
    }

    public void addProductToCatalog(Product product) {
        productCatalog.put(product.getId(), product);
    }

    public Product getProductFromCatalog(String productId) {
        return productCatalog.get(productId);
    }

    public void createSale(Sale sale) {
        sales.add(sale);
    }

    public void showSales() {
        for (Sale sale : sales) {
            System.out.println(sale);
        }
    }

    public static void main(String[] args) {
        SalesManager manager = new SalesManager();

        // Adding products to the catalog
        manager.addProductToCatalog(new Product("001", "Laptop", 1200.00));
        manager.addProductToCatalog(new Product("002", "Smartphone", 800.00));
        manager.addProductToCatalog(new Product("003", "Tablet", 300.00));

        // Creating a sale
        Sale sale1 = new Sale();
        sale1.addProduct(manager.getProductFromCatalog("001"));
        sale1.addProduct(manager.getProductFromCatalog("002"));
        manager.createSale(sale1);

        // Creating another sale
        Sale sale2 = new Sale();
        sale2.addProduct(manager.getProductFromCatalog("003"));
        manager.createSale(sale2);

        // Showing all sales
        manager.showSales();
    }
}