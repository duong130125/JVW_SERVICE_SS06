package ra.session_06.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Thông tin nhân viên")
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID định danh", example = "1")
    private Long id;

    @Column(nullable = false, length = 50)
    @Schema(description = "Tên nhân viên", example = "Nguyễn Văn A")
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    @Schema(description = "Email nhân viên", example = "a.nguyen@example.com")
    private String email;

    @Column(nullable = false, length = 50)
    @Schema(description = "Chức vụ", example = "Developer")
    private String position;

    @Column(nullable = false)
    @Schema(description = "Lương", example = "1200.50")
    private Double salary;
}