package org.example.finalprojectm3;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class Settings {

    public static Map<String, Integer> counterName= new HashMap<>();
    public static String inputName = "playerGame";

    public static String getParameter(HttpServletRequest request, String inputName) {
        if (isNull(inputName))
            throw new IllegalArgumentException("Name cannot be null");
        return request.getParameter(inputName);
    }

}
