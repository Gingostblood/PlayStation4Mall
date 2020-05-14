package com.gingost.layui.service.impl;

import com.gingost.layui.domain.Role;
import com.gingost.layui.domain.User;
import com.gingost.layui.domain.dto.QueryCriteria.UserQueryCriteria;
import com.gingost.layui.domain.dto.req.UserReqDto;
import com.gingost.layui.domain.dto.resp.UserRespDto;
import com.gingost.layui.domain.vo.LayuiTableVo;
import com.gingost.layui.domain.vo.LayuiTransferVo;
import com.gingost.layui.mapper.UserMapper;
import com.gingost.layui.repository.RoleJpa;
import com.gingost.layui.repository.UserJpa;
import com.gingost.layui.service.UserService;
import com.gingost.layui.untils.ArgsCheck;
import com.gingost.layui.untils.QueryHelp;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author:lezzy
 * @Date:2020/4/2 0:57
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserJpa userJpa;
    private RoleJpa roleJpa;
    private UserMapper userMapper;

    @Override
    public LayuiTableVo<UserRespDto> findAll(int page, int size) {
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size);
        UserQueryCriteria userQueryCriteria = new UserQueryCriteria();
        userQueryCriteria.setId(1);
        List<User> userList = userJpa.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, userQueryCriteria, criteriaBuilder), pageable).getContent();
        int count = userList.size();
        List<UserRespDto> userRespDtoList = new ArrayList<>();
        for (User user : userList) {
            List<String> nickNameList = new ArrayList<>();
            UserRespDto userRespDto = userMapper.toDto(user);
            for (Role role : user.getRoles()) {
                nickNameList.add(role.getNickName());
            }
            userRespDto.setNickName(nickNameList);
            userRespDtoList.add(userRespDto);
        }
        return new LayuiTableVo(count, userRespDtoList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeStatu(Integer id) {
        User user = userJpa.findById(id).orElseGet(User::new);
        if (user.getStatu() == 1) {
            user.setStatu(0);
            userJpa.save(user);
        } else {
            user.setStatu(1);
            userJpa.save(user);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserById(Integer id) {
        ArgsCheck.NumCheck(id, "警告，服务器可能遭受致命打击，请立即联系网站管理员");
        ArgsCheck.ObjCheck(userJpa.findById(id).orElseGet(User::new), "该用户已经不存在");
        userJpa.deleteById(id);
        roleJpa.delUserRole(id);
    }

    @Override
    public LayuiTransferVo getTransferRole(Integer id) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Role> roleList = roleJpa.findAllByIdIsNot(2);
        for (Role role : roleList) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", role.getId());
            map.put("title", role.getNickName());
            map.put("disabled", "");
            map.put("checked", "");
            mapList.add(map);
        }
        if (id == null || id.equals("")) {
            return new LayuiTransferVo(mapList, null);
        } else {
            User user = userJpa.findById(id).orElseGet(User::new);
            Set<Role> roles = user.getRoles();
            List<Integer> list = new ArrayList<>();
            for (Role role : roles) {
                list.add(role.getId());
            }
            return new LayuiTransferVo(mapList, list);
        }
    }

    @Override
    public User findUserByid(Integer id) {
        User user = userJpa.findById(id).orElseGet(User::new);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String changeUser(UserReqDto req) {
        if (req.getRoles().size() == 0) {
            return " <h2 style='color: red; text-align: center;'>脑子有问题还是眼睛有问题？都说了角色不能为空还要搞？</h2>";
        }
        User oldUser = new User(req);//缺少Set<Role>
        User newUser = userJpa.findById(req.getId()).orElseGet(User::new);
        Set<Integer> roles = req.getRoles();//role_id数组
        Set<Role> roleSet = new HashSet<>();
        for (Integer roleid : roles) {
            Role role = roleJpa.findById(roleid).orElseGet(Role::new);
            roleSet.add(role);
        }
        oldUser.setRoles(roleSet);
        if (oldUser.equals(newUser)) {
            return " <h2 style='color: red; text-align: center;'>提示信息:你没有修改任何数据</h2>";
        } else {
            //////////////////////////////////
            Set<User> userSet = new HashSet<>();
            oldUser.setUpdateTime(new Date());
            userSet.add(oldUser);

            roleJpa.delUserRole(req.getId());
            for (Integer roleid : roles) {
                Role role = roleJpa.findById(roleid).orElseGet(Role::new);
                roleJpa.saveUserRole(req.getId(), roleid);
            }
            return "<h2 style='text-align: center;'>提示信息:修改成功，请刷新页面查看</h2>";
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveUser(UserReqDto req) {
        Set<Integer> roles = req.getRoles();
        if (roles == null || roles.equals("") || roles.size() == 0) {
            return "<h2>我求求你添加个角色吧，别搞我了</h2>";
        } else {
            User userName = userJpa.findUserByUsername(req.getUsername());
            User userEmail = userJpa.findUserByEmail(req.getEmail());
            User userPhone = userJpa.findUserByPhone(req.getPhone());
            if (userName != null) {
                return " <h2 style='color: red; text-align: center;'>提示信息:用户名已经被注册，请仔细核对</h2>";
            } else if (userEmail != null) {
                return " <h2 style='color: red; text-align: center;'>提示信息:邮箱已经被注册，请仔细核对</h2>";
            } else if (userPhone != null) {
                return " <h2 style='color: red; text-align: center;'>提示信息:电话号码已经被注册，请仔细核对</h2>";
            }
            User user = new User(req);
            System.out.println("=======================================" + req);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String bcpwd = bCryptPasswordEncoder.encode(req.getPassword());
            user.setPassword(bcpwd);
            user.setCreatTtime(new Date());
            user.setUpdateTime(user.getCreatTtime());
            user.setStatu(1);
            userJpa.save(user);
            for (Integer roleid : roles) {
                roleJpa.saveUserRole(user.getId(), roleid);
            }
            return "<h2 style='text-align: center;'>提示信息:修改成功，添加成功</h2>";
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        User user = userJpa.findUserByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("该用户名尚未被添加");
        }else if(user.getStatu()==0){
            throw new UsernameNotFoundException("该账户已被封禁");
        }
        return user;
    }
}
