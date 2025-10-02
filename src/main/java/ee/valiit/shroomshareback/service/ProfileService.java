package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.controller.profile.dto.ProfileData;
import ee.valiit.shroomshareback.infrastructure.exception.DataNotFoundException;
import ee.valiit.shroomshareback.persistence.profile.Profile;
import ee.valiit.shroomshareback.persistence.profile.ProfileMapper;
import ee.valiit.shroomshareback.persistence.profile.ProfileRepository;
import ee.valiit.shroomshareback.persistence.user.User;
import ee.valiit.shroomshareback.persistence.user.UserRepository;
import ee.valiit.shroomshareback.persistence.userImage.UserImage;
import ee.valiit.shroomshareback.persistence.userImage.UserImageRepository;
import ee.valiit.shroomshareback.util.BytesConverter;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.BCException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final UserImageRepository userImageRepository;
    private final UserRepository userRepository;

    public ProfileData getProfile(Integer userId) {
        Optional<Profile> optionalProfile = profileRepository.findByUserId(userId);
        if (optionalProfile.isPresent()) {
            ProfileData profileData = createAndSetProfileData(userId, optionalProfile);
            return profileData;
        } else {
            throw new DataNotFoundException(Error.PROFILE_NOT_FOUND.getMessage(), Error.PROFILE_NOT_FOUND.getErrorCode());
        }


    }

    private ProfileData createAndSetProfileData(Integer userId, Optional<Profile> optionalProfile) {
        Profile profile = optionalProfile.get();
        ProfileData profileData = profileMapper.toProfileData(profile);
        handleImageIfexists(userId, profileData);
        return profileData;
    }

    private void handleImageIfexists(Integer userId, ProfileData profileData) {
        Optional<UserImage> optionalUserImage = userImageRepository.findByUserId(userId);
        if (optionalUserImage.isPresent()) {
            profileData.setImageData(BytesConverter.bytesToString(optionalUserImage.get().getImageData()));
        } else {
            profileData.setImageData("");
        }
    }

    public void addProfile(ProfileData profileData) {
        Profile profile = profileMapper.toProfile(profileData);
        User user = userRepository.getUserById(profileData.getUserId());
        profile.setUser(user);
        UserImage userImage = new UserImage();
        userImage.setUser(user);
        userImage.setImageData(BytesConverter.stringToBytes(profileData.getImageData()));
        profileRepository.save(profile);
        userImageRepository.save(userImage);

    }

    public void updateProfile(ProfileData profileData) {
        Profile profile = profileMapper.toProfile(profileData);
        profile.setId(profileData.getProfileId());
        profile.setUser(userRepository.getUserById(profileData.getUserId()));
        profileRepository.save(profile);
        if (!profileData.getImageData().isEmpty()) {
            Optional<UserImage> optionalImage = userImageRepository.findByUserId(profileData.getUserId());
            if (optionalImage.isPresent()) {
                UserImage userImage = optionalImage.get();
                userImage.setImageData(BytesConverter.stringToBytes(profileData.getImageData()));
                userImageRepository.save(userImage);
            } else {
                UserImage userImage = new UserImage();
                userImage.setUser(userRepository.getUserById(profileData.getUserId()));
                userImage.setImageData(BytesConverter.stringToBytes(profileData.getImageData()));
                userImageRepository.save(userImage);
            }
        }
    }
}
