# DELI-cious Sandwiches 🥪

A custom sandwich shop Point of Sales (POS) application built with Java, demonstrating Object-Oriented Programming principles.

## Project Description

DELI-cious is a command-line application that allows customers to create fully customized sandwich orders. The application automates the order process, calculates pricing based on size and toppings, and generates timestamped receipts for each order.

## Features

- **Custom Sandwich Builder**: Choose from multiple bread types, sizes, and toppings
- **Premium Toppings**: Meats and cheeses with extra options
- **Regular Toppings**: Free vegetables and sauces
- **Drinks & Chips**: Add beverages and sides to your order
- **Order Management**: Add multiple items to a single order
- **Receipt Generation**: Automatic receipt creation with timestamp
- **Input Validation**: Ensures orders meet business requirements

## Application Screens

### Home Screen
The main entry point where users can start a new order or exit the application.

```
╔════════════════════════════════════════╗
║            HOME SCREEN                 ║
╚════════════════════════════════════════╝
  1) New Order
  0) Exit
```

### Order Screen
Manage items in your current order.

```
╔════════════════════════════════════════╗
║            ORDER SCREEN                ║
╚════════════════════════════════════════╝
  1) Add Sandwich
  2) Add Drink
  3) Add Chips
  4) Checkout
  0) Cancel Order
```

### Add Sandwich Screen
Step-by-step sandwich customization:
1. Select bread type (White, Wheat, Rye, Wrap)
2. Choose size (4", 8", 12")
3. Add meats with extra option
4. Add cheese with extra option
5. Add regular toppings (vegetables)
6. Add sauces
7. Choose toasted or not

### Checkout Screen
Review order details and confirm purchase. Receipt is saved to the `receipts/` folder.

## Pricing

### Sandwiches
| Size | Base Price |
|------|------------|
| 4"   | $5.50      |
| 8"   | $7.00      |
| 12"  | $8.50      |

### Premium Toppings
| Topping      | 4"    | 8"    | 12"   |
|--------------|-------|-------|-------|
| Meat         | $1.00 | $2.00 | $3.00 |
| Extra Meat   | $0.50 | $1.00 | $1.50 |
| Cheese       | $0.75 | $1.50 | $2.25 |
| Extra Cheese | $0.30 | $0.60 | $0.90 |

### Other Products
| Product | Size   | Price |
|---------|--------|-------|
| Drink   | Small  | $2.00 |
| Drink   | Medium | $2.50 |
| Drink   | Large  | $3.00 |
| Chips   | -      | $1.50 |

## Project Structure

```
DELI-cious/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── pluralsight/
│                   └── deli/
│                       ├── DeliApplication.java
│                       ├── enums/
│                       │   ├── BreadType.java
│                       │   ├── CheeseType.java
│                       │   ├── DrinkSize.java
│                       │   ├── MeatType.java
│                       │   ├── RegularTopping.java
│                       │   ├── SandwichSize.java
│                       │   └── Sauce.java
│                       ├── models/
│                       │   ├── Product.java (abstract)
│                       │   ├── Sandwich.java
│                       │   ├── Drink.java
│                       │   ├── Chips.java
│                       │   ├── Order.java
│                       │   ├── Topping.java (abstract)
│                       │   ├── MeatTopping.java
│                       │   ├── CheeseTopping.java
│                       │   ├── RegularToppingItem.java
│                       │   └── SauceTopping.java
│                       ├── services/
│                       │   └── ReceiptFileManager.java
│                       └── ui/
│                           └── UserInterface.java
└── receipts/
    └── (generated receipt files)
```

## Class Diagram

The application follows Object-Oriented Design principles:

**Key Classes:**
- **Product (Abstract)**: Base class for all orderable items
  - Sandwich, Drink, Chips (concrete implementations)
- **Topping (Abstract)**: Base class for sandwich toppings
  - MeatTopping, CheeseTopping, RegularToppingItem, SauceTopping
- **Order**: Manages collection of products
- **ReceiptFileManager**: Handles file I/O for receipts
- **UserInterface**: Controls all user interactions

**Design Patterns Used:**
- **Inheritance**: Product and Topping hierarchies
- **Polymorphism**: Different products calculate prices differently
- **Composition**: Sandwich contains multiple Toppings
- **Encapsulation**: Private fields with public getters/setters

## OOP Concepts Demonstrated

1. **Abstraction**: Abstract base classes (Product, Topping)
2. **Inheritance**: Concrete classes extending abstract bases
3. **Polymorphism**: Method overriding (getPrice(), getDescription())
4. **Encapsulation**: Private fields with controlled access
5. **Composition**: Order contains Products, Sandwich contains Toppings
6. **Enums**: Type-safe constants for menu options

## Interesting Code Highlight

### Dynamic Price Calculation with Enums

One interesting aspect of this project is how the `SandwichSize` enum encapsulates all size-related pricing logic:

```java
public enum SandwichSize {
    SMALL(4, 5.50, 1.00, 0.75, 0.50, 0.30),
    MEDIUM(8, 7.00, 2.00, 1.50, 1.00, 0.60),
    LARGE(12, 8.50, 3.00, 2.25, 1.50, 0.90);

    private final int inches;
    private final double basePrice;
    private final double meatPrice;
    // ... other prices
}
```

This design allows toppings to calculate their own price based on sandwich size without complex conditional logic:

```java
public class MeatTopping extends Topping {
    @Override
    public double getPrice() {
        if (extra) {
            return sandwichSize.getExtraMeatPrice();
        }
        return sandwichSize.getMeatPrice();
    }
}
```

This demonstrates the **Strategy Pattern** where the pricing strategy is encapsulated in the enum, making the code highly maintainable and following the **Open/Closed Principle** (open for extension, closed for modification).

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Compilation
```bash
cd DELI-cious/src/main/java
javac com/pluralsight/deli/DeliApplication.java com/pluralsight/deli/**/*.java
```

### Execution
```bash
java com.pluralsight.deli.DeliApplication
```

## Business Rules

1. Orders can contain 0 or more sandwiches
2. If an order has no sandwiches, it must contain at least chips or a drink
3. Premium toppings (meat and cheese) have size-based pricing
4. Extra portions of premium toppings cost additional amounts
5. Regular toppings and sauces are included at no extra charge
6. Each order generates a unique receipt file named with timestamp format: `yyyyMMdd-HHmmss.txt`

## Sample Receipt

```
╔════════════════════════════════════════════════╗
║          DELI-cious Sandwiches                 ║
║          Official Receipt                      ║
╚════════════════════════════════════════════════╝

Date: 11/11/2025 02:15:23 PM

Order Summary:
==================================================
1. 8" Wheat Sandwich (Toasted)
  Base Price: $7.00
  Meats: Steak (+$2.00), Extra Bacon (+$1.00)
  Cheese: Cheddar (+$1.50)
  Toppings: Lettuce, Tomatoes, Onions
  Sauces: Mayo, Ranch
  Total: $11.50

2. Medium Coke - $2.50

3. Lays Chips - $1.50

==================================================
Total: $15.50

═════════════════════════════════════════════════
Thank you for your order!
We hope you enjoy your meal!
```
