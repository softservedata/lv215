package com.softserve.edu.schedule.data;

public final class UserRepository {

	private UserRepository() {
	}

	public static IUser getAdmin() {
		return new User("obutyter@gmail.com",
				"Qwerty!1", "Олександр Бутитер");
	}

	public static IUser getInvalid() {
		return new User("qwerty", "qwerty", "invalid");
	}

}
