package com.zhengyuanfang.rocketMQ;

import com.zhengyuanfang.util.MailBean;
import com.zhengyuanfang.util.MailService;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        topic = "sendPwdResetByEmail",
        selectorExpression = "*",
        consumerGroup = "zyf_admin",
        consumeMode = ConsumeMode.CONCURRENTLY
)
public class Consumer implements RocketMQListener<MailBean> {

    @Autowired
    private MailService mailService;

    @Override
    public void onMessage(MailBean mailBean) {
        try {
            mailService.sendSimpleMail(mailBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
