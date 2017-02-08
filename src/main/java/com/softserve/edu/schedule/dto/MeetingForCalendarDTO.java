/* MeetingForCalendarDTO 1.0 02/04/2017 */
package com.softserve.edu.schedule.dto;

public class MeetingForCalendarDTO {

    /**
     * Id of meeting in calendar.
     */
    private String id;

    /**
     * Title of meeting in calendar.
     */
    private String title;

    /**
     * Start time of meeting in calendar.
     */
    private String start;

    /**
     * End time of meeting in calendar.
     */
    private String end;

    /**
     * URL of meeting in calendar.
     */
    private String url;

    /**
     * Color of meeting in calendar.
     */
    private String color;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * @return the end
     */
    public String getEnd() {
        return end;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param start
     *            the start to set
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * @param end
     *            the end to set
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @param color
     *            the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

}
