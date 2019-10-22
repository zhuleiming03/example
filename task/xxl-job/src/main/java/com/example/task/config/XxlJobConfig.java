package com.example.task.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class XxlJobConfig {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    //@Value("${xxl.job.executor.ip}")
    private String ip = null;

    @Value("${xxl.job.executor.port}")
    private int port;

    //@Value("${xxl.job.accessToken}")
    private String accessToken = null ;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    @Value("${xxl.job.executor.prefer-ip-address}")
    private Boolean preferIpAddress;

    @Value("${xxl.job.executor.preferred-networks}")
    private String preferredNetworks;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
//        if (preferIpAddress != null && preferIpAddress) {
//            ip = (ip == null) ? getLocalHostLANAddress(preferredNetworks) : ip;
//        }
//        log.info(">>>>>>>>>>> xxl-job executor ip:{}", ip);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }

    private String getLocalHostLANAddress(String preferredNetworks) {
        try {
            // 遍历所有的网络接口
            for (Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces.hasMoreElements(); ) {
                NetworkInterface netInterface = (NetworkInterface) networkInterfaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = netInterface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress() && inetAddr instanceof Inet4Address) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress() && StringUtils.startsWithIgnoreCase(inetAddr.getHostAddress(), preferredNetworks)) {
                            // 如果是site-local地址，优先网段匹配成功
                            return inetAddr.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

}
