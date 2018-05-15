package com.company.project.service.impl;

import com.company.project.dao.Ac20Mapper;
import com.company.project.model.Ac20;
import com.company.project.service.Ac20Service;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/05/14.
 */
@Service
@Transactional
public class Ac20ServiceImpl extends AbstractService<Ac20> implements Ac20Service {
    @Resource
    private Ac20Mapper ac20Mapper;

}
