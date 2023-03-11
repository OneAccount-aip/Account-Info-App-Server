package site.project.accountinfoapp.fintech.userInfo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import site.project.accountinfoapp.fintech.userInfo.feignClient.UserInfoOpenFeign;
import site.project.accountinfoapp.service.kftcAuth.domain.KftcAuthorization;
import site.project.accountinfoapp.service.kftcAuth.repository.AuthorizationRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{

    private final UserInfoOpenFeign openFeign;
    private final AuthorizationRepository authorizationRepository;

    @Override
    public Object showUserInfo() throws ChangeSetPersister.NotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        KftcAuthorization userAuth = authorizationRepository.findByUsername(username).orElseThrow(
                ChangeSetPersister.NotFoundException::new);
        return openFeign.retrieveUserInfo("Bearer "+userAuth.getCntrToken(), userAuth.getUserSeqNo());
    }
}
