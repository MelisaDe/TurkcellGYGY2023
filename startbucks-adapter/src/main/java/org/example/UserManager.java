package org.example;

public class UserManager {
    ControlService controlService;

    public UserManager(ControlService controlService) {
        this.controlService = controlService;
    }

    public void register(User user) {
        if (controlService.controlUser(user)) {
            System.out.println("User registered");
        } else {
            System.out.println("User not registered");
        }
    }
}
