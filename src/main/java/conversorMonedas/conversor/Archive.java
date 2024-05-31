package conversorMonedas.conversor;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Archive {

    private List<String> stringList = new ArrayList<>();

    public void readFile(){

        File file = new File("list.dat");

        if (file.exists()){

            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                stringList = (List<String>) ois.readObject();

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void writteFile(){

        try {
            FileOutputStream fos = new FileOutputStream("list.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(stringList);
            oos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addList(String s){
        LocalDateTime ldt = LocalDateTime.now();
        DecimalFormat df = new DecimalFormat("##");

        String dateTime = " " + ldt.getDayOfMonth() + "/"
                + ldt.getDayOfMonth() + "/" + ldt.getYear() + " hour: "
                + ldt.getHour() + ":" + df.format(ldt.getMinute()) + ":" + ldt.getSecond();

        stringList.add(0, s + dateTime);
        if (stringList.size() > 5)stringList.remove(stringList.size() - 1);
    }
    public List<String> getStringList() {
        return stringList;
    }
}