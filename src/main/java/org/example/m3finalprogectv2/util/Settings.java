package org.example.m3finalprogectv2.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Settings {

    public static Map<String, Integer> counterUserName = new HashMap<>();

    public static Integer getRandom() {     // КАКОЙ СЕЙЧАС ГОД - index.jsp <h2>
        return new Random().nextInt(100);
    }

}
