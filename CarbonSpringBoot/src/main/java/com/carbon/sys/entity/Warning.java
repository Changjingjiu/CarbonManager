package com.carbon.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhang
 * @since 2023-08-10
 */
@TableName("t_warning")
public class Warning implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号，自增
     */
    @TableId(value = "warning_id", type = IdType.AUTO)
    private Integer warningId;

    /**
     * 监测点名称
     */
    private String monitorName;

    /**
     * 检测点地址
     */
    private String monitorAddr;

    /**
     * 实时功率
     */
    private String monitorPower;

    /**
     * 实时电压
     */
    private String currentVoltage;

    /**
     * 状态:0-异常 1-正常
     */
    private Integer warningStatus;

    /**
     * 上报时间，格式:YYY-MM-DD hh-mm-ss
     */
    private String reportTime;

    /**
     * 备注
     */
    private String description;

    public Integer getWarningId() {
        return warningId;
    }

    public void setWarningId(Integer warningId) {
        this.warningId = warningId;
    }
    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }
    public String getMonitorAddr() {
        return monitorAddr;
    }

    public void setMonitorAddr(String monitorAddr) {
        this.monitorAddr = monitorAddr;
    }
    public String getMonitorPower() {
        return monitorPower;
    }

    public void setMonitorPower(String monitorPower) {
        this.monitorPower = monitorPower;
    }
    public String getCurrentVoltage() {
        return currentVoltage;
    }

    public void setCurrentVoltage(String currentVoltage) {
        this.currentVoltage = currentVoltage;
    }
    public Integer getWarningStatus() {
        return warningStatus;
    }

    public void setWarningStatus(Integer warningStatus) {
        this.warningStatus = warningStatus;
    }
    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Warning{" +
            "warningId=" + warningId +
            ", monitorName=" + monitorName +
            ", monitorAddr=" + monitorAddr +
            ", monitorPower=" + monitorPower +
            ", currentVoltage=" + currentVoltage +
            ", warningStatus=" + warningStatus +
            ", reportTime=" + reportTime +
            ", description=" + description +
        "}";
    }
}
