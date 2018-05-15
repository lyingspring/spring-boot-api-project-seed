package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.ServiceException;
import com.company.project.dao.HnsiAPIMapper;
import com.company.project.dao.PublicMapper;
import com.company.project.model.*;
import com.company.project.service.*;
import oracle.sql.DATE;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/hnsiapi")
public class HnsiAPIController {
    @Resource
    private HnsiAPIMapper hnsiAPIMapper;
    @Resource
    private PublicMapper publicMapper;
    @Resource
    private Ac01Service ac01Service;
    @Resource
    private Ag02Service ag02Service;
    @Resource
    private Ade8Service ade8Service;
    @Resource
    private Ac11Service ac11Service;
    @Resource
    private Ade4Service ade4Service;
    @PostMapping("/insurancereg")
    public Result insurancereg(@RequestParam String cardno,@RequestParam String name) {
        Condition condition=new Condition(Ac01.class);
        condition.createCriteria().andCondition("aae135 ='"+cardno+"'");
        List<Ac01> ac01 = ac01Service.findByCondition(condition);
      if(ac01.size()==0){
          throw new ServiceException("社保系统中找不到该人员基本信息！");
      }else if(ac01.size()>1){
          throw new ServiceException("社保系统中该人员有多条信息，请前往社保中心做人员合并业务！");
      }else if(ac01.size()==1&&!ac01.get(0).getAac003().equals(name)){
          throw new ServiceException("社保系统中姓名"+ac01.get(0).getAac003()+" 传入姓名："+name);
      }
      Long cbflag=hnsiAPIMapper.countac02ac20(ac01.get(0));
      if(cbflag==0){
          throw new ServiceException("非续保人员不能录入！");
      }

        Condition conditionag02=new Condition(Ag02.class);
        conditionag02.createCriteria().andCondition("aac001 ='"+ac01.get(0).getAac001()+"'");
        List<Ag02> ag02 = ag02Service.findByCondition(conditionag02);
        if(ag02.size()==0){
            throw new ServiceException("该人员没有家庭户！请到乡镇街道或社保登记！");
        }


        Condition conditionade8=new Condition(Ade8.class);
        conditionade8.createCriteria().andCondition("aac001 ='"+ac01.get(0).getAac001()+"' and aae140='25' and aae003=to_char(sysdate,'yyyy')||'07'");
        List<Ade8> ade8l = ade8Service.findByCondition(conditionade8);
        if(ade8l.size()>0){
            throw new ServiceException("该人员今年有登记记录请核对！");
        }
        List<HashMap>list=hnsiAPIMapper.checkinfo(ac01.get(0));
        if(list.size()==0){
            throw new ServiceException("人员不符合缴费条件或已经缴费登记");
        }else if(list.get(0).get("AAC031").toString().equals("1")){
            throw new ServiceException("该人员参加职工医保或者土保大病，请核实");
        }

        Date date=publicMapper.queryDBdate();
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy");//yyyy-MM-dd HH:mm:ss
        String year = format0.format(date.getTime());//这个就是把时间戳经过处理得到期望格式的时间
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");
        String yearm = format1.format(date.getTime());//这个就是把时间戳经过处理得到期望格式的时间

        Ade8 ade8=new Ade8();
        ade8.setAac001(Long.valueOf(ac01.get(0).getAac001()));
        ade8.setAaz002(publicMapper.querySequenceByParam("SQ_AAZ002"));
        ade8.setAac003(name);
        ade8.setAae135(cardno);
        ade8.setAae001(Short.valueOf(year));
        ade8.setAae002(Integer.valueOf(yearm));
        ade8.setAae003(Integer.valueOf(year+"07"));
        ade8.setAae140("25");
        ade8.setAae036(date);
        ade8.setAae016("0");
        ade8Service.save(ade8);
     //Date date= publicMapper.queryDBdate();
        //System.out.println(date.getYear());
        return ResultGenerator.genSuccessResult(ade8);
    }

    @PostMapping("/queryinsurancereg")
    public Result queryinsurancereg(@RequestParam String cardno) {
        Condition condition=new Condition(Ac01.class);
        condition.createCriteria().andCondition("aae135 ='"+cardno+"'");
        List<Ac01> ac01 = ac01Service.findByCondition(condition);
        if(ac01.size()==0){
            throw new ServiceException("社保系统中找不到该人员基本信息！");
        }else if(ac01.size()>1){
            throw new ServiceException("社保系统中该人员有多条信息，请前往社保中心做人员合并业务！");
        }
        Condition conditionade8=new Condition(Ade8.class);
        conditionade8.createCriteria().andCondition("aac001 ='"+ac01.get(0).getAac001()+"' and aae140='25' and aae003=to_char(sysdate,'yyyy')||'07'");
        List<Ade8> ade8l = ade8Service.findByCondition(conditionade8);
        if(ade8l.size()==0){
            throw new ServiceException("找不到登记信息，请先缴费登记");
        }

        List<InsuranceRegDTO>list=new ArrayList<InsuranceRegDTO>();
        for(int i=0;i<ade8l.size();i++){
            InsuranceRegDTO dto=new InsuranceRegDTO();
            dto.setAaz002(ade8l.get(i).getAaz002().intValue());
            dto.setAac003(ade8l.get(i).getAac003());
            dto.setAae135(ade8l.get(i).getAae135());
            dto.setAae001(ade8l.get(i).getAae001().toString());
            dto.setAae140(publicMapper.getCodeValue("AAE140",ade8l.get(i).getAae140()));
            //dto.setAae016(ade8l.get(i).getAae016().equals("1")?"审核通过":ade8l.get(i).getAae016().equals("0")?"未审核":"审核不通过");
            dto.setAae016(ade8l.get(i).getAae016());
            dto.setAae013(ade8l.get(i).getAae013());
            dto.setEad184(ade8l.get(i).getEad184());
            SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//yyyy-MM-dd HH:mm:ss
            String aae036 = format0.format(ade8l.get(i).getAae036());//这个就是把时间戳经过处理得到期望格式的时间
            dto.setAae036(aae036);
            Ac11 ac11=new Ac11();
            try {
                ac11=   ac11Service.findBy("aac001",ade8l.get(i).getAac001());
            }catch (Exception e) {
                ac11 = null;
            }
            if (ac11!=null){
                dto.setAae010(ac11.getAae010());
                dto.setAae009(ac11.getAae009());
                dto.setAaa082(ac11.getAaa082());
            }

            if(ade8l.get(i).getAae016().equals("1")&&ade8l.get(i).getEad184().length()>0){
                Ade4 ade4=new Ade4();
                try {
                    ade4=   ade4Service.findBy("ead184",ade8l.get(i).getEad184());
                }catch (Exception e) {
                    ade4 = null;
                }
                if(ade4!=null){
                    String aae037 = format0.format(ade4.getAae036());
                    dto.setAae037(aae037);
                    dto.setAab033(ade4.getAab033());
                    dto.setEad186(ade4.getEad186().doubleValue());
                    dto.setEab009(publicMapper.getCodeValue("EAB009",ade4.getEab009()));
                    dto.setEab030(publicMapper.getCodeValue("EAB030",ade4.getEab030()));
                    dto.setEad189(publicMapper.getCodeValue("EAD189",ade4.getEad189()));
                    dto.setAae010(ade4.getAae010());
                    dto.setAaa082(ade4.getAaa082());

                }


            }


            list.add(dto);
        }

        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/setPayment")
    public Result setPayment(@RequestParam String paymentmethod,@RequestParam Long aaz002) {
        InsuranceRegDTO dto=new InsuranceRegDTO();
        Ade8 ade8=new Ade8();
        try {
            ade8=   ade8Service.findBy("aaz002",aaz002);
        }catch (Exception e) {
            ade8 = null;
        }
        if(ade8==null){
            throw new ServiceException("找不到登记信息，请先缴费登记");
        }
        if(!ade8.getAae016().equals("1")){
            throw new ServiceException("该条信息未审核，操作失败");
        }
        if(ade8.getEad184().length()==0||ade8.getEad184()==null){
            throw new ServiceException("找不到对应的审核信息");
        }
        Ade4 ade4=new Ade4();
        try {
            ade4=   ade4Service.findBy("ead184",ade8.getEad184());
        }catch (Exception e) {
            ade4 = null;
        }
        if(ade4==null){
            throw new ServiceException("找不到对应的审核信息2");
        }
        if(!ade4.getEad189().equals("0")){
            throw new ServiceException("该信息不是待扣款状态不能进行操作");
        }
        if(ade4.getAab033()!=null&&ade4.getAab033().equals("11")){
            throw new ServiceException("该人员是免缴人员不需要进行操作");
        }
        if((ade4.getAae010().length()==0||ade4.getAae010()==null)&&paymentmethod.equals("12")){
            throw new ServiceException("该人员没有银行信息，只能选择银行自主缴费");
        }
        if(!paymentmethod.equals("12")&&!paymentmethod.equals("13")){
            throw new ServiceException("传入的缴费方式代码有误");
        }


        Date date=publicMapper.queryDBdate();
        ade8.setAab033(paymentmethod);
        ade8.setAae037(date);
        ade8Service.update(ade8);
        ade4.setAab033(paymentmethod);
        ade4Service.update(ade4);

        dto.setAaz002(ade8.getAaz002().intValue());
        dto.setAac003(ade8.getAac003());
        dto.setAae135(ade8.getAae135());
        dto.setAae001(ade8.getAae001().toString());
        dto.setAae140(publicMapper.getCodeValue("AAE140",ade8.getAae140()));
        //dto.setAae016(ade8l.get(i).getAae016().equals("1")?"审核通过":ade8l.get(i).getAae016().equals("0")?"未审核":"审核不通过");
        dto.setAae016(ade8.getAae016());
        dto.setAae013(ade8.getAae013());
        dto.setEad184(ade8.getEad184());
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//yyyy-MM-dd HH:mm:ss
        String aae036 = format0.format(ade8.getAae036());//这个就是把时间戳经过处理得到期望格式的时间
        dto.setAae036(aae036);
        Ac11 ac11=new Ac11();
        try {
            ac11=   ac11Service.findBy("aac001",ade8.getAac001());
        }catch (Exception e) {
            ac11 = null;
        }
        if (ac11!=null){
            dto.setAae010(ac11.getAae010());
            dto.setAae009(ac11.getAae009());
            dto.setAaa082(ac11.getAaa082());
        }
        String aae037 = format0.format(ade4.getAae036());
        dto.setAae037(aae037);
        dto.setAab033(ade4.getAab033());
        dto.setEad186(ade4.getEad186().doubleValue());
        dto.setEab009(publicMapper.getCodeValue("EAB009",ade4.getEab009()));
        dto.setEab030(publicMapper.getCodeValue("EAB030",ade4.getEab030()));
        dto.setEad189(publicMapper.getCodeValue("EAD189",ade4.getEad189()));
        dto.setAae010(ade4.getAae010());
        dto.setAaa082(ade4.getAaa082());

        return ResultGenerator.genSuccessResult(dto);
    }


}
