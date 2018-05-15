package com.company.project.service.impl;

import com.company.project.dao.Ac11Mapper;
import com.company.project.model.Ac11;
import com.company.project.service.Ac11Service;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/05/15.
 */
@Service
@Transactional
public class Ac11ServiceImpl extends AbstractService<Ac11> implements Ac11Service {
    @Resource
    private Ac11Mapper ac11Mapper;

}
