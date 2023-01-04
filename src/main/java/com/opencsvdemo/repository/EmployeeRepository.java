package com.opencsvdemo.repository;

import com.opencsvdemo.model.Employee;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository extends CassandraRepository<Employee, Long> {


    @AllowFiltering
    @Transactional
//    @Query("UPDATE emp USING TTL 120 SET emp_name = :employee.getEmpName(),emp_email = :employee.getEmpEmailId()," +
//            "emp_skill = :employee.getEmpSkillSet() WHERE emp_id = :employee.getEmpId()")
//    void updateEmployee(@Param("employee")Employee employee);
        @Query("UPDATE emp USING TTL 120 SET emp_name = :empName,emp_email = :empEmail,emp_skill = :empSkill WHERE emp_id = :empId")
       void updateEmployee(@Param("empId") Long empId,@Param("empName") String empName,@Param("empEmail") String empEmail,@Param("empSkill") String  empSkill);
}

