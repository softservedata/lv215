/* ControllerConst 1.0 01/29/2017 */
package com.softserve.edu.schedule.service.implementation.mailsenders;

/**
 * An interface to storage mail senders attributes.
 *
 * @version 1.0 29 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface MailConstants {

	/**
	 * Meeting cancelled message subject.
	 */
	String MEETING_CANCELLED_MESSAGE_SUBJECT = "meetingCanceled.messageSubject";

	/**
	 * Meeting cancelled message subject.
	 */
	String MEETING_CANCELLED_MESSAGE_GROUP = "meetingCanceled.messageGroup";

	/**
	 * Default message from attribute.
	 */
	String DEFAULT_MESSAGE_FROM_ADDRESS = "${mail.from}";

	/**
	 * Default velocity template encoding.
	 */
	String DEFAULT_MESSAGE_ENCODING = "UTF-8";

	/**
	 * Meeting cancelled message template for EN locale location.
	 */
	String MEETING_CANCELLED_TEMPLATE = "meetingCancelledDueRoomUnavailability";

	/**
	 * Meeting cancelled message template for EN locale location.
	 */
	String MEETING_CANCELLED_BY_SUBJECT_TEMPLATE = "meetingCanceledDueSubjectDelete";

	/**
	 * Meeting cancelled message template for EN locale location.
	 */
	String MEETING_CANCELLED_BY_GROUP_TEMPLATE = "meetingCanceledDueGroupDelete";

	/**
	 * Model name for meeting cancelled template.
	 */
	String MEETING_MODEL_NAME = "meeting";

	String USER_MODEL_NAME = "user";

	String REGISTRATION_MESSAGE_SUBJECT = "userRegistrated.messageSubject";

	String USER_REGISTRATED_TEMPLATE = "userRegistrated";

	/**
	 * Name of a usergroup model.
	 */
	String USERGROUP_MODEL_NAME = "usergroup";

	/**
	 * Name of a group user.
	 */
	String USERGROUP_USER = "groupUser";

	/**
	 * Name of a group curator.
	 */
	String USERGROUP_CURATOR = "groupCurator";

	/**
	 * UserGroup deleted message.
	 */
	String USERGROUP_DELETED_MESSAGE = "groupDeleted.messageUserGroup";

	/**
	 * UserGroup deleted message template for EN locale.
	 */
	String USERFROUP_DELETED_TEMPLATE = "userGroupDeleted";
}
