package CSVParser;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    private List<Student> Students;

    private static String[] readLine(CSVReader reader) {
        try {
            return reader.readNext();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public CSVParser(String file) {
        try {
            FileReader reader = new FileReader(file);
            CSVReader csv = new CSVReaderBuilder(new FileReader(file)).build();

            this.Students = this.createStudents(reader);
            List<StudentModule> modules = new ArrayList<>();


            String[] curr_line = readLine(csv);
            String[] max_values_line;


            for (int i = 0; i < curr_line.length; i++) {
                if (!curr_line[i].equals("")) {
                    StudentModule md = new StudentModule(curr_line[i]);
                    for (int j = i + 1; j < curr_line.length; j++) {
                        if (!curr_line[j].equals("")) {
                            md.setIndexes(i, j);
                            break;
                        }
                        if (j + 1 == curr_line.length) {
                            md.setIndexes(i, j + 1);
                        }
                    }
                    modules.add(md);
                }
            }
            curr_line = readLine(csv);
            max_values_line = readLine(csv);
            for (StudentModule md : modules) {
                md.setFields(md.cutArray(curr_line));
                md.setMaxValues(md.cutArray(max_values_line));
            }

            for (Student st: this.Students) {
                curr_line = readLine(csv);
                for (StudentModule md : modules) {
                    try {
                        StudentModule module = (StudentModule) md.clone();
                        module.setStudentValues(module.cutArray(curr_line));
                        st.addModule(module);
                        md.clearValues();
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public List<Student> getStudents() {
        return this.Students;
    }

    private List<Student> createStudents(FileReader reader) {
        List<Student> students = new CsvToBeanBuilder<Student>(reader)
                .withType(Student.class).build().parse();
        return students.subList(3, students.size());
    }
}
