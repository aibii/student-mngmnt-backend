package com.example.demo.class_group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassGroupRepository extends JpaRepository<ClassGroup, Long> {

    @Query(value = "SELECT cg.id, cg.group_name, s.student_id, s.first_name, s.last_name, sg.start_date " +
                   "FROM class_groups cg " +
                   "JOIN student_group sg ON cg.id = sg.group_id " +
                   "JOIN students s ON s.student_id = sg.student_id", nativeQuery = true)
    List<Object[]> findAllGroupsWithStudentsNative();
}
