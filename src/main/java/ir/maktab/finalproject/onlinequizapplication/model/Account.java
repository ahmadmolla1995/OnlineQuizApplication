package ir.maktab.finalproject.onlinequizapplication.model;

import ir.maktab.finalproject.onlinequizapplication.enumeration.AccountStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private Date createdDate;
    private Date lastLoginDate;
    private Boolean enable;

    @OneToOne(cascade = CascadeType.ALL)
    private Person person = new Person();

    @ManyToMany
    @Column(nullable = false)
    @JoinTable(name = "account_roles", joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();


    public Account() {}

    public Account(Long id, String username, String password, Role role, AccountStatus accountStatus, Date createdDate, Date lastLoginDate, Boolean enable) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountStatus = accountStatus;
        this.createdDate = createdDate;
        this.lastLoginDate = lastLoginDate;
        this.enable = enable;
        this.roles.add(role);
    }


    public Long getId() { return id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) { this.person = person; }

    public Set<Role> getRoles() { return roles; }

    public void addRole(Role role) { this.roles.add(role); }

    public void removeRole(Role role) { this.roles.remove(role); }
}
