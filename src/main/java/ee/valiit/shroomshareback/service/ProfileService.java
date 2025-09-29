package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.controller.profile.dto.ProfileData;
import ee.valiit.shroomshareback.infrastructure.exception.DataNotFoundException;
import ee.valiit.shroomshareback.persistence.profile.Profile;
import ee.valiit.shroomshareback.persistence.profile.ProfileMapper;
import ee.valiit.shroomshareback.persistence.profile.ProfileRepository;
import ee.valiit.shroomshareback.persistence.userImage.UserImage;
import ee.valiit.shroomshareback.persistence.userImage.UserImageRepository;
import ee.valiit.shroomshareback.util.BytesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final UserImageRepository userImageRepository;

    public ProfileData getProfile(Integer userId) {
        Optional<Profile> optionalProfile = profileRepository.findByUserId(userId);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            ProfileData profileData = profileMapper.toProfileData(profile);
            Optional<UserImage> optionalUserImage = userImageRepository.findByUserId(userId);
            if (optionalUserImage.isPresent()) {
                profileData.setImageData(BytesConverter.bytesToString(optionalUserImage.get().getImageData()));
            } else {
                profileData.setImageData("");
            }
            return profileData;
        } else {
            throw new DataNotFoundException(Error.PROFILE_NOT_FOUND.getMessage(), Error.PROFILE_NOT_FOUND.getErrorCode());
        }


    }
}
