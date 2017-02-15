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
    
    String RESTORE_TEMPLATE = "userRestorePassword";
    
    String RESTORE_PASSWORD_MESSAGE_SUBJECT = "userRestorePassword.messageSubject";

    /**
     * Name of a usergroup model.
     */
    String USERGROUP_MODEL_NAME = "usergroup";

    /**
     * Name of a group user.
     */
    String USERGROUP_USER = "groupUser";

    /**
     * Name of a meeting curator.
     */
    String MEETING_GROUP_CURATOR = "meetingCurator";

    /**
     * Name of a meeting owner.
     */
    String MEETING_OWNER = "owner";

    /**
     * Name of a group curator.
     */
    String USERGROUP_CURATOR = "groupCurator";

    /**
     * UserGroup deleted message.
     */
    String USERGROUP_DELETED_MESSAGE = "groupDeleted.messageUserGroup";
    
    /**
     * Subject deleted message.
     */
    String SUBJECT_DELETED_MESSAGE = "subjectDeleted.messageTutor";

    /**
     * Meeting deleted message.
     */
    String MEETING_DELETED_MESSAGE = "meetingDeleted.messageMeetingOwnerAndCurators";

    /**
     * Meeting changed status (from APPROVED to DISAPPROVED or NOT_APPROVED)
     * message.
     */
    String MEETING_CHANGEDSTATUS_MESSAGE = "meetingChangedStatus.messageToMeetingOwnerAndCurators";

    /**
     * UserGroup deleted message template for EN locale.
     */
    String USERFROUP_DELETED_TEMPLATE = "userGroupDeleted";

    /**
     * Meeting deleted message template for locale.
     */
    String MEETING_DELETED_TEMPLATE = "MeetingDelete";

    /**
     * Meeting changed status message template for locale.
     */
    String MEETING_CHANGESTATUS_TEMPLATE = "MeetingChangedStatusInfo";

    /**
     * Location delete message subject.
     */
    String LOCATION_DELETE_MESSAGE = "locationDelete.messageSubject";

    /**
     * Location delete message template for EN locale location.
     */
    String LOCATION_DELETE_TEMPLATE = "infoLocationDelete";
    /**
     * Model name for moderator location delete message template.
     */
    String MODERATOR_MODEL_NAME = "moderator";
    /**
     * Model name for location delete message template.
     */
    String LOCATION_MODEL_NAME = "location";
    /**
     * Model name for location delete message template.
     */
    String SUBJECT_MODEL_NAME="subject";
    /**
     * Model name for location delete message template.
     */
    String SUBJECT_TUTOR_MODEL_NAME="tutor";   
    /**
     * Subject deleted message template for EN locale.
     */
    String SUBJECT_DELETED_TEMPLATE = "subjectDeleted";

}
