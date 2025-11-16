import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * PizzaSchemaDesign class provides a conceptual database schema design
 * using Java classes. Each static nested class represents a database table,
 * and its public fields represent the columns. Relationships are indicated
 * by foreign key fields and conceptual collections (e.g., Set, List) for
 * many-to-many or one-to-many relationships, clarified with comments.
 */
public class PizzaSchemaDesign {

    /**
     * Represents the 'users' table.
     * Stores user authentication and contact information.
     */
    public static class User {
        public Long id; // Primary Key
        public String username; // Unique username
        public String passwordHash; // Hashed password for security
        public String email; // User's email address
        public String address; // User's default delivery address
        public String phoneNumber;
        // public LocalDateTime createdAt;
        // public LocalDateTime lastLogin;
    }

    /**
     * Represents the 'pizzas' table.
     * Defines the base pizza types available (e.g., Margherita, Pepperoni).
     */
    public static class Pizza {
        public Long id; // Primary Key
        public String name; // Name of the pizza type
        public String description;
        public BigDecimal basePrice; // Base price for a standard size/configuration
        // Conceptual many-to-many relationship: Default toppings included with this pizza type.
        // In a relational database, this would typically be resolved via a join table (e.g., Pizza_DefaultTopping).
        public Set<Topping> defaultToppings;
    }

    /**
     * Represents the 'toppings' table.
     * Defines individual toppings that can be added to pizzas.
     */
    public static class Topping {
        public Long id; // Primary Key
        public String name; // Name of the topping
        public BigDecimal price; // Additional cost for this topping
        // public boolean isVegetarian;
    }

    /**
     * Represents the 'orders' table.
     * Stores details about a customer's order.
     */
    public static class Order {
        public Long id; // Primary Key
        public Long userId; // Foreign Key referencing User.id
        public LocalDateTime orderDate;
        public BigDecimal totalAmount; // Total calculated amount for the order
        public OrderStatus status; // Current status of the order (e.g., PENDING, DELIVERED)
        public String deliveryAddress; // Specific delivery address for this order (can override user's default)
        // Conceptual one-to-many relationship: List of items included in this order.
        // In a relational database, these would be retrieved from the OrderItem table using orderId.
        public List<OrderItem> items;
        // public String paymentMethod;
    }

    /**
     * Enum defining possible statuses for an order.
     */
    public enum OrderStatus {
        PENDING,
        ACCEPTED,
        PREPARING,
        OUT_FOR_DELIVERY,
        DELIVERED,
        CANCELLED
    }

    /**
     * Represents the 'order_items' table.
     * Details a specific pizza (with its customizations) within an order.
     * This acts as a junction between an Order and a Pizza, allowing for custom configurations per ordered pizza.
     */
    public static class OrderItem {
        public Long id; // Primary Key
        public Long orderId; // Foreign Key referencing Order.id
        public Long pizzaId; // Foreign Key referencing Pizza.id (the base pizza type)
        public int quantity; // Number of this specific pizza item
        public BigDecimal itemPrice; // Calculated price for this specific item (base price + custom toppings)
        public PizzaSize size; // Size of the pizza (e.g., SMALL, MEDIUM, LARGE)
        // Conceptual many-to-many relationship: Toppings specifically added or removed for THIS pizza in THIS order.
        // In a relational database, this would typically be resolved via a join table (e.g., OrderItem_CustomTopping).
        public Set<Topping> customToppings;
        // public String specialInstructions; // E.g., "no onions"
    }

    /**
     * Enum defining possible sizes for a pizza.
     */
    public enum PizzaSize {
        SMALL,
        MEDIUM,
        LARGE,
        FAMILY
    }
}
