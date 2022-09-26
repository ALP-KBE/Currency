package ALP.KBECurrency.Model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Representation of a product of a guitar
 */
public class Product implements Serializable {

    private String name;
    private String additionalInfo;
    private String price;
    private List<Component> components = new LinkedList<>();

    public Product(String name,
            String additionalInfo,
            Component... components) {
        for (Component comp : components) {
            this.components.add(comp);
        }
        this.name = name;
        this.additionalInfo = additionalInfo;
    }

    public Product(String name,
            String additionalInfo,
            String price,
            Component... components) {
        for (Component comp : components) {
            this.components.add(comp);
        }
        this.name = name;
        this.price = price;
        this.additionalInfo = additionalInfo;
    }

    public Product() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Component> getComponents() {
        return this.components;
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String s = "";
        for (Component component : components) {
            s = s + "<-| ";
            s = s + component.toString();
            s = s + " |->";
        }
        return s;
    }
}