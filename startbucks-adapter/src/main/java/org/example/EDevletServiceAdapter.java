package org.example;

public class EDevletServiceAdapter implements ControlService {
    @Override
    public boolean controlUser(User user) {
        EDevletService eDevletService = new EDevletService();
        return eDevletService.checkUser(user.getNationalityId(), user.getFirstName(), user.getLastName(), user.getDateOfBirth());
    }
}

