package com.softserve.edu.oms.data;

import java.util.List;

public final class UserRepository {

	private static volatile UserRepository instance = null;

	private UserRepository() {
	}

	public static UserRepository get() {
		if (instance == null) {
			synchronized (UserRepository.class) {
				if (instance == null) {
					instance = new UserRepository();
				}
			}
		}
		return instance;
	}
	
//	public IUser adminUser() {
//		return new User("iva", "ivanka", "horoshko", "qwerty",
//				"mail@gmail.com", "West", "Administrator");
//	}
	
	public IUser adminUser() {
        return new User("noneiva", "noneivanka", "nonehoroshko", "qwerty",
                "mail@gmail.com", "West", "Administrator");
    }
    

	public IUser customerUser() {
		return new User("login1", "firstName1", "lastName1", "qwerty",
				"mail@gmail.com", "East", "Customer");
	}

	public IUser merchandiserUser() {
		return new User("login2", "firstName2", "lastName2", "qwerty",
				"mail@gmail.com", "East", "Merchandiser");
	}

	public IUser supervisorUser() {
		return new User("login3", "firstName3", "lastName3", "qwerty",
				"mail@gmail.com", "East", "Supervisor");
	}

	public IUser invalidUser() {
		return new User("abcdqwd", "abcd123", "abcd123", "abcd1",
				"abcd@gmail.com", "East", "Administrator");
	}

	public IUser badMemoryUser() {
		return new User("BadMemoryUser", "Neo", "Matrix", "paSSworD",
				"hello@gmail.com", "East", "Supervisor");
	}
	
	public IUser someUser() {
        return new User("roman", "rrd", "rrd", "1234",
                "sasdasdad@fg.com", "West", "Administrator");
    }
	
	public IUser userForDelete() {
        return new User("andriy", "rrd", "rrd", "1234",
                "sasdasdad@fg.com", "West", "Administrator");
    }
    
	public IUser nonExistingUser() {
		return new User("hippo", "hippopotamus", "hippopotamus", "qwerty",
				"hippo@ui.com", "East", "Administrator");
	}
    public IUser newUser() {
        return new User("isComing", "New", "WorldOrder", "1234",
                "rdd@g.com", "West", "Administrator");
    }

	public IUser UserWithLongCredentials(){
		return new User(
				"qwertyuiopasdfghjklz",
				"qwertyuiopasdfghjklz",
				"qwertyuiopasdfghjklz",
				"211111111111112",
				"nonlocalparties@domain.com",
				"",
				""
		);
	}


	public List<IUser> getUsersFromExcelFile() {
		return new UserUtils("/users.xlsx", new ExcelUtils()).getAllUsers();
	}

	public List<IUser> getUsersFromDB() {
		return new UserUtils("/", new DBUtils()).getAllUsers();
	}



}
