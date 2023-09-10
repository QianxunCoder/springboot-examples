package com.github.codeqingkong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.codeqingkong.dto.UserDTO;
import com.github.codeqingkong.entity.UserDO;
import com.github.codeqingkong.service.IUserService;
import com.github.codeqingkong.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: QingKong
 * @date: 2023/2/27
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/save")
    public String save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return "success";
    }

    @GetMapping("/page")
    public List<UserVO> page() {
        Page<UserDO> page = new Page<>();
        page.setCurrent(1);
        page.setSize(10);
        IPage<UserDO> pageRecords = userService.page(page);
        List<UserVO> userVOList = new ArrayList<>();
        for (UserDO record : pageRecords.getRecords()) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(record,userVO);
            userVOList.add(userVO);
        }
        return userVOList;
    }


}
