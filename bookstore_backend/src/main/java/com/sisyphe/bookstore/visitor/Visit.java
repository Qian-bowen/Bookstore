package com.sisyphe.bookstore.visitor;


import com.sisyphe.bookstore.entity.VisitData;
import com.sisyphe.bookstore.repository.VisitDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Configuration
@EnableScheduling
public class Visit{
    private static Count count=new Count();

    @Autowired
    private VisitDataRepository visitDataRepository;

    @PostConstruct
    public void setValue()
    {
        Integer recordNum=visitDataRepository.getRecordNumber();
        System.out.println(recordNum);
        if(recordNum==0)
        {
            //default set is zero
            return;
        }
        Pageable pageable = PageRequest.of(0,1);
        List<VisitData> visitData=visitDataRepository.getTopRecordByRecordTime(pageable);
        if(visitData==null) return;
        count.setValue(visitData.get(0).getVisitNum());
    }

    /**
     * write back every 5 minute
     */
    @Scheduled(fixedRate = 300000)
    public void writeBack()
    {
        System.out.println("write back");
        VisitData visitData=new VisitData(count.getCount(), LocalDateTime.now());
        visitDataRepository.saveAndFlush(visitData);
    }

    @PreDestroy
    public void writeBackBeforeDestroy()
    {
        System.out.println("write back before destroy");
        VisitData visitData=new VisitData(count.getCount(), LocalDateTime.now());
        visitDataRepository.saveAndFlush(visitData);
    }

    public Visit(){}

    public synchronized int getCountValue()
    {
        return count.incrementAndGet();
    }
}
