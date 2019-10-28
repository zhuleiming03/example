package com.vcredit.job.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@JobHandler(value = "demoJobHandler")
@Component
public class PullAccountFlowsRecordJob extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        log.info("[PushSmsInfoJob.execute]启动, xxl-job入参信息:" + param);
        System.out.println("This is a test ");

        return SUCCESS;
    }

}
