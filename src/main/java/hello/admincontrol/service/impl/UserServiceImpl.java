package hello.admincontrol.service.impl;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hello.admincontrol.entity.User;
import hello.admincontrol.exception.Forbidden;
import hello.admincontrol.exception.NotFound;
import hello.admincontrol.repository.UserRepository;
import hello.admincontrol.service.UserService;
import hello.admincontrol.service.dto.user.UserPasswordPutDTO;
import hello.admincontrol.service.dto.user.UserPostDTO;
import hello.admincontrol.service.dto.user.UserPutDTO;
import hello.admincontrol.service.dto.user.UserResponseDTO;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

	@Override
	public void createUser(UserPostDTO user) {
        try {
            final User newUser = new User();
            BeanUtils.copyProperties(user, newUser);
            newUser.setThirdPartyAccountType("none");
            newUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
            this.userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            if (e.getRootCause() instanceof SQLIntegrityConstraintViolationException
                    && e.getRootCause().getMessage().contains("Duplicate")) 
            {
                var rootMsg = e.getRootCause().getMessage();

                if(rootMsg.contains(String.format("'%s'", user.getPhone()))) {
                    throw new Forbidden("手机号已注册");
                }
                else if(rootMsg.contains(String.format("'%s'", user.getEmail()))) {
                    throw new Forbidden("邮箱已注册");
                }
                else if(rootMsg.contains(String.format("'%s'", user.getUserName()))) {
                    throw new Forbidden("用户名已注册");
                }
                else {
                    throw e;
                }
            } else {
                throw e;
            }
        }
	}

	@Override
	public void updateUser(UserPutDTO user) {
        final var u = this.userRepository.findByUserName(user.getUserName())
            .orElseThrow(() -> new NotFound("用户不存在"));

        BeanUtils.copyProperties(u, user);
        this.userRepository.save(u);
	}

	@Override
	public void deleteUser(String username) {
        this.userRepository.deleteByUserName(username);
	}

	@Override
	public UserResponseDTO getUserInfo(String username) {
        final var u = this.userRepository.findByUserName(username)
            .orElseThrow(() -> new NotFound("用户不存在"));

        final var ans = new UserResponseDTO();
        BeanUtils.copyProperties(u, ans);
        ans.setProfilePhoto(new String(u.getProfilePhoto()));

        return ans;
	}

	@Override
	public void updateUserPassword(UserPasswordPutDTO newPass) {
        final var u = this.userRepository.findByUserName(newPass.getUserName())
            .orElseThrow(() -> new NotFound("用户不存在"));

        if (!this.passwordEncoder.matches(newPass.getOldPassword(), u.getPassword())) {
            throw new Forbidden("密码错误");
        }

        u.setPassword(this.passwordEncoder.encode(newPass.getNewPassword()));
        this.userRepository.save(u);
	}

	@Override
	public boolean validateUserPassowrd(String username, String password) {
        final var u = this.userRepository.findByUserName(username)
            .orElseThrow(() -> new NotFound("用户不存在"));

        return this.passwordEncoder.matches(password, u.getPassword());
	}
}

