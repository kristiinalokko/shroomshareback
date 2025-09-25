package ee.valiit.shroomshareback.persistence.shroom;

import ee.valiit.shroomshareback.persistence.location.Location;
import ee.valiit.shroomshareback.persistence.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "shroom", schema = "shroomshare")
public class Shroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "status", nullable = false, length = Integer.MAX_VALUE)
    private String status;

    @ManyToMany(mappedBy = "shrooms")
    private List<Location> locations;

}