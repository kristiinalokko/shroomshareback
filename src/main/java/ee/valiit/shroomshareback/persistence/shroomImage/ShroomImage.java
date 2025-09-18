package ee.valiit.shroomshareback.persistence.shroomImage;

import ee.valiit.shroomshareback.persistence.shroom.Shroom;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "shroom_image", schema = "shroomshare")
public class ShroomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shroom_id", nullable = false)
    private Shroom shroom;

    @NotNull
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;

}