package roman.extramoney.jinxin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import roman.extramoney.jinxin.model.common.Identify;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Data
public class Bridge implements Identify{
    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     *
     * @description 用户ID
     */
    @Column(name = "account_id")
    @ApiModelProperty(value = "用户ID",example = "1")
    private Long accountId;

    /**
     * 借款主体
     */
    @ApiModelProperty(value = "借款主体",example = "xxx公司")
    private String borrower;

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
     * 资金缺口
     */
    @ApiModelProperty(value = "资金缺口",example = "100",dataType = "double")
    private BigDecimal amount;

    /**
     * 预约用款日
     */
    @Column(name = "expected_account_time")
    @ApiModelProperty(value = "预约用款日",example = "2018-01-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expectedAccountTime;

    /**
     * 最早用款日
     */
    @Column(name = "earliest_account_time")
    @ApiModelProperty(value = "最早用款日",example = "2018-01-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date earliestAccountTime;

    /**
     * 最迟用款日
     */
    @Column(name = "latest_account_time")
    @ApiModelProperty(value = "最迟用款日",example = "2018-12-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date latestAccountTime;

    /**
     * 用款天数
     */
    @ApiModelProperty(value = "用款天数",example = "100")
    private Integer days;


    @Column(name = "planned_repayment_time")
    @ApiModelProperty(value = "计划还款日",example = "2018-12-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date plannedRepaymentTime;

    /**
     * 是否合同期内
     */
    @Column(name = "is_during_contract")
    @ApiModelProperty(value = "是否合同期内",example = "true")
    private Boolean isDuringContract;

    /**
     * 状态（0:待审核 1:审核通过 2:审核不通过）
     */
    @ApiModelProperty(value = "状态（0:待审核 1:审核通过 2:审核不通)",example = "0")
    private Integer status;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间",example = "2018-12-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String creator;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间",example = "2018-12-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updator;

}