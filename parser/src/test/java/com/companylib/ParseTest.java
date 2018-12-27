package com.companylib;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertEquals;

public class ParseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParseTest.class);

    private Parse parse;
    private final String lineAllValues = "80000001 some product                                  00000567 00000123 00000456 00000789 00000002 00000003 NNNNNYNYN      some 123 quantity";
    private final String lineNegative = "80000001 some product                                  -0000567 00000123 00000456 00000789 00000002 00000003 NNNNNYNYN      some 123 quantity";
    private final String lineSplit = "80000001 some product                                  00000000 00000000 00000456 00000788 00000002 00000003 NNNNNYNYN      some 123 quantity";
    private final String lineNotSplit = "80000001 some product                                  00000567 00000123 00000000 00000000 00000000 00000000 NNNNNYNYN      some 123 quantity";
    private final String lineExtras = "80000001 some product                                  00000567 00000123 00000000 00000000 00000000 00000000 NNYNYNNNN      some 123 quantity";

    @Before
    public void setup() {
        parse = new Parse();
    }

    @Test
    public void allValuesTest() {
        ProductRecord productRecord = parse.parseLine(lineAllValues);

        assertEquals(productRecord.getId(), 80000001);
        assertEquals(productRecord.getDescription(), "some product");
        assertEquals(productRecord.getPriceRegularSingular(), (Double)5.67);
        assertEquals(productRecord.getPricePromoSingular(), (Double)1.23);
        assertEquals(productRecord.getPriceRegularSplit(), (Double)4.56);
        assertEquals(productRecord.getPricePromoSplit(), (Double)7.89);
        assertEquals(productRecord.getPriceRegularX(), (Double)2.);
        assertEquals(productRecord.getPricePromoX(), (Double)3.);

        assertEquals(productRecord.getFlags(), "NNNNNYNYN");
        assertEquals(productRecord.getProductSize(), "some 123 quantity");
    }


    @Test
    public void lineTest_Negative() {
        ProductRecord productRecord = parse.parseLine(lineNegative);
        assertEquals((Double)(-5.67), productRecord.getPriceRegularSingular());

    }

    @Test
    public void lineTest_Split() {
        ProductRecord productRecord = parse.parseLine(lineSplit);
        assertEquals(productRecord.getPriceRegularCalculated(), (Double)2.28);
        assertEquals(productRecord.getPricePromoCalculated(), (Double)2.63);
    }

    @Test
    public void lineTest_NotSplit() {
        ProductRecord productRecord = parse.parseLine(lineNotSplit);
        assertEquals(productRecord.getPriceRegularCalculated(), (Double)5.67);
        assertEquals(productRecord.getPricePromoCalculated(), (Double)1.23);
    }

    @Test
    public void lineTest_Extras() {
        ProductRecord productRecord = parse.parseLine(lineExtras);
        assertEquals(productRecord.getUnits(), "Pound");
        assertEquals(productRecord.getTaxRate(), (Double)0.07775);
    }


    @Test
    public void lineTest_NoExtras() {
        ProductRecord productRecord = parse.parseLine(lineAllValues);
        assertEquals(productRecord.getUnits(), "Each");
        assertEquals(productRecord.getTaxRate(), (Double)0.0);
    }

}
