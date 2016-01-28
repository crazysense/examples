package examples.neo4j.dao;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;

import examples.neo4j.domain.User;

public interface UserMapper {
	Collection<User> getUsers(@Param("name") String name);
}
