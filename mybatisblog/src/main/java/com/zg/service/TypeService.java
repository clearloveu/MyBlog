package com.zg.service;

import com.zg.pojo.Type;

import java.util.List;


public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    List<Type> getAdminType();

    Type getTypeByName(String name);

    int updateType(Type type);

    int deleteType(Long id);


}
