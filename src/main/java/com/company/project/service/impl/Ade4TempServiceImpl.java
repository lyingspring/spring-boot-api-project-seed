package com.company.project.service.impl;

import com.company.project.dao.Ade4TempMapper;
import com.company.project.model.Ade4Temp;
import com.company.project.service.Ade4TempService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/05/09.
 */
@Service
@Transactional
public class Ade4TempServiceImpl extends AbstractService<Ade4Temp> implements Ade4TempService {
    @Resource
    private Ade4TempMapper ade4TempMapper;

}
