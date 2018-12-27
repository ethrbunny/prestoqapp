package com.companylib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse {
    private static final Logger LOGGER = LoggerFactory.getLogger(Parse.class);

    private Double convertCurrency(String source) {
        boolean negative = false;
        if(source.contains("-")) {
            negative = true;
            source = source.substring(1);
        }

        Double dd = Double.parseDouble(source);
        return (negative ? -1 : 1) * dd / 100;
    }

    /**
     * Take a single line of product information and return a ProductRecord object
     *
     * @param line Single line of product information
     * @return ProductRecord
     */
    public ProductRecord parseLine(String line) {
        Pattern pattern = Pattern.compile("^(\\d{8}) ([ 0-9A-Za-z\\-()]*) ([\\-0-9]{8}) ([\\-0-9]{8}) ([\\-0-9]{8}) ([\\-0-9]{8}) ([\\-0-9]{8}) ([\\-0-9]{8}) ([YN]*)(([A-Za-z0-9 ]*)?)$");
        Matcher matcher = pattern.matcher(line);
        ProductRecord productRecord = new ProductRecord();

        if (matcher.matches()) {
            productRecord.setId(Integer.valueOf(matcher.group(1).trim()));
            productRecord.setDescription(matcher.group(2).trim());
            productRecord.setPriceRegularSingular(convertCurrency(matcher.group(3)));
            productRecord.setPricePromoSingular(convertCurrency(matcher.group(4)));
            productRecord.setPriceRegularSplit(convertCurrency(matcher.group(5)));
            productRecord.setPricePromoSplit(convertCurrency(matcher.group(6)));
            productRecord.setPriceRegularX(Double.valueOf(matcher.group(7)));
            productRecord.setPricePromoX(Double.valueOf(matcher.group(8)));
            productRecord.setFlags(matcher.group(9));
            productRecord.setProductSize(matcher.group(10).trim());
        }

        // A bit of defensive coding...
        if(productRecord.getDescription() == null) {
            throw new RuntimeException("Invalid line found");
        }

        return productRecord;
    }
}
