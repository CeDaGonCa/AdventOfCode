package AdventofCode2025;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AOCReader {
    public final List<String> inputStrings;
    public AOCReader(String filename) {
        inputStrings = readFile(filename);
    }
    public List<String> readFile (String filename) {
        List<String> list = new ArrayList<>();
        try {
            StringBuilder line = new StringBuilder();
            line.append("src/AdventofCode2025/").append(filename).append("/").append("input").append(filename).append(".txt");
            FileReader in = new FileReader(line.toString());
            BufferedReader br = new BufferedReader(in);
            while (br.ready()){
                list.add(br.readLine());
            }
            in.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }
}
