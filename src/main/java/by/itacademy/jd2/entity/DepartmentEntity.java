package by.itacademy.jd2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Data
@EqualsAndHashCode(exclude = "positions", callSuper = true)
@ToString(exclude = "positions")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class DepartmentEntity extends CommonSuperEntity {
    @Column
    private String name;

    @Column(name = "genitive_case_name")
    private String genitiveCaseName;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "department")
    @Builder.Default
    private Set<PositionEntity> positions = new HashSet<>();

    @Column(name = "is_actual")
    @Builder.Default
    private Boolean isActual = true;
}
