package co.cristian.customer.service.businessLayer;

import java.util.Objects;

public class ProductDto {
    private String name;
    private String type;
    private String price;

    public ProductDto() {
    }

    public ProductDto(String name, String type, String price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return name.equals(that.name) && type.equals(that.type) && price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, price);
    }
}
