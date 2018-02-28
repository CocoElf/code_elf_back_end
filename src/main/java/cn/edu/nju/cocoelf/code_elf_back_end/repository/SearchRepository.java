package cn.edu.nju.cocoelf.code_elf_back_end.repository;

import cn.edu.nju.cocoelf.code_elf_back_end.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {

    List<Search> findBySearchDateBefore(Date date);

    List<Search> findBySearchDateAfter(Date date);

    List<Search> findBySearchDateBetween(Date fromDate, Date toDate);

    Integer countBySearchDateBetween(Date fromDate, Date toDate);
}
