package com.baymin.scaffold.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by baymin on 18-07-08.
 * 技能等级
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Restroom:厕所类")
public class RestRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restRoomId;
    @ApiModelProperty(value = "厕所名称",example = "碧海蓝天5星级公厕")
    private String restRoomName;
    @ApiModelProperty(value = "公厕ip",example = "ip")
    private String ip;
    @ApiModelProperty(value = "图片路径",example = "",notes = "notes")
    private String img="https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/map/pic/item/a9d3fd1f4134970a4f3e0c1398cad1c8a7865db8.jpg";
    @ApiModelProperty(value = "备注",example = "浙江省 宁波市 鄞州区",notes = "notes")
    private String region;
    @ApiModelProperty(value = "详细地址",example = "和邦大厦公厕",notes = "notes")
    private String address;
    @ApiModelProperty(value = "责任保洁",example = "马化腾")
    private String cleaner;
    @ApiModelProperty(value = "备注",example = "国内一流",notes = "notes")
    private String remark;
    @ApiModelProperty(value = "状态",example = "0：厕所关闭|1：厕所对外开放")
    private Integer status;
    @ApiModelProperty(value = "经度",example = "经度")
    private Float longitude;
    @ApiModelProperty(value = "纬度",example = "纬度")
    private Float latitude;
    @ApiModelProperty(value = "人流术",example = "100")
    private Integer peopleNum=0;
    //@Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createTime = new Date();


    private Date updateTime = new Date();

    @OneToMany(mappedBy = "restRoom",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    @ApiModelProperty(value = "厕所里摄像头")
    private List<DeviceCamera> deviceCameras= new ArrayList<>();


    public void addDeviceCamera(DeviceCamera comment) {
        deviceCameras.add(comment);
        comment.setRestRoom(this);
    }

    public void removeDeviceCamera(DeviceCamera comment) {
        deviceCameras.remove(comment);
        comment.setRestRoom(null);
    }


}
