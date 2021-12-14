package pl.slaska.it.exchange.model;

public class UserDetails {
    String id;
    int balance;
    long credit_card;
    int age;
    String email;
    String nombre;
    String password;
    int phone;



    public UserDetails(String id, int balance, long credit_card, int age, String email, String nombre,  String password, int phone) {
        this.id = id;
        this.balance = balance;
        this.credit_card = credit_card;
        this.age = age;
        this.email = email;
        this.nombre = nombre;
        this.password = password;
        this.phone = phone;
    }

    public UserDetails() {

    }

    public String getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public long getCredit_card() {
        return credit_card;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public int getPhone() {
        return phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setCredit_card(long credit_card) {
        this.credit_card = credit_card;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}