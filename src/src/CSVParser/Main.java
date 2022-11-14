package CSVParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CSVParser parser = new CSVParser("basicprogramming_2.csv");
        List<Student> students = parser.getStudents();
        var st = students.get(0);

        System.out.println();
        System.out.println(st.getInfo());

        System.out.println();
        System.out.println(st.getFinalScores());

        System.out.println();
        System.out.println(st.getResultModules());
    }
}