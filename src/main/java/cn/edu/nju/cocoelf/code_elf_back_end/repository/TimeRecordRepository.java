package cn.edu.nju.cocoelf.code_elf_back_end.repository;

import cn.edu.nju.cocoelf.code_elf_back_end.entity.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord, Long> {
    List<TimeRecord> findByUser_Username(String username);
}
