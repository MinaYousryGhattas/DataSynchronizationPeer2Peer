import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class queuingModel {
    private Queue<String> queue = new LinkedList<>();
    File file = new File("data.txt");

    public queuingModel(){

    }

    public queuingModel(Queue<String> queue) {
        this.queue = queue;
    }

    public Queue<String> getQueue() {
        return queue;
    }

    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }

    public void addToQueue(String element){
        queue.add(element);
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public void saveToFile(){

        PrintWriter printWriter = null;
        try  {
            printWriter = new PrintWriter(new FileOutputStream(file, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 100; i++) {
            if(queue.isEmpty()){
                break;
            }

            printWriter.append(queue.poll() + "\n");
        }

        printWriter.close();
    }

    public void loadFromFile(){
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }
    }
}
