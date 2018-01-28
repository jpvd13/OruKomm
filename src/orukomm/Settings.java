package orukomm;

import java.util.EnumSet;
import orukomm.data.entities.User.PermissionFlag;

public class Settings {
	
	// The different user roles.
	public static final EnumSet<PermissionFlag> LOGGED_OUT_ROLE = EnumSet.of(PermissionFlag.NONE); // 1.
	public static final EnumSet<PermissionFlag> USER_ROLE = EnumSet.of(PermissionFlag.USER); // 2.
	public static final EnumSet<PermissionFlag> ADMIN_ROLE = EnumSet.of(PermissionFlag.USER, PermissionFlag.ADMIN); // 6.
	public static final EnumSet<PermissionFlag> SUPERADMIN_ROLE = EnumSet.of(PermissionFlag.USER, PermissionFlag.ADMIN, PermissionFlag.SUPERADMIN); // 14.
}