package site.project.accountinfoapp.service.user.service;

import site.project.accountinfoapp.service.user.domain.User;
import site.project.accountinfoapp.service.user.dto.UserDto.UserSignupRequestDto;

public interface UserService {

    User signup(UserSignupRequestDto user);

    Object checkToken() throws Exception;

    Object getInfo();
}
