package simple;

import java.sql.SQLException;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import simple.thrifted.User;
import simple.thrifted.UserService;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class UserHandler implements UserService.Iface {

	@Autowired SqlMapClient sqlMap;
	
	@Override
	public void store(User user) throws TException {
		try {
			sqlMap.update("userSvc.addUser", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User get(String id) throws TException {
		User user = null;
		try {
			user = (User) sqlMap
					.queryForObject("userSvc.getUser", id);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return user;
	}

}
