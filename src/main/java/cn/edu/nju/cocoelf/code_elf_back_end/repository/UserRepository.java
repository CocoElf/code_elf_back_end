package cn.edu.nju.cocoelf.code_elf_back_end.repository;

import cn.edu.nju.cocoelf.code_elf_back_end.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsernameAndPassword(String username, String password);
}
