/* MailConstants 1.0 01/29/2017 */
package com.softserve.edu.schedule.service.implementation.mailsenders;

/**
 * An class to storage mail senders attributes.
 *
 * @version 1.0 29 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public final class MailConstants {

    /**
     * Meeting cancelled message subject.
     */
    public static final String MEETING_CANCELLED_MESSAGE_SUBJECT = "meetingCanceled.messageSubject";

    /**
     * Meeting cancelled message subject.
     */
    public static final String MEETING_CANCELLED_MESSAGE_GROUP = "meetingCanceled.messageGroup";

    /**
     * Default message from attribute.
     */
    public static final String DEFAULT_MESSAGE_FROM_ADDRESS = "${mail.from}";

    /**
     * Default velocity template encoding.
     */
    public static final String DEFAULT_MESSAGE_ENCODING = "UTF-8";

    /**
     * Meeting cancelled message template for EN locale location.
     */
    public static final String MEETING_CANCELLED_TEMPLATE = "meetingCancelledDueRoomUnavailability";

    /**
     * Meeting cancelled message template for EN locale location.
     */
    public static final String MEETING_CANCELLED_BY_SUBJECT_TEMPLATE = "meetingCanceledDueSubjectDelete";

    /**
     * Meeting cancelled message template for EN locale location.
     */
    public static final String MEETING_CANCELLED_BY_GROUP_TEMPLATE = "meetingCanceledDueGroupDelete";

    /**
     * Model name for meeting cancelled template.
     */
    public static final String MEETING_MODEL_NAME = "meeting";

    /**
     * Model name for user.
     */
    public static final String USER_MODEL_NAME = "user";

    /**
     * Registration message subject.
     */
    public static final String REGISTRATION_MESSAGE_SUBJECT = "userRegistrated.messageSubject";

    /**
     * Registration message template.
     */
    public static final String USER_REGISTRATED_TEMPLATE = "userRegistrated";

    /**
     * Password restore template.
     */
    public static final String RESTORE_TEMPLATE = "userRestorePassword";

    /**
     * Password restore message subject.
     */
    public static final String RESTORE_PASSWORD_MESSAGE_SUBJECT = "userRestorePassword.messageSubject";

    /**
     * Password model variable.
     */
    public static final String PASSWORD = "password";

    /**
     * Name of a usergroup model.
     */
    public static final String USERGROUP_MODEL_NAME = "usergroup";

    /**
     * Name of a group user.
     */
    public static final String USERGROUP_USER = "groupUser";

    /**
     * Name of a meeting curator.
     */
    public static final String MEETING_GROUP_CURATOR = "meetingCurator";

    /**
     * Name of a meeting owner.
     */
    public static final String MEETING_OWNER = "owner";

    /**
     * Name of a group curator.
     */
    public static final String USERGROUP_CURATOR = "groupCurator";

    /**
     * UserGroup deleted message.
     */
    public static final String USERGROUP_DELETED_MESSAGE = "groupDeleted.messageUserGroup";

    /**
     * Subject deleted message.
     */
    public static final String SUBJECT_DELETED_MESSAGE = "subjectDeleted.messageTutor";

    /**
     * Meeting deleted message.
     */
    public static final String MEETING_DELETED_MESSAGE = "meetingDeleted.messageMeetingOwnerAndCurators";

    /**
     * Meeting changed status (from APPROVED to DISAPPROVED or NOT_APPROVED)
     * message.
     */
    public static final String MEETING_CHANGEDSTATUS_MESSAGE = "meetingChangedStatus.messageToMeetingOwnerAndCurators";

    /**
     * UserGroup deleted message template for EN locale.
     */
    public static final String USERFROUP_DELETED_TEMPLATE = "userGroupDeleted";

    /**
     * Meeting deleted message template for locale.
     */
    public static final String MEETING_DELETED_TEMPLATE = "MeetingDelete";

    /**
     * Meeting changed status message template for locale.
     */
    public static final String MEETING_CHANGESTATUS_TEMPLATE = "MeetingChangedStatusInfo";

    /**
     * Location delete message subject.
     */
    public static final String LOCATION_DELETE_MESSAGE = "locationDelete.messageSubject";

    /**
     * Location delete message template for EN locale location.
     */
    public static final String LOCATION_DELETE_TEMPLATE = "infoLocationDelete";
    /**
     * Model name for moderator location delete message template.
     */
    public static final String MODERATOR_MODEL_NAME = "moderator";
    /**
     * Model name for location delete message template.
     */
    public static final String LOCATION_MODEL_NAME = "location";
    /**
     * Model name for location delete message template.
     */
    public static final String SUBJECT_MODEL_NAME = "subject";
    /**
     * Model name for location delete message template.
     */
    public static final String SUBJECT_TUTOR_MODEL_NAME = "tutor";
    /**
     * Subject deleted message template for EN locale.
     */
    public static final String SUBJECT_DELETED_TEMPLATE = "subjectDeleted";

    /**
     * Private constructor to prevent instance creation.
     */
    private MailConstants() {
    }

}
