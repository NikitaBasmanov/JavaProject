package CSVParser;
import com.opencsv.bean.CsvBindByPosition;

import java.util.ArrayList;
import java.util.List;

public class Student {

    @CsvBindByPosition(position = 0)
    private String Name;

    @CsvBindByPosition(position = 1)
    private String Group;

    private final List<StudentModule> Modules = new ArrayList<>();

    public void addModule(StudentModule md) {
        this.Modules.add(md);
    }

    public String getInfo() {
        String buff = "";
        buff += this.Name + "\n";
        buff += String.format("Группа: %s", this.Group);
        return buff;
    }

    public String getFinalScores() {
        String buff;
        buff = this.Modules.get(0).getName();
        buff += this.Modules.get(0).getModuleResults();
        return buff;
    }

    public String getResultModules() {
        StringBuilder buff = new StringBuilder();
        for (int i = 1; i < this.Modules.size(); i++) {
            buff.append(this.Modules.get(i).getName());
            buff.append(this.Modules.get(i).getModuleResults());
            buff.append("\n");
        }
        return buff.toString();
    }
}
