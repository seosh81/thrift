package simple;

import java.sql.SQLException;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class UserHandler implements UserService.Iface {

	@Autowired SqlMapClient sqlMap;
	
	@Override
	public void store(User user) throws TException {
		
		System.out.println("in store");
		try {
			sqlMap.update("userSvc.addUser", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("out store");
	}

	@Override
	public User get(String id) throws TException {
		User user = null;
		try {
			
			user = (User) sqlMap
					.queryForObject("userSvc.getUser", id);
			System.out.println("delete count " + sqlMap.update("userSvc.deleteUser", user.getId()));
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		System.out.println(user);
		return user;
	}

}
