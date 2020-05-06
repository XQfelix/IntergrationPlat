package com.uinv.inter.intergration.dao;

import com.uinv.inter.intergration.pojo.InterTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InterTagDao extends JpaRepository<InterTag, String>, JpaSpecificationExecutor {
}
