package com.tendeuse_automatique.batch.listeners;

import com.tendeuse_automatique.batch.utils.JobConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Slf4j
@Getter
@RequiredArgsConstructor
@Component
public class TondeuseJobImportListener implements JobExecutionListener {

    public static final String NOMBRE_DE_LIGNES_RECUPEREES = "nombre de lignes récupérées : {}";
    public static final String NOMBRE_DE_LIGNES_TRAITEES = "nombre de lignes traitées : {}";
    public static final String NOMBRE_DE_LIGNES_ECRITES = "nombre de lignes écrites : {}";
    public static final String STATUS_JOB = "Status de step courant : {}";

    private final TondeuseSkipListener tondeuseSkipListener;
    private final TondeuseReadListener tondeuseReadListener;

    @BeforeJob
    @Override
    public void beforeJob(JobExecution jobExecution) {

        // pas d'implementation
    }

    /**
     * Afficher des statistiques après l'exécution du job avec succès, sinon afficher la listes des exceptions rencontrées
     *
     * @param jobExecution jobExecution
     */
    @AfterJob
    @Override
    public void afterJob(JobExecution jobExecution) {
        switch (jobExecution.getStatus()) {
            case COMPLETED:
                log.info("Job d'import de données exécuté avec succès");
                log.info(JobConstants.LOG_LINE_SEPARATOR);
                log.info("Statistiques du Job:");
                jobExecution.getStepExecutions().forEach(this::log);
                break;
            case FAILED:
                log.error("Erreur lors de l'exécution du job d'import");
                var listException = jobExecution.getAllFailureExceptions();
                listException.forEach(ex -> {
                    log.error(ex.getMessage());
                    log.error(JobConstants.LOG_LINE_SEPARATOR);
                });
                break;
            case STOPPED:
                log.error("Job d'import arrêté");
                break;
            case ABANDONED:
                log.error("Job d'import abandonné");
                break;
            default:
                log.warn("Statut inconnu");
        }
    }

    /**
     * Logger pour les steps CSV/EXCEL/TXT
     *
     * @param stepExecution objet étape execution
     */
    private void log(StepExecution stepExecution) {
        log.info("Nom du Step {}", stepExecution.getStepName());
        if (stepExecution.getStepName().contains("tondeuseStep")) {
            Set<Integer> skippableOffset = new HashSet<>();
            skippableOffset.addAll(tondeuseSkipListener.getSkippableOffset());
            skippableOffset.addAll(tondeuseReadListener.getErrorReadOffset());
            var nbIgnored = stepExecution.getReadCount() - stepExecution.getWriteCount();

            log.info("Nom du Step {}", stepExecution.getStepName());
            log.info(STATUS_JOB, stepExecution.getStatus());
            log.info(NOMBRE_DE_LIGNES_RECUPEREES, stepExecution.getReadCount());
            log.info(NOMBRE_DE_LIGNES_ECRITES, stepExecution.getWriteCount());
            log.info(NOMBRE_DE_LIGNES_TRAITEES, nbIgnored);
            log.info(JobConstants.LOG_LINE_SEPARATOR);
        }
    }
}
