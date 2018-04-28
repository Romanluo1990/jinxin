package roman.extramoney.jinxin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import roman.extramoney.jinxin.model.common.Identify;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "other_loans")
public class OtherLoans implements Identify{
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
     * 联系人姓名
     */
    @Column(name = "contact_name")
    @ApiModelProperty(value = "联系人姓名",example = "张三")
    private String contactName;

    /**
     * 联系人电话
     */
    @Column(name = "contact_phone")
    @ApiModelProperty(value = "联系人电话",example = "18888888888")
    private String contactPhone;

    /**
     * 借贷类型（1:国土抵押加急 2:国土注册加急 3:国土过户j加急 4:公正加急 5:做流水 6:做主体 7:跨行过桥 8:抵押 9:淘宝拍卖）
     */
    @Column(name = "loans_type")
    @ApiModelProperty(value = "借贷类型（1:国土抵押加急 2:国土注册加急 3:国土过户j加急 4:公正加急 5:做流水 6:做主体 7:跨行过桥 8:抵押 9:淘宝拍卖）",example = "1")
    private Integer loansType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",example = "无")
    private String note;

    /**
     * 状态（0:待审核 1:审核通过 2:审核不通过）
     */
    @ApiModelProperty(value = "状态（0:待审核 1:审核通过 2:审核不通过）",example = "0")
    private Integer status;

    /**
     * 审核信息
     */
    @ApiModelProperty(value = "审核信息",example = "审核通过")
    private String statusMessage;

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
     * 附件（回执）
     */
    @ApiModelProperty(value = "附件",example = "http://image/a.pic,http://image/b.pic")
    private String attachments;

}