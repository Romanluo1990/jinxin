package roman.extramoney.jinxin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import roman.extramoney.jinxin.model.common.Identify;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
public class ForumReplyWithUser {

    private String userName;

    @JsonIgnore
    private ForumReply forumReply;

    public ForumReplyWithUser(String userName, ForumReply forumReply) {
        this.userName = userName;
        this.forumReply = forumReply;
    }

    public Long getId() {
        return forumReply.getId();
    }

    public String getContent() {
        return forumReply.getContent();
    }

    public String getUserName() {
        return userName;
    }
}