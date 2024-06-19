import java.util.ArrayList;
import java.util.List;

public class Sale {
    private List<Product> products;
    private double total;

    public Sale() {
        products = new ArrayList<>();
        total = 0.0;
    }

    public void addProduct(Product product) {
        products.add(product);
        total += product.getPrice();
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sale{products=[");
        for (Product p : products) {
            sb.append(p.toString()).append(", ");
        }
        sb.append("], total=").append(total).append("}");
        return sb.toString();
    }
}
