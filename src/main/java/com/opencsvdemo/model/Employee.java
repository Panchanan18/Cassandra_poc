package com.opencsvdemo.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(value = "emp")
public class Employee {


    @CsvBindByName(column = "Employee Id")
    @PrimaryKey(value = "emp_id")
    private Long empId;

    @CsvBindByName(column = "Employee Name")
    @Column(value = "emp_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String empName;

    @CsvBindByName(column = "Employee Email")
    @Column(value = "emp_email")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String empMailId;

    @CsvBindByName(column = "Employee SkillSet")
    @Column(value = "emp_skill")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String empSkillSet;
}
