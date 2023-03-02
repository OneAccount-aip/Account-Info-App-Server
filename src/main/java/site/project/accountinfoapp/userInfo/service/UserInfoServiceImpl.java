package site.project.accountinfoapp.userInfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.project.accountinfoapp.api.UserInfoOpenFeign;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{

    private final UserInfoOpenFeign openFeign;

    @Override
    public Object showUserInfo(String token, String seqNo) {
        return openFeign.retrieveUserInfo(token, seqNo);
    }
}
