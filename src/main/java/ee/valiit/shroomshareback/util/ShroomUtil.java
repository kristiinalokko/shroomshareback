package ee.valiit.shroomshareback.util;

import ee.valiit.shroomshareback.controller.shroom.dto.ShroomBasicInfo;
import ee.valiit.shroomshareback.persistence.shroom.Shroom;

import java.util.ArrayList;
import java.util.List;

public class ShroomUtil {

    public static List<ShroomBasicInfo> createAndSaveShroomBasicInfos(List<Shroom> shrooms) {
        List<ShroomBasicInfo> shroomBasicInfos = new ArrayList<>();
        for (Shroom shroom : shrooms) {
            ShroomBasicInfo shroomBasicInfo = new ShroomBasicInfo();
            shroomBasicInfo.setShroomId(shroom.getId());
            shroomBasicInfo.setShroomName(shroom.getName());
            shroomBasicInfos.add(shroomBasicInfo);
        }
        return shroomBasicInfos;
    }

}
