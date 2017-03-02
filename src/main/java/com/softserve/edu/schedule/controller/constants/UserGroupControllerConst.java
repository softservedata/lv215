/* UserGroupControllerConst 1.0 01/03/2017 */
package com.softserve.edu.schedule.controller.constants;

/**
 * Class that stores all constants for UserGroup controller.
 *
 * @author Andrew Zhydenko
 *
 */
public final class UserGroupControllerConst {

	/**
	 * Filter attribute for groups.
	 */
	public static final String FILTER_MODEL_ATTR = "usergroupFilter";

	/**
	 * Paginator attribute for groups.
	 */
	public static final String USERGROUP_PAGINATOR_MODEL_ATTR = "usergroupPaginator";

	/**
	 * UserGroup model attribute name.
	 */
	public static final String USERGROUP_MODEL_ATTR = "userGroupForm";

	/**
	 * UserGroups model attribute name.
	 */
	public static final String USERGROUPS_MODEL_ATTR = "usergroups";

	/**
	 * UserGroup create mapping name.
	 */
	public static final String USERGROUP_CREATE_MAPPING = "/create";

	/**
	 * UserGroup delete mapping name.
	 */
	public static final String USERGROUP_DELETE_MAPPING = "/delete/{id}";

	/**
	 * UserGroups delete mapping name.
	 */
	public static final String USERGROUPS_DELETE_MAPPING = "/usergroups/delete/";

	/**
	 * UserGroup create url name.
	 */
	public static final String USERGROUP_CREATE_URL = "usergroups/create";

	/**
	 * UserGroup edit url name.
	 */
	public static final String USERGROUP_EDIT_URL = "usergroups/edit";

	/**
	 * UserGroup edit mapping name.
	 */
	public static final String USERGROUP_EDIT_MAPPING = "/edit/{id}";

	/**
	 * UserGroup redirect url name.
	 */
	public static final String USERGROUP_REDIRECT_URL = "redirect:/usergroups";

	/**
	 * UserGroup list url name.
	 */
	public static final String USERGROUP_LIST_URL = "usergroups/list";

	/**
	 * Mapping for searching.
	 */
	public static final String SEARCH_MODEL_ATTR = "search";

	/**
	 * Curators attribute.
	 */
	public static final String USERGROUP_CURATORS_ATTR = "curators";

	/**
	 * Curator attribute.
	 */
	public static final String USERGROUP_CURATOR_ATTR = "curator";

	/**
	 * All users attribute.
	 */
	public static final String USERGROUP_ALL_USERS_ATTR = "allUsers";

	/**
	 * Levels attribute.
	 */
	public static final String USERGROUP_LEVEL_ATTR = "levels";

	/**
	 * Mapping on a usergroups page.
	 */
	public static final String USERGROUP_MAPPING = "/usergroups";

	/**
	 * Path name.
	 */
	public static final String USERGROUP_PATH_NAME = "name";

	/**
	 * Path curator's lastname.
	 */
	public static final String USERGROUP_PATH_CURATOR_LASTNAME = "curator.lastName";

	/**
	 * UserGroup details mapping URL name.
	 */
	public static final String USERGROUP_SHOW_MAPPING = "/{id}";

	/**
	 * Path variable for usergroup id.
	 */
	public static final String PATH_VAR_ID = "id";

	/**
	 * Show group details URL name.
	 */
	public static final String USERGROUP_SHOW_URL = "usergroups/show";

	/**
	 * Group members attribute.
	 */
	public static final String USERGROUP_GROUP_MEMBERS = "groupMembers";

	/**
	 * Minimum description length.
	 */
	public static final int MIN_GROUP_DESCRIPTION_LENGTH = 15;

	/**
	 * Maximum description length.
	 */
	public static final int MAX_GROUP_DESCRIPTION_LENGTH = 100;

	/**
	 * Minimum group name length.
	 */
	public static final int MIN_GROUP_NAME_LENGTH = 5;

	/**
	 * Maximum group name length.
	 */
	public static final int MAX_GROUP_NAME_LENGTH = 20;

	/**
	 * Mapping to a profile from usergroups list.
	 */
	public static final String USERGROUP_USER_PROFILE_MAPPING = "/profile/";

}
