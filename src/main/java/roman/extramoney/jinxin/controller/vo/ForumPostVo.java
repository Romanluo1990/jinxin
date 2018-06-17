package roman.extramoney.jinxin.controller.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.ForumPost;
import roman.extramoney.jinxin.model.ForumReplyWithUser;

import java.util.Date;
import java.util.List;

@Data
public class ForumPostVo {

    @JsonIgnore
    private ForumPost forumPost;

    @JsonIgnore
    private Account account;

    private List<ForumReplyWithUser> forumReplys;

    public String getNickName() {
        return account.getNickName();
    }

    public String getImage() {
        return account.getImage();
    }

    public ForumPostVo(ForumPost forumPost) {
        this.forumPost = forumPost;
    }

    public Integer getTimes() {
        return forumPost.getTimes();
    }

    public String getAttachments() {
        return forumPost.getAttachments();
    }

    public String getContent() {
        return forumPost.getContent();
    }

    public String getUpdator() {
        return forumPost.getUpdator();
    }

    public Date getUpdateTime() {
        return forumPost.getUpdateTime();
    }

    public String getCreator() {
        return forumPost.getCreator();
    }

    public Date getCreateTime() {
        return forumPost.getCreateTime();
    }

    public String getSign() {
        return forumPost.getSign();
    }

    public String getTitle() {
        return forumPost.getTitle();
    }

    public Long getAccountId() {
        return forumPost.getAccountId();
    }

    public Long getId() {
        return forumPost.getId();
    }
}
