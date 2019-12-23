package example.spring.boot.mybatis.plus.xml.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import example.spring.boot.mybatis.plus.xml.mapper.UserInfoMapper;
import example.spring.boot.mybatis.plus.xml.po.UserInfoPO;
import example.spring.boot.mybatis.plus.xml.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoPO> implements UserService {
}
