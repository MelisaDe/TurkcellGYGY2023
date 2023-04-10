package org.example;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager(new EDevletServiceAdapter());
        userManager.register(new User(1, "12345678900", "Engin", "Demiroğ", "1985"));
        userManager.register(new User(2, "12345678901", "Melisa", "Demirhan", "2000"));
        userManager.register(new User(3, "12345678902", "ElifNur", "Benhür", "1994"));
        userManager.register(new User(4, "12345678903", "Özcan", "Elmacı", "2000"));
        userManager.register(new User(5, "12345678904", "Mert", "Kayatay", "1999"));
    }
}