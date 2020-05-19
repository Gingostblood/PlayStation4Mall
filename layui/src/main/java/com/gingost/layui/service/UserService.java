package com.gingost.layui.service;

import com.gingost.layui.domain.User;
import com.gingost.layui.domain.dto.req.UserReqDto;
import com.gingost.layui.domain.dto.resp.UserRespDto;
import com.gingost.layui.domain.vo.LayuiTableVo;
import com.gingost.layui.domain.vo.LayuiTransferVo;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author:lezzy
 * @Date:2020/4/2 0:56
 */
public interface UserService extends UserDetailsService {
    LayuiTableVo<UserRespDto> findAll(int page,int size);

    void changeStatu(Integer id);

    void deleteUserById(Integer id);

    LayuiTransferVo getTransferRole(Integer id);

    User findUserByid(Integer id);

    String changeUser(UserReqDto req);

    String saveUser(UserReqDto req);
}
