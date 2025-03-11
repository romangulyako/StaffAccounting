package by.itacademy.jd2.staffaccountingspringboot.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Data
@EqualsAndHashCode(exclude = "history", callSuper = false)
@ToString(exclude = "history")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "positions")
public class PositionEntity extends BaseEntity {
    @Column
    private String name;

    @Column(name = "necessary_education_level")
    private String educationLevel;

    @Column
    private double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "position")
    @Builder.Default
    private Set<CareerStepEntity> history = new HashSet<>();

    @Column(name = "is_actual")
    @Builder.Default
    private Boolean isActual = true;
}
