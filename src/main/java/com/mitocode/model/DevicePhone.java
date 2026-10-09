package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DevicePhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long deviceId;
    @Column(length = 50, nullable = false)
    private String deviceName;
    @Column(length = 100, nullable = false)
    private String deviceModel;
    @Column(length = 70, nullable = false)
    private String deviceManufacturer;
    @Column(length = 50, nullable = false)
    private String deviceOsVersion;

}
