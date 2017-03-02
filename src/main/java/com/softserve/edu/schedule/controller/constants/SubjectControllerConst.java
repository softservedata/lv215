/* LocationControllerConst 1.0 01/03/2017 */
package com.softserve.edu.schedule.controller.constants;

/**
 * A class to storage subject controller model attributes and mappings URL.
 *
 * @version 1.0 1 March 2017
 *
 * @author Ped'ko Volodymyr
 */
public final class SubjectControllerConst {

	/**
	 * Subject form model attribute name.
	 */
	public static final String SUBJECT_FORM_MODEL_ATTR = "subjectForm";

	/**
	 * Filter model attribute name.
	 */
	public static final String FILTER_MODEL_ATTR = "subjectFilter";

	/**
	 * File for subject attribute name.
	 */
	public static final String SUBJECT_FILES = "subjectFiles";

	/**
	 * Filter model attribute name.
	 */
	public static final String SUBJECT_PAGINATOR_MODEL_ATTR = "subjectPaginator";

	/**
	 * File model attribute name.
	 */
	public static final String SUBJECT_FILE_FORM = "fileForSubjectDTO";

	/**
	 * Subjects model attribute name.
	 */
	public static final String SUBJECTS_MODEL_ATTR = "subjects";

	/**
	 * Subject model attribute name.
	 */
	public static final String SUBJECT_MODEL_ATTR = "subject";

	/**
	 * Users model attribute name.
	 */
	public static final String USERS_MODEL_ATTR = "users";

	/**
	 * Subjects list URL.
	 */
	public static final String SUBJECTS_LIST_URL = "subjects/list";

	/**
	 * Subjects list redirect URL.
	 */
	public static final String SUBJECTS_REDIRECT_URL = "redirect:/subjects";

	/**
	 * Subject file delete redirect URL.
	 */
	public static final String SUBJECT_SHOW_URL = "redirect:/subjects/show/{id}";

	/**
	 * Create new Subject URL.
	 */
	public static final String SUBJECT_CREATE_URL = "subjects/create";

	/**
	 * Edit subject information URL.
	 */
	public static final String SUBJECTS_EDIT_URL = "subjects/edit";

	/**
	 * Show subject information URL.
	 */
	public static final String SUBJECTS_SHOW_URL = "subjects/show";

	/**
	 * Subjects mapping URL.
	 */
	public static final String SUBJECTS_MAPPING = "/subjects";

	/**
	 * Subject delete mapping URL.
	 */
	public static final String SUBJECT_DELETE_FILE_MAPPING = "/subjects/deleteFile/";

	/**
	 * Subject download mapping URL.
	 */
	public static final String SUBJECT_DOWNLOAD_FILE_MAPPING = "/subjects/downloadFile/";

	/**
	 * Subjects mapping URL.
	 */
	public static final String SUBJECTS_MAPPING_SHOW = "/subjects/show/";

	/**
	 * Delete subject mapping URL name.
	 */
	public static final String SUBJECT_DELETE_MAPPING = "/subjects/delete/";

	/**
	 * Create new subject mapping URL name.
	 */
	public static final String SUBJECT_CREATE_MAPPING = "/subjects/create";

	/**
	 * Edit subject information mapping URL name.
	 */
	public static final String SUBJECT_EDIT_MAPPING = "/subjects/edit/";

	/**
	 * Subject field id.
	 */
	public static final String SUBJECT_PATH_ID = "id";

	/**
	 * Subject field name.
	 */
	public static final String SUBJECT_PATH_NAME = "name";

	/**
	 * Subject field description.
	 */
	public static final String SUBJECT_PATH_DESCRIPTION = "description";

	/**
	 * Subject field lastName.
	 */
	public static final String SUBJECT_PATH_LASTNAME = "lastName";

	/**
	 * Subject field users.
	 */
	public static final String SUBJECT_PATH_USERS = "users";

	/**
	 * Subject field users.
	 */
	public static final String SUBJECT_PATH_USER_ID = "userId";

	/**
	 * Security permission.
	 */
	public static final String HAS_ANY_ROLE = "hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR')";

	/**
	 * Security permission.
	 */
	public static final String HAS_ANY_ROLE_EXEPT_USER = "hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR')";

	/**
	 * Acces for files download.
	 */
	public static final String ACCES_FILES = "image/jpeg, image/gif, application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document, "
	        + "application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-powerpoint, "
	        + "application/vnd.openxmlformats-officedocument.presentationml.presentation, application/pdf";

	/**
	 * Private constructor to prevent instance creation.
	 */
	private SubjectControllerConst() {
	}
}
