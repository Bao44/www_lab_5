package vn.edu.iuh.fit.lab_5.backend.exceptions;

public class EntityIdNotFoundException extends Exception {

    public EntityIdNotFoundException(String id) {
        super("The entity id = " + id + " was not found!");
    }
}
