package com.company.project.service.impl;

import com.company.project.dao.Ade4Mapper;
import com.company.project.model.Ade4;
import com.company.project.service.Ade4Service;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/05/15.
 */
@Service
@Transactional
public class Ade4ServiceImpl extends AbstractService<Ade4> implements Ade4Service {
    @Resource
    private Ade4Mapper ade4Mapper;

}
