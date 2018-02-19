package org.tvornica_znanosti.udruga.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "oib", nullable = false)
    private String oib;

    @Column(name = "cellphone")
    private String cellphone;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "datecreated", nullable = false)
    private LocalDate datecreated;

    @NotNull
    @Column(name = "lastupdated", nullable = false)
    private LocalDate lastupdated;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Invoice> ids = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOib() {
        return oib;
    }

    public Customer oib(String oib) {
        this.oib = oib;
        return this;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getCellphone() {
        return cellphone;
    }

    public Customer cellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDatecreated() {
        return datecreated;
    }

    public Customer datecreated(LocalDate datecreated) {
        this.datecreated = datecreated;
        return this;
    }

    public void setDatecreated(LocalDate datecreated) {
        this.datecreated = datecreated;
    }

    public LocalDate getLastupdated() {
        return lastupdated;
    }

    public Customer lastupdated(LocalDate lastupdated) {
        this.lastupdated = lastupdated;
        return this;
    }

    public void setLastupdated(LocalDate lastupdated) {
        this.lastupdated = lastupdated;
    }

    public Set<Invoice> getIds() {
        return ids;
    }

    public Customer ids(Set<Invoice> invoices) {
        this.ids = invoices;
        return this;
    }

    public Customer addId(Invoice invoice) {
        this.ids.add(invoice);
        invoice.setCustomer(this);
        return this;
    }

    public Customer removeId(Invoice invoice) {
        this.ids.remove(invoice);
        invoice.setCustomer(null);
        return this;
    }

    public void setIds(Set<Invoice> invoices) {
        this.ids = invoices;
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
        Customer customer = (Customer) o;
        if (customer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", oib='" + getOib() + "'" +
            ", cellphone='" + getCellphone() + "'" +
            ", email='" + getEmail() + "'" +
            ", datecreated='" + getDatecreated() + "'" +
            ", lastupdated='" + getLastupdated() + "'" +
            "}";
    }
}
