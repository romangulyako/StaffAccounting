package by.itacademy.jd2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Table;
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
public class PositionEntity extends CommonSuperEntityWithId {
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
}
