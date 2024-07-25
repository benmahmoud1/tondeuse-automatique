package com.tendeuse_automatique.batch.configuration;

import com.tendeuse_automatique.batch.listeners.TondeuseProcessorFailureListener;
import com.tendeuse_automatique.batch.listeners.TondeuseReadListener;
import com.tendeuse_automatique.batch.listeners.TondeuseSkipListener;
import com.tendeuse_automatique.batch.listeners.TondeuseWriteListener;
import com.tendeuse_automatique.batch.processors.TondeuseProcessor;
import com.tendeuse_automatique.batch.readers.TondeuseReader;
import com.tendeuse_automatique.batch.tasklet.FileVerificationSkipper;
import com.tendeuse_automatique.batch.writers.TondeuseWriter;
import com.tendeuse_automatique.dto.TondeuseInDto;
import com.tendeuse_automatique.entity.Tondeuse;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.policy.NeverRetryPolicy;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchTondeuseStepConfig {

    @Value("${spring.batch.chunkSize}")
    private int chunkSize;

    private final FileVerificationSkipper fileVerificationSkipper;
    private final TondeuseReadListener tondeuseReadListener;
    private final TondeuseProcessorFailureListener tondeuseProcessorFailureListener;
    private final TondeuseWriteListener tondeuseWriteListener;

    private final TondeuseReader tondeuseReader;
    private final TondeuseProcessor tondeuseProcessor;
    private final TondeuseWriter tondeuseWriter;

    private final TondeuseSkipListener tondeuseSkipListener;

    //  Steps configuration

    /**
     * l'étape d'import du fichier txt
     *
     * @return Step l'étape construite à partir des reader, processor et writer
     */
    @Bean(name = "tondeuseStep")
    public Step tondeuseStep(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
        return new StepBuilder("tondeuseStep",jobRepository)
                .<TondeuseInDto, Tondeuse>chunk(chunkSize,transactionManager)
                .reader(tondeuseReader)
                .listener(tondeuseReadListener)
                .faultTolerant()
                .skipPolicy(fileVerificationSkipper)
                .retryPolicy(new NeverRetryPolicy())
                .listener(tondeuseSkipListener)
                .processor(tondeuseProcessor)
                .faultTolerant()
                .retryPolicy(new NeverRetryPolicy())
                .listener(tondeuseProcessorFailureListener)
                .writer(tondeuseWriter)
                .faultTolerant()
                .retryPolicy(new NeverRetryPolicy())
                .listener(tondeuseWriteListener)
                .build();
    }

}
