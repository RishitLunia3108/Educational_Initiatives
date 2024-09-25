import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvDatabase {
    private String filePath;

    public CsvDatabase(String filePath) {
        this.filePath = filePath;
    }

    // Read CSV file and return list of room data
    public List<String[]> readCsv() {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    // Write list of room data to CSV file
    public void writeCsv(List<String[]> records) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] record : records) {
                bw.write(String.join(",", record));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update room data in the CSV
    public void updateRoom(int roomNumber, String[] updatedData) {
        List<String[]> records = readCsv();
        if (roomNumber >= 1 && roomNumber <= records.size()) {
            records.set(roomNumber - 1, updatedData);
            writeCsv(records);
        } else {
            System.out.println("Invalid room number for update.");
        }
    }
}
