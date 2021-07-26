package hello.admincontrol.service;

import hello.admincontrol.service.dto.user.UserPasswordPutDTO;
import hello.admincontrol.service.dto.user.UserPostDTO;
import hello.admincontrol.service.dto.user.UserPutDTO;
import hello.admincontrol.service.dto.user.UserResponseDTO;


public interface UserService {
    /** 创建用户 */
    void createUser(UserPostDTO user);

    /** 修改用户信息 */
    void updateUser(UserPutDTO  user);

    /** 修改用户密码 */
    void updateUserPassword(UserPasswordPutDTO newPass);

    /** 验证用户密码 */
    boolean validateUserPassowrd(String username, String password);

    /** 删除用户 */
    void deleteUser(String username);

    /** 获取用户信息 */
    UserResponseDTO getUserInfo(String username);
}

