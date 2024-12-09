package ca.uqam.info.solanum.inf2050.f24halma.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Tests for the Field record. No extension required.
 *
 * @author Maximilian Schiedermeier
 */
public class FieldTest {


  /**
   * Creates two mutually referencing fields, one at (0,0), one at (1,1).
   *
   * @return array with the two test fields.
   */
  private Field[] createTestFields() {
    Field testField1 = new Field(0, 0);
    Field testField2 = new Field(1, 1);
    return new Field[] {testField1, testField2};
  }

  @Test
  public void verifyFieldCreation() {

    // Testing basic data set upon field creation
    Field[] testFields = createTestFields();

    // Test visual positions
    Assert.assertEquals(
        "Sample fields do not carry position they are supposed to (Visual horizontal position)", 0,
        testFields[0].x());
    Assert.assertEquals(
        "Sample fields do not carry position they are supposed to (Visual vertical position)", 0,
        testFields[0].y());
    Assert.assertEquals(
        "Sample fields do not carry position they are supposed to (Visual horizontal position)", 1,
        testFields[1].x());
    Assert.assertEquals(
        "Sample fields do not carry position they are supposed to (Visual vertical position)", 1,
        testFields[1].y());
  }


  /**
   * Verify if separate field objects with identical semantic are considered equal.
   */
  @Test
  public void verifyFieldEquality() {

    // Testing basic data set upon field creation
    Field[] testFields = createTestFields();

    // Test if objects equal themselves
    Assert.assertEquals("Sample field is not equal to itself", testFields[0], testFields[0]);
    Assert.assertEquals("Sample field is not equal to itself", testFields[1], testFields[1]);
    Assert.assertNotSame("Sample field should be different from other field", testFields[0],
        testFields[1]);

  }

  /**
   * Verify if sorting of fields produces determinist order.
   */
  @Test
  public void testFieldPairwiseOrder() {

    Field testField01 = new Field(0, 0);
    Field testField02 = new Field(1, 0);
    Assert.assertEquals("Sorting not correct", testField01.compareTo(testField02), -1);

    Field testField03 = new Field(0, 1);
    Assert.assertEquals("Sorting not correct", testField01.compareTo(testField03), -1);


  }

  /**
   * Verify if sorting of entire field sets produces determinist order.
   */
  @Test
  public void testFieldCollectionOrder() {

    // Creates two lists with identical elements but non-identical order.
    List<Field> sortedFields = new LinkedList<>();
    List<Field> shuffledFields = new LinkedList<>();
    for (int y = 0; y < 10; y++) {
      for (int x = 0; x < 10; x++) {
        sortedFields.add(new Field(x, y));
        shuffledFields.add(new Field(x, y));
      }
    }
    Collections.shuffle(shuffledFields);

    // Verifies the lists are identical after sorting
    Collections.sort(shuffledFields);
    Assert.assertEquals("Sorting of fields does not provides list of fields in ascending order",
        sortedFields, shuffledFields);
    System.out.println(sortedFields);
  }
}
