package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 查询replytime倒序，根据labelid查询，分页显示  （多表查询）
     */
    @Query("select p from Problem p where p.id in ( select pl.problemid from Pl pl where pl.labelid = ?1) order by p.replytime desc")
    public Page<Problem> findNewListByLabelId(String labelid, Pageable pageable);

    /**
     * 查询reply倒序，根据labelid查询，分页显示  （多表查询）
     */
    @Query("select p from Problem p where p.id in ( select pl.problemid from Pl pl where pl.labelid = ?1) order by p.reply desc")
    public Page<Problem> findHotListByLabelId(String labelid, Pageable pageable);

    /**
     * 查询reply = 0，根据labelid查询，分页显示  （多表查询）
     */
    @Query("select p from Problem p where p.id in ( select pl.problemid from Pl pl where pl.labelid = ?1) and p.reply = 0 order by p.createtime desc")
    public Page<Problem> findWaitListByLabelId(String labelid, Pageable pageable);
}
