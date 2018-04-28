package roman.extramoney.jinxin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import roman.extramoney.jinxin.model.common.Identify;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "building_property")
public class BuildingProperty implements Identify{
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
     * 联系人姓名(与业主关系)
     */
    @Column(name = "contact_name")
    @ApiModelProperty(value = "联系人姓名(与业主关系)",example = "本人")
    private String contactName;

    /**
     * 联系人电话
     */
    @Column(name = "contact_phone")
    @ApiModelProperty(value = "联系人电话",example = "18888888888")
    private String contactPhone;

    /**
     * 业主
     */
    @Column(name = "property_ower")
    @ApiModelProperty(value = "业主",example = "张三")
    private String propertyOwer;

    /**
     * 物业类型（1:厂房 2:土地 3:商品房 4:商铺）
     */
    @Column(name = "property_type")
    @ApiModelProperty(value = "物业类型（1:厂房 2:土地 3:商品房 4:商铺）",example = "1")
    private Integer propertyType;

    /**
     * 物业性质（1:商业 2:商住 3:住宅 4:划拨）
     */
    @Column(name = "property_nature")
    @ApiModelProperty(value = "物业性质（1:商业 2:商住 3:住宅 4:划拨）",example = "1")
    private Integer propertyNature;

    /**
     * 土地面积
     */
    @Column(name = "land_area")
    @ApiModelProperty(value = "土地面积",example = "100")
    private BigDecimal landArea;

    /**
     * 建筑面积
     */
    @Column(name = "building_area")
    @ApiModelProperty(value = "建筑面积",example = "200")
    private BigDecimal buildingArea;

    /**
     * 性质扩展（1:一般物业 2:贷款物业 3:已逾期 4:已查封 5:已判决 6:已在淘宝拍卖）
     */
    @Column(name = "property_nature_ext")
    @ApiModelProperty(value = "性质扩展（1:一般物业 2:贷款物业 3:已逾期 4:已查封 5:已判决 6:已在淘宝拍卖）",example = "1")
    private Integer propertyNatureExt;

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