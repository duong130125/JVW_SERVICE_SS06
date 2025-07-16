package ra.session_06.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stuId;

    @Column(nullable = false, length = 50)
    private String fullName;
    @Column(nullable = false, length = 10)
    private String gender;
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false, length = 100)
    private String address;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classEntity;
}