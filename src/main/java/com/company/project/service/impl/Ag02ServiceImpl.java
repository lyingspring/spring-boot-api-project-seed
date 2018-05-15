package com.company.project.service.impl;

import com.company.project.dao.Ag02Mapper;
import com.company.project.model.Ag02;
import com.company.project.service.Ag02Service;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/05/15.
 */
@Service
@Transactional
public class Ag02ServiceImpl extends AbstractService<Ag02> implements Ag02Service {
    @Resource
    private Ag02Mapper ag02Mapper;

}
