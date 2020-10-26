package shoppingSpree;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.contains(" ")) {
            throw new InvalidParameterException("Name cannot be empty.");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new InvalidParameterException("Money cannot be negative.");
        }
        this.money = money;
    }

    public void buyProduct(Product product) {
        if (this.money < product.getCost()) {
            throw new InvalidParameterException("Money cannot be negative.");
        }

        this.products.add(product);
        this.setMoney(this.money - product.getCost());
        System.out.println(this.name + " bought " + product.getName());

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append(" - ");
        if (this.products.isEmpty()) {
            sb.append("Nothing bought");
        } else {
            for (int i = 0; i < products.size(); i++) {
                sb.append(products.get(i).toString());
                if (i != products.size() - 1) {
                    sb.append(", ");
                }
            }
        }

        return sb.toString();
    }
}
