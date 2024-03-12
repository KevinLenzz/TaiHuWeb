package com.dvweb4th.springprj.dao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Food {
    @Insert("insert into food_reviews(time,name,content,rate,foodnum) values(#{time},#{name},#{content},#{rate},#{foodnum})")
    public void insert(OneReview review);

    @Select("select * from food_reviews where foodnum=#{fn}")
    public List<OneReview> select(int fn);
}
