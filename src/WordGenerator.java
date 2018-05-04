import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Mina_Yousry on 01/05/2018.
 */
public class WordGenerator extends Thread implements Generator{
    ArrayList<String> words = new ArrayList<>(1000);
    Random random;
    int counter = 0;
    long sleepvalue = 10000;

    WordGenerator() throws IOException {
        random = new Random();
        BufferedReader in = new BufferedReader(new FileReader("1000Words.txt"));
        int count = 0;
        String word;
        while ((word = in.readLine()) != null)
        {
            words.add(word);
        }
        System.out.println(words.get(999));
    }
    @Override
    public String generateData() {
        String sent;
        sent = words.get( (random.nextInt(1000)));
        sent += " " + words.get((random.nextInt(1000)));
        sent += " " + words.get((random.nextInt(1000)));
        sent += " " + words.get((random.nextInt(1000)));
        sent += " " + words.get( (random.nextInt(1000))) + ".";
        return sent;
    }

    public void run(){
        boolean moredata = true;
        while (moredata) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("outdata.txt"));
                out.write(String.valueOf(counter++)+"\n");
//                out.write(counter++);
                out.append(generateData());
                out.close();
                System.out.println(counter-1);
                sleep((long)sleepvalue);
            } catch (IOException e) {
                moredata = false;
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
