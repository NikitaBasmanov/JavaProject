package CSVParser;

import java.util.Arrays;
import java.util.Hashtable;


class StudentModule implements Cloneable{
    private final String Name;
    private Hashtable<String, int[]> Values;
    private String[] Fields;
    private String[] MaxValues;
    private int StartIndex;
    private int EndIndex;   // End index is exclusive, and actual end field will be EndIndex-1

    public String[] cutArray(String[] arr) {
        return Arrays.copyOfRange(arr, this.StartIndex, this.EndIndex);
    }

    public void setIndexes(int start, int end) {
        this.StartIndex = start;
        this.EndIndex = end;
    }

    public void setFields(String[] arr) {
        this.Fields = arr;
    }

    public void setMaxValues(String[] arr) {
        this.MaxValues = arr;
    }

    public void setStudentValues(String[] line) {
        this.Values = new Hashtable<>();
        for (int i = 0; i < line.length; i++) {
            int[] arr = new int[2];
            arr[0] = Integer.parseInt(this.MaxValues[i]);
            arr[1] = Integer.parseInt(line[i]);
            this.Values.put(this.Fields[i], arr);
        }
    }

    public void clearValues() {
        this.Values = null;
    }

    public String getModuleResults() {
        String buff = "";
        buff += String.format(" Активности: %d из %d\n", this.Values.get("Акт")[1], this.Values.get("Акт")[0]);
        try {
            buff += String.format(" Упражнения: %d из %d\n", this.Values.get("Упр")[1], this.Values.get("Упр")[0]);
        } catch (Exception ex) {
            buff += String.format(" Упражнения: %d из %d\n", 0, 0);
        }
        try {
            buff += String.format(" Домашние работы: %d из %d\n", this.Values.get("ДЗ")[1], this.Values.get("ДЗ")[0]);
        } catch (Exception ex) {
            buff += String.format(" Домашние работы: %d из %d\n", 0, 0);
        }
        buff += String.format(" Сем: %d из %d\n", this.Values.get("Сем")[1], this.Values.get("Сем")[0]);

        return buff;
    }

    public String getName() {
        return this.Name + "\n";
    }

    StudentModule(String name){
        this.Name = name;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}