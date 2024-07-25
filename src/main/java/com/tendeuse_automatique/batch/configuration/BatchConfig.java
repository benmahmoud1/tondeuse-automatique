package com.tendeuse_automatique.batch.configuration;

import com.tendeuse_automatique.batch.listeners.TondeuseJobImportListener;
import com.tendeuse_automatique.batch.tasklet.FileVerificationSkipper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJvmExitCodeMapper;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@RequiredArgsConstructor
@EnableBatchProcessing
@Configuration
public class BatchConfig {



    /**
     * Le job d'import des fichiers txt
     *
     * @return Step l'étape construite à partir des reader, processor et writer
     */
    @Bean(name = "tondeuseJob")
    public Job tondeuseJob(JobRepository jobRepository, Step tondeuseStep, TondeuseJobImportListener tondeuseJobImportListener) {
        return new JobBuilder("tondeuseJob",jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(tondeuseJobImportListener)
                .start(tondeuseStep)
                .build();
    }

    /**
     * flux d'exécution lié à l'étape importDataFromTxtFileStep
     *
     * @return Flow tendeuseFlow
     */
    @Bean(name = ")")
    public Flow tondeuseFlow(Step tendeuseStep) {
        return new FlowBuilder<SimpleFlow>("tondeuseFlow")
                .start(tendeuseStep)
                .build();
    }

    @Bean
    public FileVerificationSkipper fileVerificationSkipper() {
        return new FileVerificationSkipper();
    }

    @Bean
    public ExitCodeMapper exitCodeMapper() {
        return new SimpleJvmExitCodeMapper();
    }


}
