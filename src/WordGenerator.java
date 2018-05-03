import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Mina_Yousry on 01/05/2018.
 */
public class WordGenerator implements Generator {
    ArrayList<String> words = new ArrayList<>(1000);
    Random random;
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
}
