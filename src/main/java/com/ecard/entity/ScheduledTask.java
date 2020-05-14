package com.ecard.entity;

import com.ecard.dao.RedpacketRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTask {
    @Autowired
    private RedpacketRecordMapper redpacketRecordMapper;

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduledTask() {
        List<RedpacketRecord> redpacketRecords = redpacketRecordMapper.selectByDisableFlag();
        for(RedpacketRecord redpacketRecord : redpacketRecords){
            if (redpacketRecord.getExpireDate().after(new Date())){
                redpacketRecord.setDisableFlag(1);
                redpacketRecordMapper.updateByPrimaryKeySelective(redpacketRecord);
            }
        }
    }
}
