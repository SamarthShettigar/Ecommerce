import java.util.*;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    private List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public List<Product> getItems() {
        return items;
    }
}

class Customer {
    private String name;
    private String email;
    private ShoppingCart cart;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        cart = new ShoppingCart();
    }

    public void addToCart(Product product) {
        cart.addItem(product);
    }

    public double checkout() {
        double total = cart.calculateTotal();
        
        return total;
    }

    public Order generateOrder() {
        return new Order(this, cart);
    }

    public String getEmail() {
        return email;
    }
}

class Order {
    private Customer customer;
    private ShoppingCart cart;
    private double totalAmount;

    public Order(Customer customer, ShoppingCart cart) {
        this.customer = customer;
        this.cart = cart;
        this.totalAmount = cart.calculateTotal();
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

public class Ecommerce {
    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("Enter customer's name:");
        String name = scanner.nextLine();

        System.out.println("Enter Customer's email:");
        String email = scanner.nextLine();

        
        Customer customer = new Customer(name, email);

       
        List<Product> products = new ArrayList<>();
        while (true) {
            System.out.println("Enter product name (type 'done' to finish):");
            String productName = scanner.nextLine();
            if (productName.equals("done")) {
                break;
            }
            System.out.println("Enter product price:");
            double productPrice = scanner.nextDouble();
            scanner.nextLine(); 
            Product product = new Product(productName, productPrice);
            customer.addToCart(product);
            products.add(product);
        }

        
        System.out.println("Products selected:");
        for (Product product : products) {
            System.out.println("Name: " + product.getName() + ", Price: RS: " + product.getPrice());
        }

       
        double total = customer.checkout();
        System.out.println("Total amount: RS: " + total);

      
        Order order = customer.generateOrder();
        System.out.println("Order Total: RS: " + order.getTotalAmount());

        
        scanner.close();
    }
}
