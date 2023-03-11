package site.project.accountinfoapp.service.kftcAuth.service;

import site.project.accountinfoapp.service.user.dto.UserDto.UserTokenRequestDto;

public interface AuthService {
    Object authenticate(UserTokenRequestDto tokens);
    Object findAll();
}
