package com.uinv.inter.intergration.dao;

import com.uinv.inter.intergration.pojo.InterDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterDao extends JpaRepository<InterDoc, String> , JpaSpecificationExecutor {
    InterDoc findByInterId(String param);
    int countInterDocBy();
}
