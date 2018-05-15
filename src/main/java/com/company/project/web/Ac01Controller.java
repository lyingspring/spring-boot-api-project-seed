package com.company.project.web;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.ServiceException;
import com.company.project.dao.Ac01Mapper;
import com.company.project.dao.PublicMapper;
import com.company.project.model.Aa10;
import com.company.project.model.Ac01;
import com.company.project.service.Ac01Service;
import com.company.project.service.impl.Ac01ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2018/05/03.
*/
@RestController
@RequestMapping("/ac01")
@PropertySource("classpath:application-dev.properties")////如果是application.properties，就不用写
public class Ac01Controller {
    @Resource
    private Ac01Service ac01Service;
    @Resource
    private PublicMapper publicMapper;

    @PostMapping("/add")
    public Result add(Ac01 ac01) {
        ac01Service.save(ac01);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        ac01Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public Result update(@RequestParam String name) {
        Ac01 ac01=new Ac01();
        ac01=ac01Service.findById(950713);
        ac01.setAac003("倪梦岚1");
        ac01Service.update(ac01);
        if(name.equals("1")) {
            throw new ServiceException("123");
        }
        ac01.setAac003("倪梦岚");
        ac01Service.update(ac01);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Ac01 ac01 = ac01Service.findById(id);
        return ResultGenerator.genSuccessResult(ac01);
    }
    @PostMapping("/findBy")
    public Result findBy(@RequestParam String fieldName,@RequestParam String value) {
        Ac01 ac01 = ac01Service.findBy(fieldName,value);
        return ResultGenerator.genSuccessResult(ac01);
    }



    @Value("${spring.datasource.url}")
    private String ms;
    @PostMapping("/findByCondition")
    public Result findByCondition(@RequestParam String value) {
    	Condition condition=new Condition(Ac01.class);
    	condition.createCriteria().andCondition("aae135 in('321028196307097433','330419196709085829')");
        List<Ac01> ac01 = ac01Service.findByCondition(condition);
        if(value.equals("123")) {
            HashMap<String, Long> paramMap = new HashMap<String, Long>();
            Long aac001=  publicMapper.querySequenceByParam("sq_aac001");
           // paramMap.put("aaa100","AAC004");
            paramMap.put("num",123l);
            ac01.get(0).setAac001(33212);
            Aa10 aa10=new Aa10();
            aa10.setAaa100("AAC004");
            aa10.setAaa102("1");
             publicMapper.callCodeview(aa10);

            throw new ServiceException(aac001.toString()+" "+paramMap.isEmpty());
        }
        return ResultGenerator.genSuccessResult(ac01);
    }
    

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Ac01> list = ac01Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Autowired
    private RestTemplate restTemplate;
    @PostMapping("/test_restTemplate")
    public Result test_restTemplate (){
       String pageInfo="";
        //post json数据
        JSONObject postData = new JSONObject();
        postData.put("value", "122");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String jsonPost = postData.toString();
        HttpEntity<String> entity = new HttpEntity<>(jsonPost, headers);
        JSONObject json = restTemplate.postForEntity("http://localhost:8080/ac01/findByCondition", entity, JSONObject.class).getBody();
        pageInfo=json.toJSONString();
        return ResultGenerator.genSuccessResult(pageInfo);
    }


}
