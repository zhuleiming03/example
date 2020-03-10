package example.spring.service;

import example.spring.config.SettingConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    private SettingConfig settingConfig;

    @Resource
    public void setSettingConfig(SettingConfig settingConfig) {
        this.settingConfig = settingConfig;
        System.out.println("--------------- Show setting -----------------");
        System.out.println("name:" + this.settingConfig.name);
        System.out.println("age:" + this.settingConfig.age);
        System.out.println("interest:" + this.settingConfig.interest);
    }
}
