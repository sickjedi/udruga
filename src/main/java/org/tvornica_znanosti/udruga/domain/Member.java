package org.tvornica_znanosti.udruga.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

import org.tvornica_znanosti.udruga.domain.enumeration.ReasonInactive;

/**
 * A Member.
 */
@Entity
@Table(name = "member")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Column(name = "oib", nullable = false)
    private String oib;

    @NotNull
    @Column(name = "dateofbirth", nullable = false)
    private Instant dateofbirth;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "cellphone", nullable = false)
    private String cellphone;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "datecreated", nullable = false)
    private LocalDate datecreated;

    @NotNull
    @Column(name = "memberfrom", nullable = false)
    private LocalDate memberfrom;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "dateinactive")
    private LocalDate dateinactive;

    @Enumerated(EnumType.STRING)
    @Column(name = "reasoninactive")
    private ReasonInactive reasoninactive;

    @NotNull
    @Column(name = "lastupdated", nullable = false)
    private LocalDate lastupdated;

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

    public Member name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public Member surname(String surname) {
        this.surname = surname;
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOib() {
        return oib;
    }

    public Member oib(String oib) {
        this.oib = oib;
        return this;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public Instant getDateofbirth() {
        return dateofbirth;
    }

    public Member dateofbirth(Instant dateofbirth) {
        this.dateofbirth = dateofbirth;
        return this;
    }

    public void setDateofbirth(Instant dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getAddress() {
        return address;
    }

    public Member address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public Member cellphone(String cellphone) {
        this.cellphone = cellphone;
        return this;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public Member email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDatecreated() {
        return datecreated;
    }

    public Member datecreated(LocalDate datecreated) {
        this.datecreated = datecreated;
        return this;
    }

    public void setDatecreated(LocalDate datecreated) {
        this.datecreated = datecreated;
    }

    public LocalDate getMemberfrom() {
        return memberfrom;
    }

    public Member memberfrom(LocalDate memberfrom) {
        this.memberfrom = memberfrom;
        return this;
    }

    public void setMemberfrom(LocalDate memberfrom) {
        this.memberfrom = memberfrom;
    }

    public Boolean isActive() {
        return active;
    }

    public Member active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getDateinactive() {
        return dateinactive;
    }

    public Member dateinactive(LocalDate dateinactive) {
        this.dateinactive = dateinactive;
        return this;
    }

    public void setDateinactive(LocalDate dateinactive) {
        this.dateinactive = dateinactive;
    }

    public ReasonInactive getReasoninactive() {
        return reasoninactive;
    }

    public Member reasoninactive(ReasonInactive reasoninactive) {
        this.reasoninactive = reasoninactive;
        return this;
    }

    public void setReasoninactive(ReasonInactive reasoninactive) {
        this.reasoninactive = reasoninactive;
    }

    public LocalDate getLastupdated() {
        return lastupdated;
    }

    public Member lastupdated(LocalDate lastupdated) {
        this.lastupdated = lastupdated;
        return this;
    }

    public void setLastupdated(LocalDate lastupdated) {
        this.lastupdated = lastupdated;
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
        Member member = (Member) o;
        if (member.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), member.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Member{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", oib='" + getOib() + "'" +
            ", dateofbirth='" + getDateofbirth() + "'" +
            ", address='" + getAddress() + "'" +
            ", cellphone='" + getCellphone() + "'" +
            ", email='" + getEmail() + "'" +
            ", datecreated='" + getDatecreated() + "'" +
            ", memberfrom='" + getMemberfrom() + "'" +
            ", active='" + isActive() + "'" +
            ", dateinactive='" + getDateinactive() + "'" +
            ", reasoninactive='" + getReasoninactive() + "'" +
            ", lastupdated='" + getLastupdated() + "'" +
            "}";
    }
}
