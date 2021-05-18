package kg.itacademy.academy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creatorId")
    private User creatorId;

    @Column(name = "full_name")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course courseId;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "fee")
    private BigDecimal fee;
}
