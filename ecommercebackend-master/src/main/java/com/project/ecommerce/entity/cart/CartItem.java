package com.project.ecommerce.entity.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.entity.User;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @EmbeddedId
    @JsonIgnore
    private CartItemUP pk;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date addedOn = new Date();

    @Column(nullable = false)
    private int quantity = 1;

    public CartItem () {

    }

    public CartItem (User user, Product product, int quantity) {
        pk = new CartItemUP();
        pk.setUser(user);
        pk.setProduct(product);
        this.quantity = quantity;
    }

    @Transient
    public Product getProduct () {
        return pk.getProduct();
    }

    @Transient
    public double getTotalPrice () {
        return quantity * getProduct().getPrice();
    }

    public CartItemUP getPk() {
        return pk;
    }

    public void setPk(CartItemUP pk) {
        this.pk = pk;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CartItem that = (CartItem) o;
        return Objects.equals(pk.getUser().getId(), that.pk.getUser().getId()) &&
                Objects.equals(getProduct().getId(), that.getProduct().getId());
    }
}
