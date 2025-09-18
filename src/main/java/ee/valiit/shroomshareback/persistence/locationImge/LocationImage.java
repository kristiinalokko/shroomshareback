package ee.valiit.shroomshareback.persistence.locationImge;

import ee.valiit.shroomshareback.persistence.location.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "location_image", schema = "shroomshare")
public class LocationImage {
    @Id
    @ColumnDefault("nextval('shroomshare.location_image_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @NotNull
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;

}