package com.hls.service.impl;

import com.hls.dao.DictDao;
import com.hls.pojo.Dict;
import com.hls.service.DictService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/29 17:00
 * @content
 */
@Service
public class DictServiceImpl implements DictService {

    @Resource
    private DictDao dictDao;


    /**
     * 代码装换成中文
     *
     * @param code 代码
     * @param kind 类
     * @return {@link String}
     */
    @Override
    public String codeToValue(String code, String kind) {

        Example example = new Example(Dict.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code",code);
        criteria.andEqualTo("kind",kind);
        List<Dict> dicts = dictDao.selectByExample(example);
        if (dicts.size()>0){
          return   dicts.get(0).getValue();
        }
        return "";
    }
}
