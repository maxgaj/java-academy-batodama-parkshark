package be.cm.batodama.parkshark.domain.base_user;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BASE_USER")
public class BaseUser {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BU_SEQUENCE")
    @SequenceGenerator(sequenceName = "BASE_USER_SEQUENCE", name = "BU_SEQUENCE", allocationSize = 1)
    private long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public BaseUser() {
    }

    public BaseUser(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
