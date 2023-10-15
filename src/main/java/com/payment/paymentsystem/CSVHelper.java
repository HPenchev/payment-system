package com.payment.paymentsystem;

import com.payment.paymentsystem.data.MerchantStatus;
import com.payment.paymentsystem.data.dto.UserDTO;
import com.payment.paymentsystem.data.dto.UserRole;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;

public class CSVHelper {

    public static Collection<UserDTO> csvToUsers(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.builder()
                             .setHeader()
                             .setSkipHeaderRecord(true)
                             .setIgnoreHeaderCase(true)
                             .setTrim(true)
                             .build())
             ) {

            Collection<UserDTO> users = new HashSet<>();

            for (CSVRecord csvRecord : csvParser.getRecords()) {
                String description = csvRecord.get("Description");

                users.add(new UserDTO(
                        csvRecord.get("name"),
                        csvRecord.get("email"),
                        description == null || description.length() == 0 ? null : description,
                        MerchantStatus.fromString(csvRecord.get("status")),
                        UserRole.fromString(csvRecord.get("UserRole"))
                ));
            }

            return users;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
