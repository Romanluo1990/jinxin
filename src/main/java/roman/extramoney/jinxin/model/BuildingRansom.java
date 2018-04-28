package roman.extramoney.jinxin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import roman.extramoney.jinxin.model.common.Identify;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "building_ransom")
public class BuildingRansom implements Identify{
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
     * 职业
     */
    @ApiModelProperty(value = "职业",example = "老板")
    private String profession;

    /**
     * 楼盘
     */
    @ApiModelProperty(value = "楼盘",example = "万科1期")
    private String estate;

    /**
     * 贷款银行
     */
    @Column(name = "lending_bank")
    @ApiModelProperty(value = "贷款银行",example = "工商银行")
    private String lendingBank;

    /**
     * 资金缺口
     */
    @ApiModelProperty(value = "资金缺口",example = "100")
    private BigDecimal amount;

    /**
     * 使用天数
     */
    @ApiModelProperty(value = "使用天数",example = "100")
    private Integer days;

    /**
     * 其他说明
     */
    @ApiModelProperty(value = "其他说明",example = "无")
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
     * 附件
     */
    @ApiModelProperty(value = "附件",example = "http://image/a.pic,http://image/b.pic")
    private String attachments;

}