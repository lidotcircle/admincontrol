package hello.admincontrol.service.impl;

import java.time.Instant;
import java.sql.Date;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.admincontrol.entity.RefreshToken;
import hello.admincontrol.repository.RefreshTokenRepository;
import hello.admincontrol.service.RefreshTokenService;


@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Autowired()
    private RefreshTokenRepository refreshTokenRepository;

    private Date expireFromNow() {
        java.util.Date expire = java.util.Date.from(
                Instant.now().plusSeconds(2 * 24 * 60 * 60));
        Date ans = new Date(expire.getTime());
        return ans;
    }

    @Override
    public RefreshToken createRefreshToken(String username) {
        UUID corrId = UUID.randomUUID();
        RefreshToken token = new RefreshToken();
        token.setToken(corrId.toString());
        token.setUserName(username);
        token.setExpiredDate(this.expireFromNow());

        this.refreshTokenRepository.save(token);

        return token;
    }

    @Override
    public Optional<RefreshToken> getRefreshToken(String token) {
        RefreshToken ans = this.refreshTokenRepository.getByToken(token);
        Date now = new Date(java.util.Date.from(Instant.now()).getTime());

        if(ans == null || ans.getExpiredDate().before(now)) {
            return Optional.empty();
        } else {
            return Optional.of(ans);
        }
    }

    @Override
    public void refreshRfreshToken(String token) {
        this.refreshTokenRepository.updateExpiredDateForToken(token, this.expireFromNow());
    }

    @Override
    public void deleteRefreshTokenByUserName(String username) {
        this.refreshTokenRepository.deleteByUserName(username);
    }

	@Override
	public void deleteRefreshTokenByToken(String token) {
        this.refreshTokenRepository.deleteByToken(token);
	}
}

