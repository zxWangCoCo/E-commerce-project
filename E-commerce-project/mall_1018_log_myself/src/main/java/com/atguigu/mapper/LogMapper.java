package com.atguigu.mapper;

import org.apache.ibatis.annotations.Param;

public interface LogMapper {

	void saveLogMessage(@Param("massage")String massage);

}
