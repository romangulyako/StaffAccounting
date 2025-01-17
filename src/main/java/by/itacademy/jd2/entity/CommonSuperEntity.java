package by.itacademy.jd2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder(toBuilder = true)
public class CommonSuperEntity {
    @CreationTimestamp
    @Column(updatable = false, name = "create_date")
    @Builder.ObtainVia(field = "createDate")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(insertable = false, name = "update_date")
    @Builder.ObtainVia(field = "updateDate")
    private LocalDateTime updateDate;
}
