package sample.Messages;

import java.io.Serializable;
import java.util.List;

public class Table1 implements Serializable {
    private List<String> data1;

    public Table1(List<String> data1) {
        this.data1 = data1;
    }

    public List<String> getData1() {
        return data1;
    }

}
