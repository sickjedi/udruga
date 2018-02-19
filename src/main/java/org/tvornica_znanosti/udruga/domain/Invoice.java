package org.tvornica_znanosti.udruga.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.tvornica_znanosti.udruga.domain.enumeration.NameOfService;

/**
 * A Invoice.
 */
@Entity
@Table(name = "invoice")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "invoicenum", nullable = false)
    private String invoicenum;

    @NotNull
    @Column(name = "datecreated", nullable = false)
    private LocalDate datecreated;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nameofservice", nullable = false)
    private NameOfService nameofservice;

    @NotNull
    @Column(name = "unit", nullable = false)
    private Integer unit;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ManyToOne
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoicenum() {
        return invoicenum;
    }

    public Invoice invoicenum(String invoicenum) {
        this.invoicenum = invoicenum;
        return this;
    }

    public void setInvoicenum(String invoicenum) {
        this.invoicenum = invoicenum;
    }

    public LocalDate getDatecreated() {
        return datecreated;
    }

    public Invoice datecreated(LocalDate datecreated) {
        this.datecreated = datecreated;
        return this;
    }

    public void setDatecreated(LocalDate datecreated) {
        this.datecreated = datecreated;
    }

    public NameOfService getNameofservice() {
        return nameofservice;
    }

    public Invoice nameofservice(NameOfService nameofservice) {
        this.nameofservice = nameofservice;
        return this;
    }

    public void setNameofservice(NameOfService nameofservice) {
        this.nameofservice = nameofservice;
    }

    public Integer getUnit() {
        return unit;
    }

    public Invoice unit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Invoice quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public Invoice price(Integer price) {
        this.price = price;
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public Invoice amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Invoice customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Invoice invoice = (Invoice) o;
        if (invoice.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invoice.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Invoice{" +
            "id=" + getId() +
            ", invoicenum='" + getInvoicenum() + "'" +
            ", datecreated='" + getDatecreated() + "'" +
            ", nameofservice='" + getNameofservice() + "'" +
            ", unit=" + getUnit() +
            ", quantity=" + getQuantity() +
            ", price=" + getPrice() +
            ", amount=" + getAmount() +
            "}";
    }
}
