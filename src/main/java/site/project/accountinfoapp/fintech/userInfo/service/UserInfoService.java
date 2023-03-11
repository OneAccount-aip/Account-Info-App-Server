package site.project.accountinfoapp.fintech.userInfo.service;

import org.springframework.data.crossstore.ChangeSetPersister;

public interface UserInfoService {
    Object showUserInfo() throws ChangeSetPersister.NotFoundException;
}
