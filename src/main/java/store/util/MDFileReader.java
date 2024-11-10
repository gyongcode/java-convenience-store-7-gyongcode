package store.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MDFileReader {
    private final String FILEPATH;

    public MDFileReader(String filePath){
        validateFilePath(filePath);
        this.FILEPATH = filePath;
    }

    public List<String> getLines() throws IOException {
        BufferedReader br = getBufferedReader();
        String line;
        List<String> lines = new ArrayList<>();

        while((line = br.readLine()) != null){
            lines.add(line);
        }

        return lines;
    }

    private BufferedReader getBufferedReader() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
            br.readLine();
            return br;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("[ERROR] 파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            throw new IOException("[ERROR] 파일이 비어있습니다.");
        }
    }

    private void validateFilePath(String filePath){
        if(filePath == null || filePath == ""){
            throw new IllegalArgumentException("[ERROR] 파일 경로를 제대로 입력해주세요");
        }
    }
}
