package com.company.project.service.impl;

import com.company.project.dao.Ade8Mapper;
import com.company.project.model.Ade8;
import com.company.project.service.Ade8Service;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/05/15.
 */
@Service
@Transactional
public class Ade8ServiceImpl extends AbstractService<Ade8> implements Ade8Service {
    @Resource
    private Ade8Mapper ade8Mapper;

}
