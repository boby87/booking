package cm.ftg.bookingImage.entity;

public class UploadResponse {
    private String fileName;
    private String message;

    public UploadResponse(String fileName, String message) {
        this.fileName = fileName;
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public String getMessage() {
        return message;
    }
}
