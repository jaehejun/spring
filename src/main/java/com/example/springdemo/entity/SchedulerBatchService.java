package com.example.springdemo.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerBatchService {

    private final JobLauncher jobLauncher;
    private final Job csvJob;

    //users.csv 경로(src/main/resources/users.csv 기준으로 classpath에서 찾음)
    private  static final String FILE_PATH = "users.csv";

//    @Scheduled(fixedRate = 60000) //1분마다 실행
    @Scheduled(cron = "0 */2 * * * *")    //2분마다 실행
    public void runsScheduledBatchJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .addString("filePath",FILE_PATH)
                    .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(csvJob, jobParameters);

            System.out.println("배치 실행 상태: " + jobExecution.getStatus());
        } catch (Exception e) {
            System.err.println("배치 실행 중 오류 발생: " + e.getMessage());
        }
    }
}
