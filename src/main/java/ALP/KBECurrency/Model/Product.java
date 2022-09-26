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
    private String preis;
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
            String preis,
            Component... components) {
        for (Component comp : components) {
            this.components.add(comp);
        }
        this.name = name;
        this.preis = preis;
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

    public String getPreis() {
        return this.preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
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