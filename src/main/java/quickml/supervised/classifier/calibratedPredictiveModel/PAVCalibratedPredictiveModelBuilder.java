package quickml.supervised.classifier.calibratedPredictiveModel;

/**
 * Created by alexanderhawk on 3/10/14.
 * This class builds a calibrated predictive model, where the calibrator is implements the Pool Adjacent Violators algorithm.
 * It currently has some severe implementation problems and it's use is not recommended.
 */
/*
public class PAVCalibratedPredictiveModelBuilder implements UpdatablePredictiveModelBuilder<CalibratedPredictiveModel> {
    private int binsInCalibrator = 5;
    private final PredictiveModelBuilder predictiveModelBuilder;
    private final Serializable positiveClassification;

    public PAVCalibratedPredictiveModelBuilder() {
        this(new RandomForestBuilder());
    }

    public PAVCalibratedPredictiveModelBuilder(PredictiveModelBuilder<? extends PredictiveModel<Object>> predictiveModelBuilder) {
        this(predictiveModelBuilder, 1.0);
    }

    public PAVCalibratedPredictiveModelBuilder(PredictiveModelBuilder<? extends PredictiveModel<Object>> predictiveModelBuilder, Serializable positiveClassification) {
        this.predictiveModelBuilder = predictiveModelBuilder;
        this.positiveClassification = positiveClassification;
    }

    public PAVCalibratedPredictiveModelBuilder binsInCalibrator(Integer binsInCalibrator) {
        if (binsInCalibrator!=null) {
            this.binsInCalibrator = binsInCalibrator;
        }
        return this;
    }

    @Override
    public CalibratedPredictiveModel buildPredictiveModel(Iterable<? extends AbstractInstance> trainingData) {
        validateData(trainingData);
        PredictiveModel<Object> wrappedPredictiveModel = predictiveModelBuilder.buildPredictiveModel(trainingData);
        Calibrator calibrator = createCalibrator(wrappedPredictiveModel, trainingData);
        return new CalibratedPredictiveModel(wrappedPredictiveModel, calibrator, positiveClassification);
    }

    private void validateData(Iterable<? extends AbstractInstance> trainingData) {
        ClassificationCounter classificationCounter = ClassificationCounter.countAll(trainingData);
        Preconditions.checkArgument(classificationCounter.getCounts().keySet().size() <= 2, "trainingData must contain only 2 classifications, but it had %s", classificationCounter.getCounts().keySet().size());
        for(Serializable classification : classificationCounter.getCounts().keySet()) {
            Preconditions.checkArgument(classification instanceof Double || classification instanceof Integer, "trainingData must contain only Double or Integer classifications (found %s)", classification.getClass());
        }
    }

    @Override
    public PredictiveModelBuilder<CalibratedPredictiveModel> updatable(boolean updatable) {
        predictiveModelBuilder.updatable(updatable);
        return this;
    }

    @Override
    public void updatePredictiveModel(CalibratedPredictiveModel wrappedPredictiveModel, Iterable<? extends AbstractInstance> newData, List<? extends AbstractInstance> trainingData, boolean splitNodes) {
        if (predictiveModelBuilder instanceof UpdatablePredictiveModelBuilder) {
            validateData(newData);
            updateCalibrator(wrappedPredictiveModel, newData);
            ((UpdatablePredictiveModelBuilder)predictiveModelBuilder).updatePredictiveModel(wrappedPredictiveModel.wrappedPredictiveModel, newData, trainingData, splitNodes);
        } else {
            throw new RuntimeException("Cannot update predictive model without UpdatablePredictiveModelBuilder");
        }
    }

    @Override
    public void stripData(CalibratedPredictiveModel wrappedPredictiveModel) {
        if (predictiveModelBuilder instanceof UpdatablePredictiveModelBuilder) {
            ((UpdatablePredictiveModelBuilder) predictiveModelBuilder).stripData(wrappedPredictiveModel.wrappedPredictiveModel);
        } else {
            throw new RuntimeException("Cannot strip data without UpdatablePredictiveModelBuilder");
        }
    }

    @Override
    public void setID(Serializable id) {
        predictiveModelBuilder.setID(id);
    }

    private void updateCalibrator(PredictiveModel<Object> wrappedPredictiveModel, Iterable<? extends AbstractInstance> trainingInstances) {
        List<PoolAdjacentViolatorsModel.Observation> mobservations = getObservations(wrappedPredictiveModel, trainingInstances);

        PoolAdjacentViolatorsModel calibrator = (PoolAdjacentViolatorsModel)((CalibratedPredictiveModel)wrappedPredictiveModel).calibrator;
        for(PoolAdjacentViolatorsModel.Observation observation : mobservations) {
            calibrator.addObservation(observation);
        }
    }


    private Calibrator createCalibrator(PredictiveModel<Object> wrappedPredictiveModel, Iterable<? extends AbstractInstance> trainingInstances) {
        List<PoolAdjacentViolatorsModel.Observation> mobservations = getObservations(wrappedPredictiveModel, trainingInstances);
        return new PoolAdjacentViolatorsModel(mobservations, Math.max(1, Iterables.size(trainingInstances)/binsInCalibrator));
    }

    protected List<PoolAdjacentViolatorsModel.Observation> getObservations(PredictiveModel<Object> wrappedPredictiveModel, Iterable<? extends AbstractInstance> trainingInstances) {
        List<PoolAdjacentViolatorsModel.Observation> mobservations = Lists.<PoolAdjacentViolatorsModel.Observation>newArrayList();
        double prediction = 0;
        double groundTruth = 0;
        PoolAdjacentViolatorsModel.Observation observation;
        for(AbstractInstance instance : trainingInstances)  {
            groundTruth = ((Number)(instance.getLabel())).doubleValue();
            prediction = wrappedPredictiveModel.getProbability(instance.getAttributes(), positiveClassification);
            observation = new PoolAdjacentViolatorsModel.Observation(prediction, groundTruth, instance.getWeight());
            mobservations.add(observation);
        }
        return mobservations;
    }
}
*/