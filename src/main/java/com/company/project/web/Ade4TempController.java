package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Ade4Temp;
import com.company.project.service.Ade4TempService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/05/09.
*/
@RestController
@RequestMapping("/ade4/temp")
public class Ade4TempController {
    @Resource
    private Ade4TempService ade4TempService;

    @PostMapping("/add")
    public Result add(Ade4Temp ade4Temp) {
        ade4TempService.save(ade4Temp);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        ade4TempService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Ade4Temp ade4Temp) {
        ade4TempService.update(ade4Temp);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Ade4Temp ade4Temp = ade4TempService.findById(id);
        return ResultGenerator.genSuccessResult(ade4Temp);
    }

    @PostMapping("/findBy")
    public Result findBy(@RequestParam String fieldName,@RequestParam String value) {
        Ade4Temp ade4Temp = ade4TempService.findBy(fieldName,value);
        return ResultGenerator.genSuccessResult(ade4Temp);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Ade4Temp> list = ade4TempService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
