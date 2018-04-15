package roman.extramoney.jinxin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import roman.extramoney.jinxin.model.common.Identify;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "forum_post")
public class ForumPost implements Identify{
    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "account_id")
    @ApiModelProperty(value = "用户ID",example = "1")
    private Long accountId;

    /**
     * 主题
     */
    @ApiModelProperty(value = "主题",example = "可乐鸡翅做法")
    private String title;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签",example = "生活,美食")
    private String sign;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间",example = "2018-01-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间",example = "2018-01-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    private String updator;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容",example = "一瓶可乐烧鸡翅")
    private String content;


    /**
     * 附件
     */
    @ApiModelProperty(value = "附件",example = "http://image/a.pic,http://image/b.pic")
    private String attachments;

    @ApiModelProperty(value = "热度",example = "1")
    private Integer times;

}