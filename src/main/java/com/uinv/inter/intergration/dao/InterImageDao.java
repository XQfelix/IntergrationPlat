package com.uinv.inter.intergration.dao;

import com.uinv.inter.intergration.pojo.InterDoc;
import com.uinv.inter.intergration.pojo.InterImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InterImageDao  extends JpaRepository<InterImage, String>, JpaSpecificationExecutor {
    InterImage findByInterId(String param);
}
