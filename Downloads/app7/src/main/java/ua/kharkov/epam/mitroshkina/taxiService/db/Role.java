package ua.kharkov.epam.mitroshkina.taxiService.db;

import ua.kharkov.epam.mitroshkina.taxiService.db.entity.User;

/**
 * Role entity.
 */

public enum Role {
	ADMIN, CLIENT;
	
	public static Role getRole(User user) {
		int roleId = user.getRole_id();
		return Role.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
