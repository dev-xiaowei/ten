package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 标签Service
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /**
     * 查询一个
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    /**
     * 添加
     */
    public void add(Label label){
        //获取分布式id
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /**
     * 修改
     */
    public void update(Label label){ // label对象必须存在id
        labelDao.save(label);
    }

    /**
     * 删除
     */
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    public List<Label> findSearch(Map searchMap) {
        //创建Specification对象，封装查询条件
        Specification<Label> spec = createSpecification(searchMap);
        List<Label> list = labelDao.findAll(spec);
        return list;
    }

    private Specification<Label> createSpecification(Map searchMap) {
        //使用匿名内部类提供Specification接口的实现类对象
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //preList: 保存所有查询条件
                List<Predicate> preList = new ArrayList<>();

                //标签名称
                if(searchMap.get("labelname")!=null && !searchMap.get("labelname").equals("")){
                    preList.add(criteriaBuilder.like(root.get("labelname").as(String.class), "%" + searchMap.get("labelname") + "%"));
                }
                if(searchMap.get("state")!=null && !searchMap.get("state").equals("")){
                    preList.add(criteriaBuilder.equal(root.get("state").as(String.class), searchMap.get("state")));
                }
                if(searchMap.get("recommend")!= null && !searchMap.get("recommend").equals("")){
                    preList.add(criteriaBuilder.equal(root.get("recommend").as(String.class), searchMap.get("recommend")));
                }
                //where labelname like '%xxx%' and  state =  'xxx'  and recommend =  'xxx'
                //preList.toArray(): 把preList集合里面的每个元素，放入一个新的Object[]数组里面，返回Object[]数组
                //preList.toArray(preArray): 把preList集合的每个元素取出，放入指定preArray数组里面
                Predicate[] preArray = new Predicate[preList.size()];
                return criteriaBuilder.and(preList.toArray(preArray));
            }
        };
    }

    /**
     * 条件+分页
     */
    public Page<Label> findSearch(int page, int size, Map searchMap){
        Specification<Label> spec = createSpecification(searchMap);
        //spring datajpa 的page从0开始
        return labelDao.findAll(spec, PageRequest.of(page-1,size));
    }
}
