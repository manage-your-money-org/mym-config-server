package com.rkumar0206.mymconfigserver;

import com.rkumar0206.mymconfigserver.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RestController
@RequestMapping("/file/encrypt")
public class UtilController {

    @Autowired
    private EncryptionUtil encryptionUtil;

    @PostMapping
    public ResponseEntity encryptMySecretFile(@RequestBody String location) {

        StringBuilder stringBuilder = new StringBuilder();

        if (location != null && !location.isEmpty()) {

            try (FileReader fileReader = new FileReader(location)) {

                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line;

                while ((line = bufferedReader.readLine()) != null) {

                    String str = line.trim();

                    if (!str.isEmpty()) {

                        if (str.endsWith(":")) {
                            stringBuilder.append(str);
                            stringBuilder.append("\n");
                            continue;
                        }

                        if (checkIfEncryptionRequired(str)) {

                            String[] split = str.split("===");
                            stringBuilder
                                    .append("\t")
                                    .append(split[0].trim())
                                    .append(": ")
                                    .append(encryptionUtil.encrypt(split[1].trim()))
                                    .append("\n");
                        }
                    }
                }

            } catch (IOException e) {
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
        }

        return ResponseEntity.ok(stringBuilder.toString());
    }

    private boolean checkIfEncryptionRequired(String str) {

        return str.startsWith("username")
                || str.startsWith("password")
                || str.startsWith("secret")
                || str.startsWith("uri")
                || str.startsWith("issuer");

    }

}
