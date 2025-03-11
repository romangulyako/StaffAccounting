package by.itacademy.jd2.staffaccountingspringboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity extends CommonSuperEntity implements GrantedAuthority {
    @Column(name = "name")
    private String authority;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    private Set<UserEntity> users;
}
