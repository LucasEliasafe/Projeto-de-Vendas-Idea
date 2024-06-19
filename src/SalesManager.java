import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
        saveSaleToFile(sale);
    }

    public void showSales() {
        for (Sale sale : sales) {
            System.out.println(sale);
        }
    }

    private void saveSaleToFile(Sale sale) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sales.txt", true))) {
            writer.write(sale.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar venda no arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SalesManager manager = new SalesManager();
        Scanner scanner = new Scanner(System.in);

        // Adding products to the catalog
        manager.addProductToCatalog(new Product("001", "Laptop", 1200.00));
        manager.addProductToCatalog(new Product("002", "Smartphone", 800.00));
        manager.addProductToCatalog(new Product("003", "Tablet", 300.00));

        boolean running = true;

        while (running) {
            System.out.println("1. Criar venda");
            System.out.println("2. Mostrar vendas");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consumir nova linha

            switch (option) {
                case 1:
                    Sale sale = new Sale();
                    boolean addingProducts = true;

                    while (addingProducts) {
                        System.out.print("Digite o ID do produto (ou 'sair' para finalizar): ");
                        String productId = scanner.nextLine();

                        if (productId.equalsIgnoreCase("sair")) {
                            addingProducts = false;
                        } else {
                            Product product = manager.getProductFromCatalog(productId);
                            if (product != null) {
                                sale.addProduct(product);
                                System.out.println("Produto adicionado: " + product.getName());
                            } else {
                                System.out.println("Produto não encontrado.");
                            }
                        }
                    }

                    manager.createSale(sale);
                    System.out.println("Venda criada: " + sale);
                    break;
                case 2:
                    manager.showSales();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}