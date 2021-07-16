package hello.admincontrol.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        final User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        newUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(newUser);
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
}

