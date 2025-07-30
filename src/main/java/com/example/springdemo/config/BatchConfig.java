package com.example.springdemo.config;

import com.example.springdemo.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Log4j2
@Configuration
public class BatchConfig { // 배치 잡(Job)과 스텝(Step), 리더(ItemReader), 라이터(ItemWriter) 설정
    @Bean
    public Job csvJob(JobRepository jobRepository, Step step) { //Job: 하나의 전체 배치 작업 단위
        return new JobBuilder("csvJob", jobRepository)
                .incrementer(new RunIdIncrementer()) //RunIdIncrementer()는 매 실행 시 고유하게 ID를 생성해 동일 Job도 여러 번 실행 가능
                .start(step) //.start(step): 이 Job은 step() 하나로 시작해서 끝나는 단일 단계 작업
                .build();
    }
    @Bean
    public ItemProcessor<User,User> processor() {
        return user -> {
            return user.getAge() >= 30 ? user : null; //null반환시 writer로 전달되지 않음
        };
    }
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) { //Step: Job을 구성하는 작업 단위
        return new StepBuilder("csvStep", jobRepository)
                //chunk(5)는 5개 단위로 데이터를 읽고 처리하고, 쓰는 단위 처리(chunk processing)를 의미
                //User 타입 데이터를 읽고 그대로 다시 출력하므로 <User, User>로 타입 지정
                .<User, User> chunk(5, transactionManager)
                .reader(reader(null))
                .processor(processor())
                .writer(writer()) //StepScope로 CSV 경로 받음
                .build();
    }
    @Bean
    @StepScope  //이 Bean은 Step 실행 시점에 생성됨 -> jobParameter를 사용할 수 있음
    public FlatFileItemReader<User> reader(@Value("#{jobParameters['filePath']}") String filePath) {
        return new FlatFileItemReaderBuilder<User>()
                .name("userReader")
                // .resource(new FileSystemResource(filePath)) --> 실제 파일 시스템 상의 경로 사용
                .resource(new ClassPathResource(filePath)) //src/main/resources 경로에 위치한 파일
                .linesToSkip(1)
                .delimited()
                .names("name","age")    //CSV 컬럼 순서와 이름이 User 클래스의 필드와 일치해야함
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(User.class);
                }})
                .build();
    }
    @Bean
    public ItemWriter<User> writer() { //변환된 객체를 로깅하거나 DB에 저장하는 역할
        return items -> {
            log.info("=== Batch Output Start ===");
            for (User user : items) {
                log.info(user.toString());
            }
            log.info("=== Batch Output End ===");
        };
    }
}
