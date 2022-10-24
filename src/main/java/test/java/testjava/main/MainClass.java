package test.java.testjava.main;

import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        List<String> lstString = ImmutableList.of("Ali", "Naqvi", "Syed");
        Collections.sort(lstString, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println(lstString);
    }
}
