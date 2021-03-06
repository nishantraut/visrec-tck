package jsr381.tck.tests.spi;

import jsr381.tck.spi.JSR381Configuration;
import org.jboss.test.audit.annotations.SpecAssertion;
import org.jboss.test.audit.annotations.SpecVersion;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.ml.classification.ImageClassifier;
import javax.visrec.spi.ClassifierService;
import javax.visrec.spi.ServiceProvider;

import static org.testng.Assert.assertNotNull;

@SpecVersion(spec = "JSR 381", version = "1.0.0")
public class ClassifierServiceTest {

    private JSR381Configuration config;

    @BeforeTest
    public void setUp() {
        config = JSR381Configuration.Load();
    }

    @Test(description = "4.2.1 Must use the ImageClassifier.BuildingBlock to return an implemented and trained classification model as ImageClassifier.")
    @SpecAssertion(section = "4.2.1", id = "421-D1")
    public void testCreateImageClassifier() throws ClassifierCreationException {
        ServiceProvider serviceProvider = ServiceProvider.current();
        ClassifierService classifierService = serviceProvider.getClassifierService();
        assertNotNull(classifierService);
        classifierService.createImageClassifier(config.getABImageClassificationBuildingBlock(
                config.getABImageClassificationBuilder(ImageClassifier.builder()
                        .imageHeight(28)
                        .imageWidth(28)
                        .maxError(0.4f)
                        .maxEpochs(100)
                        .learningRate(0.01f))
        ));
    }

    @Test(description = "Must use the BinaryClassifier.BuildingBlock to return an implemented and trained classification model as BinaryClassifier.")
    @SpecAssertion(section = "4.2.1", id = "421-D2")
    public void testCreateBinaryClassifier() throws ClassifierCreationException {
        ServiceProvider serviceProvider = ServiceProvider.current();
        ClassifierService classifierService = serviceProvider.getClassifierService();
        assertNotNull(classifierService);
        classifierService.createBinaryClassifier(config.getSpamBinaryClassificationBuildingBlock());
    }

}
