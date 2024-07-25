package com.tendeuse_automatique.batch.tasklet;


import com.tendeuse_automatique.batch.utils.JobException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileVerificationSkipper implements SkipPolicy {


    @Value("${spring.batch.skipPolicy.enabled}")
    private boolean skipPolicyEnabled;

    @Value("${spring.batch.skipPolicy.skipCount}")
    private int skipCount;


    /**
     * Cette méthode définit la stratégie pour sauter des erreurs lors de la lecture du fichier
     * le saut est possible si l'exception levée est une exception lié aux données (erreur de parsing) et si le
     * nombre de sauts de dépasse pas skipMaxItems
     *
     * @param exception l'exception levée
     * @param skipCounts nombre de sauts courant
     * @return true si le saut est possible et false sinon
     * @throws SkipLimitExceededException exception à lever si le nombre de sauts dépasse skipMaxItems
     */
    @Override
    public boolean shouldSkip(Throwable exception, long skipCounts) throws SkipLimitExceededException {
        if (skipPolicyEnabled
                && exception instanceof FlatFileParseException flatFileParseException
                && (int)skipCounts <= skipCount) {
            String errorMessage = "An error occured while processing the " + flatFileParseException.getLineNumber()
                    + " line of the file. Below was the faulty " + "input.\n" +
                    flatFileParseException.getInput() + "\n";
            log.error(errorMessage);
            return true;
        } else if (skipPolicyEnabled
                && exception instanceof JobException ex
                && (int)skipCounts < skipCount) {
            log.error("Tolérance d'erreur dépassée sur le job d'import {}", ex.getMessage());
            return true;
        } else {
            return false;
        }
    }


}
