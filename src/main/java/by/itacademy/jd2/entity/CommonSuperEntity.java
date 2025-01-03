package by.itacademy.jd2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class CommonSuperEntity {
    @CreationTimestamp
    @Column(updatable = false, name = "create_date")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(insertable = false, name = "update_date")
    private LocalDateTime updateDate;
}
