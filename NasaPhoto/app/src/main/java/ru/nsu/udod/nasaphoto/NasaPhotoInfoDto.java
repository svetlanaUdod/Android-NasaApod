package ru.nsu.udod.nasaphoto;

public class NasaPhotoInfoDto {
    private String date;
    private String title;
    private String explanation;
    private String copyright;
    private String url;
    private String media_type;

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getUrl() {
        return url;
    }
}
