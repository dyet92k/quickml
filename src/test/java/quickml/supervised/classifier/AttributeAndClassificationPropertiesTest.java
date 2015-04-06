package quickml.supervised.classifier;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import quickml.data.AttributesMap;
import quickml.data.InstanceWithAttributesMap;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AttributeAndClassificationPropertiesTest extends TestCase {
    List<InstanceWithAttributesMap> binaryInstances;
    List<InstanceWithAttributesMap> nonBinaryInstances;

    @Before
    private void setup(){
        binaryInstances = Arrays.asList(
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 1.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 1.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 1.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 0.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 0.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 0.0)

                );

        nonBinaryInstances = Arrays.asList(
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 2.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 2.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 2.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 1.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 1.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 1.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 0.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 0.0),
                new InstanceWithAttributesMap(AttributesMap.newHashMap(), 0.0)

                );


    }
    @Test
    public void testBinaryClassificationProperties() throws Exception {
        setup();
        AttributeAndBinaryClassificationProperties attributeAndBinaryClassificationProperties = (AttributeAndBinaryClassificationProperties) AttributeAndClassificationProperties.setDataProperties(binaryInstances);
        org.junit.Assert.assertTrue(attributeAndBinaryClassificationProperties.classificationsAreBinary());


        org.junit.Assert.assertEquals((int) attributeAndBinaryClassificationProperties.majorityToMinorityRatio, 1);
        org.junit.Assert.assertTrue(attributeAndBinaryClassificationProperties.classificationsAreBinary());
        org.junit.Assert.assertTrue(attributeAndBinaryClassificationProperties.majorityClassification!= attributeAndBinaryClassificationProperties.minorityClassification);

    }
    @Test
    public void testGetClassificationsAndCounts() throws Exception {
        setup();
        AttributeAndClassificationProperties cp = AttributeAndClassificationProperties.setDataProperties(nonBinaryInstances);
        HashMap<Serializable, Long> classificationsAndCounts = cp.getClassificationsAndCounts();
        org.junit.Assert.assertEquals(classificationsAndCounts.size(), 3);
        org.junit.Assert.assertEquals(classificationsAndCounts.get(2.0), Long.valueOf(3L));
        org.junit.Assert.assertEquals(classificationsAndCounts.get(1.0), Long.valueOf(3L));
    }
}