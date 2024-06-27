package bg.softuni.MVCDemo.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Company () {}

    public Company(String name) {
        this.name = name;
    }
}
