package com.company.project.service.impl;

import com.company.project.dao.Ac01Mapper;
import com.company.project.model.Ac01;
import com.company.project.service.Ac01Service;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/05/03.
 */
@Service
@Transactional
public class Ac01ServiceImpl extends AbstractService<Ac01> implements Ac01Service {
    @Resource
    private Ac01Mapper ac01Mapper;

}
