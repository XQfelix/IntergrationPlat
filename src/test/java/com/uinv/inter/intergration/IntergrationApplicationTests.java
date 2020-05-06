package com.uinv.inter.intergration;

import com.alibaba.fastjson.JSON;
import com.uinv.inter.intergration.dao.InterDao;
import com.uinv.inter.intergration.dao.InterImageDao;
import com.uinv.inter.intergration.dao.InterTagDao;
import com.uinv.inter.intergration.pojo.InterDoc;
import com.uinv.inter.intergration.pojo.InterTag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class IntergrationApplicationTests {

    @Autowired
    InterDao interDao;

    @Autowired
    InterImageDao interImageDao;

    @Autowired
    InterTagDao interTagDao;

    @Test
    void contextLoads() {
        interDao.findByInterId("a");
        System.out.println(JSON.toJSONString(interImageDao.findAll()));
    }

    @Test
    void test() {
        List<InterDoc> resultList = null;
        Specification querySpecifi = (Specification<InterDoc>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.like(root.get("interTag"), "%a%"));
            predicates.add(criteriaBuilder.like(root.get("interTag"), "%b%"));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "interUpdateTime");
        PageRequest pageRequest = PageRequest.of(0,100,sort);
        System.out.println(JSON.toJSONString(interDao.findAll(querySpecifi)));
    }



    @Test
    void sav() {
        System.out.println(interDao.countInterDocBy());
    }



}
