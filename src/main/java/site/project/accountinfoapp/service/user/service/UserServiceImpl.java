package site.project.accountinfoapp.service.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.project.accountinfoapp.common.exception.BusinessException;
import site.project.accountinfoapp.common.exception.ErrorCode;
import site.project.accountinfoapp.service.user.domain.User;
import site.project.accountinfoapp.service.user.dto.UserDto.UserInfoResponseDto;
import site.project.accountinfoapp.service.user.dto.UserDto.UserSignupRequestDto;
import site.project.accountinfoapp.service.user.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public User signup(UserSignupRequestDto user) {
        if (userRepository.findByUsername(user.username()).isPresent())
            throw new BusinessException(ErrorCode.DUPLICATE);
        if (userRepository.findByEmail(user.email()).isPresent())
            throw new BusinessException(ErrorCode.DUPLICATE);

        String rawPassword = user.password();

        User userAccount = User.builder()
                .username(user.username())
                .email(user.email())
                .password(encoder.encode(rawPassword))
                .role("ROLE_USER")
                .build();
        return userRepository.save(userAccount);
    }

    @Override
    public Object checkToken() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(Exception::new);
        return user.getCertified();
    }

    @Override
    public Object getInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        return new UserInfoResponseDto(user.getUsername(), user.getEmail(), user.getCertified(), user.getCreateAt());
    }
}
