package quickml.supervised.classifier.tree.treeConfig;

import com.google.common.collect.Lists;
import quickml.data.InstanceWithAttributesMap;
import quickml.supervised.classifier.*;
import quickml.supervised.classifier.tree.decisionTree.tree.BranchType;
import quickml.supervised.classifier.tree.decisionTree.tree.InitializedTreeConfig;
import quickml.supervised.classifier.tree.decisionTree.tree.TermStatistics;
import quickml.supervised.classifier.tree.decisionTree.tree.nodes.branchFinders.BranchFinder;
import quickml.supervised.classifier.tree.decisionTree.tree.nodes.branchFinders.BranchFinderBuilder;
import quickml.supervised.classifier.tree.decisionTree.tree.nodes.branchFinders.TermStatsAndOperations;

import java.util.List;
import java.util.Set;

/**
 * Created by alexanderhawk on 3/22/15.
 */
public abstract  class TreeConfigInitializer<L, I extends InstanceWithAttributesMap<L>, TS extends TermStatsAndOperations<TS>, D extends DataProperties> {

    public InitializedTreeConfig<TS, D> createTreeConfig(List<I> instances, TreeConfig<TS, D> fcb) {
        D dataProperties = getDataProperties(instances, fcb.getBranchTypes());
        List<BranchFinder<TS>> initializedBranchFinders = initializeBranchFinders(fcb, dataProperties);
        return new InitializedTreeConfig<TS, D>(fcb.getTerminationConditions(), fcb.getScorer(), initializedBranchFinders,
                fcb.getLeafBuilder(), fcb.getBagging(), dataProperties);
    }

    private List<BranchFinder<TS>> initializeBranchFinders(TreeConfig<TS, D> fcb, D dataProperties) {
        List<BranchFinder<TS>> initializedBranchFinders = Lists.newArrayList();
        for (BranchFinderBuilder<TS, D> branchFinderBuilder : fcb.getBranchFinderBuilders()) {
            branchFinderBuilder.setScorer(fcb.getScorer());
            branchFinderBuilder.setTerminationConditions(fcb.getTerminationConditions());
            initializedBranchFinders.add(branchFinderBuilder.buildBranchFinder(dataProperties));
        }
        return initializedBranchFinders;

    }

    protected abstract D getDataProperties(List<I> instances, Set<BranchType> branchTypes);

    public abstract TreeConfigInitializer<L, I, TS, D> copy();
}

