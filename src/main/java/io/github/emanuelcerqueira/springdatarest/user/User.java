package io.github.emanuelcerqueira.springdatarest.user;

import io.github.emanuelcerqueira.springdatarest.shared.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
public class User extends BaseEntity {

    @NotEmpty(message = "Must not be empty.")
    @Length(min=5, max=120, message="The name length must be between 5 and 120 characters.")
    private String name;
    @NotEmpty(message = "Must not be empty.")
    @Email(message = "Invalid e-mail.")
    @Column(unique = true)
    private String email;
    @Past(message = "Birth date must be a past date.")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    public User() {
    }

    public User(@NotEmpty(message = "Must not be empty.")
                @Length(min = 5, max = 120, message = "The name length must be between 5 and 120 characters.")
                        String name, @NotEmpty(message = "Must not be empty.")
                @Email(message = "Invalid e-mail.") String email, @Past LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
