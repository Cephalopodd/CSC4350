/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author jgreene
 */
public class Item extends Button {

    private StringProperty name;
    private StringProperty description;
    private DoubleProperty price;
    private StringProperty code;

    public Item(String name, String description, String code, double price) {
        super(name);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.code = new SimpleStringProperty(code);
        this.price = new SimpleDoubleProperty(price);
        this.initStyle();
    }

    public String getName() {
        return name.getValueSafe();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getDescription() {
        return description.getValueSafe();
    }

    public void setDescription(String description) {
        this.description.setValue(description);
    }

    public String getCode() {
        return code.getValueSafe();
    }

    public void setCode(String code) {
        this.code.setValue(code);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty codeProperty() {
        return code;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    @Override
    public String toString() {
        return getName() + ":" + getCode() + getPrice() + getDescription();
    }

    private void initStyle() {
        switch (code.getValueSafe()) {
            case BillingCode.FOOD:
                setStyle("-fx-background-color: #41208a; "
                        + "-fx-font-size: 16px; -fx-text-fill: WHITE;");
                break;
            case BillingCode.GIFTSHOP:
                setStyle("-fx-background-color: #49708a; "
                        + "-fx-font-size: 16px; -fx-text-fill: WHITE;");
                break;
            case BillingCode.MOVIE:
                setStyle("-fx-background-color: #84aa05; "
                        + "-fx-font-size: 16px; -fx-text-fill: WHITE;");
                break;
            case BillingCode.OTHER:
                setStyle("-fx-background-color: #524656; "
                        + "-fx-font-size: 16px; -fx-text-fill: WHITE;");
                break;
            case BillingCode.PARKING:
                setStyle("-fx-background-color: #ff4e23; "
                        + "-fx-font-size: 16px; -fx-text-fill: WHITE;");
                break;
            case BillingCode.RESORTFEE:
                this.setStyle("-fx-background-color: #ff4e50; "
                        + "-fx-font-size: 16px; -fx-text-fill: WHITE;");
                break;
            case BillingCode.ROOM:
                this.setStyle("-fx-background-color: #ff7850;"
                        + " -fx-font-size: 16px; -fx-text-fill: WHITE;");
                break;
            case BillingCode.SPA:
                this.setStyle("-fx-background-color: #ff4e50; "
                        + "-fx-font-size: 16px; -fx-text-fill: WHITE;");
                break;
            default:
                setStyle("-fx-background-color: #ff1250; "
                        + "-fx-font-size: 16px; -fx-text-fill: WHITE;");
                break;

        }

    }

}
