package be.cm.batodama.parkshark.domain.manager;

import be.cm.batodama.parkshark.domain.base_user.BaseUser;
import be.cm.batodama.parkshark.domain.base_user.Role;

import javax.persistence.Column;

public class Manager extends BaseUser {

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;

    public Manager() {
    }

    public Manager(String username, String password, Role role, String firstName, String lastName) {
        super(username, password, Role.ROLE_MANAGER);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
