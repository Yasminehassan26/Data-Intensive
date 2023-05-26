package Parquet;

import org.apache.avro.generic.GenericRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParquetInputHandler {
    private final HashMap<Integer, List<GenericRecord>> stationsRecords;
    private final int batchSize;
    private final ParquetHandler parquetHandler;

    public ParquetInputHandler(int size) {
        batchSize = size;
        stationsRecords = new HashMap<>();
        parquetHandler = new ParquetHandler();
    }


    public void messagesConsumer(WeatherStationMessage weatherStationMessage) throws IOException {
        GenericRecord record = parquetHandler.createRecord(weatherStationMessage);
        List<GenericRecord> temp;
        if (stationsRecords.containsKey(weatherStationMessage.getStationId())) {
            temp = stationsRecords.get(weatherStationMessage.getStationId());
        } else {
            temp = new ArrayList<>();

        }
        temp.add(record);
        stationsRecords.put(weatherStationMessage.getStationId(), temp);
        if(temp.size()>=batchSize){
            String path=parquetHandler.generateUniqueFilename();
            parquetHandler.writeToParquetFile(path,temp);
            temp.clear();
        }

    }

}
