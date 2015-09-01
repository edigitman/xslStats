package ro.agitman.ing.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by edi on 24.08.15.
 */
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date opDate;
    private String noCard;
    private String terminal;
    private String auth;
    @Column(scale = 3, precision = 13)
    private BigDecimal amount;
    private String currency;
    @Column(scale = 3, precision = 13)
    private BigDecimal amountRon;
    private Boolean debit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public String getNoCard() {
        return noCard;
    }

    public void setNoCard(String noCard) {
        this.noCard = noCard;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmountRon() {
        return amountRon;
    }

    public void setAmountRon(BigDecimal amountRon) {
        this.amountRon = amountRon;
    }

    public Boolean getDebit() {
        return debit;
    }

    public void setDebit(Boolean debit) {
        this.debit = debit;
    }
}
