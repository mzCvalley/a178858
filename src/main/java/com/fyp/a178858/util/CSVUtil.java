package com.fyp.a178858.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;

import com.fyp.a178858.model.response.SalaryResponse;
import com.opencsv.CSVWriter;
import org.apache.commons.lang3.ObjectUtils;


public class CSVUtil {
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static ByteArrayInputStream salaryResponsesToCSV(List<SalaryResponse> salaryResponses) throws IOException {
        StringWriter stringWriter = new StringWriter();
        try (CSVWriter csvWriter = new CSVWriter(stringWriter, DEFAULT_SEPARATOR, DEFAULT_QUOTE, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            String[] headerRecord = {"ID", "Name", "Position", "OT Duration", "OT Pay", "Total Pay"};
            csvWriter.writeNext(headerRecord);

            salaryResponses.stream().map(salaryResponse -> new String[]{
                    String.valueOf(salaryResponse.getId()),
                    salaryResponse.getName(),
                    salaryResponse.getPosition(),
                    ObjectUtils.isEmpty(salaryResponse.getTotalOtDuration()) ?
                            BigDecimal.ZERO.toString() :
                            salaryResponse.getTotalOtDuration().toString(),
                    ObjectUtils.isEmpty(salaryResponse.getTotalOtPay()) ?
                            BigDecimal.ZERO.toString() :
                            salaryResponse.getTotalOtPay().toString(),
                    ObjectUtils.isEmpty(salaryResponse.getTotalPay()) ?
                            BigDecimal.ZERO.toString() :
                            salaryResponse.getTotalPay().toString()
            }).forEach(csvWriter::writeNext);
        }
        return new ByteArrayInputStream(stringWriter.toString().getBytes());
    }
}
