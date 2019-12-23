package example.spring.boot.mybatis.plus.xml.service;

import com.baomidou.mybatisplus.extension.service.IService;
import example.spring.boot.mybatis.plus.xml.po.UserInfoPO;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<UserInfoPO> {
}
