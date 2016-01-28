package examples.neo4j.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import examples.neo4j.dao.UserMapper;
import examples.neo4j.domain.User;

@Component
public class Neo4JService {
	@Autowired
	private UserMapper userMapper;

	public Collection<User> getUser(String name) {
		return userMapper.getUsers(name);
	}

	public Collection<User> getAllUsers() {
		return userMapper.getUsers(null);
	}
}
