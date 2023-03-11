package site.project.accountinfoapp.service.kftcAuth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import site.project.accountinfoapp.service.kftcAuth.domain.KftcAuthorization;
import site.project.accountinfoapp.service.kftcAuth.repository.AuthorizationRepository;
import site.project.accountinfoapp.service.user.domain.User;
import site.project.accountinfoapp.service.user.dto.UserDto.UserTokenRequestDto;
import site.project.accountinfoapp.service.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthorizationRepository authorizationRepository;
    private final UserRepository userRepository;

    @Override
    public Object authenticate(UserTokenRequestDto tokens) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<KftcAuthorization> auth = authorizationRepository.findByUsername(username);
        User user = userRepository.findByUsername(username).orElseThrow();
        user.certify();

        if (auth.isPresent()){
            KftcAuthorization userAuth = auth.get();
            userAuth.update(
                    tokens.cntrToken(),
                    tokens.depositToken(),
                    tokens.refreshToken(),
                    tokens.userSeqNo(),
                    tokens.clientUseCode());
        }

        KftcAuthorization authorization = KftcAuthorization.builder()
                .cntrToken(tokens.cntrToken())
                .depositToken(tokens.depositToken())
                .refreshToken(tokens.refreshToken())
                .userSeqNo(tokens.userSeqNo())
                .clientUseCode(tokens.clientUseCode())
                .username(username)
                .build();
        return authorizationRepository.save(authorization);
    }

    @Override
    public Object findAll() {
        return authorizationRepository.findAll();
    }
}
