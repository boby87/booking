package cm.ftg.bookingImage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @Column(unique = true, nullable = false)
    private String referencePattern;
    private String fileName;
    private String contentType;
    private long size;

}
