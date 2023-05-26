package Parquet;

public class WeatherStationMessage {
    private int stationId;
    private int sequenceNumber;
    private String batteryStatus;
    private long timestamp;
    private int humidity;
    private int temperature;
    private int windSpeed;
    public WeatherStationMessage(){

    }
    public WeatherStationMessage(int stationId, int sequenceNumber, String batteryStatus, long timestamp, int humidity, int temperature, int windSpeed) {
        this.stationId = stationId;
        this.sequenceNumber = sequenceNumber;
        this.batteryStatus = batteryStatus;
        this.timestamp = timestamp;
        this.humidity = humidity;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }

    public int getStationId() {
        return stationId;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public String getBatteryStatus() {
        return batteryStatus;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public void setBatteryStatus(String batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    // Override the toString() method to print the message in a readable format
    @Override
    public String toString() {
        return "WeatherStationMessage{" +
                "stationId=" + stationId +
                ", sequenceNumber=" + sequenceNumber                + ", batteryStatus='" + batteryStatus + '\'' +
                ", timestamp=" + timestamp +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                '}';
    }
}