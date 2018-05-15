package com.company.project.service.impl;

import com.company.project.dao.Aa10Mapper;
import com.company.project.model.Aa10;
import com.company.project.service.Aa10Service;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/05/10.
 */
@Service
@Transactional
public class Aa10ServiceImpl extends AbstractService<Aa10> implements Aa10Service {
    @Resource
    private Aa10Mapper aa10Mapper;

}
