package com.company.project.service.impl;

import com.company.project.dao.Ac02Mapper;
import com.company.project.model.Ac02;
import com.company.project.service.Ac02Service;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/05/14.
 */
@Service
@Transactional
public class Ac02ServiceImpl extends AbstractService<Ac02> implements Ac02Service {
    @Resource
    private Ac02Mapper ac02Mapper;

}
