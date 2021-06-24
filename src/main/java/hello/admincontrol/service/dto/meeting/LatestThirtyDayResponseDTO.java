package hello.admincontrol.service.dto.meeting;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.beans.BeanUtils;

import hello.admincontrol.entity.Meeting;


/**
 * 用于30天内会议的请求
 */
public class LatestThirtyDayResponseDTO
{
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date date;
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    private String title;
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    private int mark;
    public int getMark() {
        return this.mark;
    }
    public void setMark(int mark) {
        this.mark = mark;
    }

    public LatestThirtyDayResponseDTO(Meeting mt) {
        BeanUtils.copyProperties(mt, this);
    }
}

