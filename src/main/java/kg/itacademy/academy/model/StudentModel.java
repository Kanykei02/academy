package kg.itacademy.academy.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {
    private String fullName;

    private Long courseId;

    private LocalDateTime registrationDate;

    private BigDecimal fee;
}
