package jsr381.tck.spi;

import javax.visrec.ImageFactory;
import javax.visrec.ml.classification.BinaryClassifier;
import javax.visrec.ml.classification.ImageClassifier;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

public interface JSR381Configuration {

    ImageClassifier.BuildingBlock getABImageClassificationBuildingBlock(ImageClassifier.Builder builder);

    Map<String, Object> getABImageClassificationConfigMap(Map<String, Object> configMap);

    ImageClassifier.Builder getABImageClassificationBuilder(ImageClassifier.Builder builder);

    BinaryClassifier.BuildingBlock getSpamBinaryClassificationBuildingBlock();

    List<ImageFactory<?>> getImageFactories();

    static JSR381Configuration Load() {
        ServiceLoader<JSR381Configuration> configurationServiceLoader = ServiceLoader.load(JSR381Configuration.class);
        Optional<JSR381Configuration> configuration = configurationServiceLoader.findFirst();
        if (configuration.isEmpty())
            throw new IllegalStateException("No JSR381Configuration implementations are found");
        return configuration.get();
    }

}