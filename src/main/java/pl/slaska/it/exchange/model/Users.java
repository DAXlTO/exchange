package pl.slaska.it.exchange.model;

public class Users {

    String id;
    int balance;
    long credit_card;
    int age;
    String email;
    String name;
    String password;
    int phone;



    public Users(String id, int balance, long credit_card, int age, String email, String name, String password, int phone) {
        this.id = id;
        this.balance = balance;
        this.credit_card = credit_card;
        this.age = age;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public Users() {

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
        return name;
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

    public void setName(String nombre) {
        this.name = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
