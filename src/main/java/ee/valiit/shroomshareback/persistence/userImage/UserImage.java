package ee.valiit.shroomshareback.persistence.userImage;

import ee.valiit.shroomshareback.persistence.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_image", schema = "shroomshare")
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_image_id_gen")
    @SequenceGenerator(name = "user_image_id_gen", sequenceName = "user_image_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;

}