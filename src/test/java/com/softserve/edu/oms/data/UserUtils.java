package com.softserve.edu.oms.data;

import java.util.ArrayList;
import java.util.List;

public final class UserUtils {
	private String filename;
	private IExternalData externalData;

	public UserUtils(String filename, IExternalData externalData) {
		this.filename = filename;
		this.externalData = externalData;
	}

	public List<IUser> getAllUsers() {
		return getAllUsers(this.getClass().getResource(filename).getPath().substring(1));
	}

	public List<IUser> getAllUsers(String absoluteFilePath) {
		List<IUser> users = new ArrayList<IUser>();
		for (List<String> row : externalData.getAllCells(absoluteFilePath, null)) {
			if (row.get(5).toLowerCase().contains("region")
					|| row.get(6).toLowerCase().contains("role")) {
				continue;
			}
			users.add(new User(row.get(0), row.get(1), row.get(2),
					row.get(3), row.get(4), row.get(5), row.get(6)));
		}
		return users;
	}

}
