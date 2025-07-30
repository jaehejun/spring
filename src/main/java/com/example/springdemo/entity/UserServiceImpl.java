package com.example.springdemo.entity;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;

@Setter
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JobLauncher jobLauncher;
    private final Job csvJob;

    @Override
    public BatchStatus runBatch(String filePath) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) //timer: 중복 실행 방지를 위해 항상 유니크한 값 사용
                .addString("filePath", filePath) //String 파라미터
                .toJobParameters();

        //JobLauncher가 실제로 배치 실행을 담당
        //Job 실행 시 filePath를 파라미터로 전달함
        JobExecution jobExecution = jobLauncher.run(csvJob, jobParameters);

        return jobExecution.getStatus();
    }
}
