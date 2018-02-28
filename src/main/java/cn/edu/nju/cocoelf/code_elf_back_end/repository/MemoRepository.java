package cn.edu.nju.cocoelf.code_elf_back_end.repository;

import cn.edu.nju.cocoelf.code_elf_back_end.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Integer> {

    Page<Memo> findByUser_Username(String username, Pageable pageable);
}
