/* 
 * Meetings controller class.
 */
package com.softserve.edu.schedule.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.softserve.edu.schedule.controller.constants.MeetingControllerConst;
import com.softserve.edu.schedule.controller.docviews.ExcelViewMeetingHistory;
import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.dto.filter.MeetingFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.service.MeetingHistoryService;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.RoomService;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.UserGroupService;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.editor.DateEditor;
import com.softserve.edu.schedule.service.implementation.editor.MeetingDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.RoomDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.SubjectDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.TimeEditor;
import com.softserve.edu.schedule.service.implementation.editor.UserDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.UserGroupDTOEditor;

/**
 * A controller class of meetings pages.
 *
 * @version 1.0 5 January 2017
 *
 * @author Bohdan Melnyk
 *
 */
@RequestMapping(ControllerConst.MeetingControllerConst.MEETINGS_URL)
@Controller
@SessionAttributes({ ControllerConst.MeetingControllerConst.FILTER_MODEL_ATTR,
        ControllerConst.MeetingControllerConst.MEETING_PAGINATOR_MODEL_ATTR })
public class MeetingController
        {

    /**
     * MeetingService example to provide meetings list to the model.
     */
    @Autowired
    private MeetingService meetingService;

    /**
     * SubjectService example to provide subject list to the model.
     */
    @Autowired
    private SubjectService subjectService;

    /**
     * UserService example to provide users list to the model.
     */
    @Autowired
    private UserService userService;

    /**
     * RoomService example to provide rooms list to the model.
     */
    @Autowired
    private RoomService roomService;

    /**
     * UserGroupService example to provide UserGroups list to the model.
     */
    @Autowired
    private UserGroupService userGroupService;

    /**
     * SubjectDTOEditor example to provide conversions from form select fields
     * to DTO.
     */
    @Autowired
    private SubjectDTOEditor subjectDTOEditor;

    /**
     * DateEditor example to provide conversions from form select fields to date
     * format.
     */
    @Autowired
    private DateEditor dateEditor;

    /**
     * TimeEditor example to provide conversions from form select fields to time
     * format.
     */
    @Autowired
    private TimeEditor timeEditor;

    /**
     * UserDTOEditor example to provide conversions from form select fields to
     * DTO.
     */
    @Autowired
    private UserDTOEditor userDTOEditor;

    /**
     * UserGroupDTOEditor example to provide conversions from form select fields
     * to DTO.
     */
    @Autowired
    private UserGroupDTOEditor userGroupDTOEditor;

    /**
     * RoomDTOEditor example to provide conversions from form select fields to
     * DTO.
     */
    @Autowired
    private RoomDTOEditor roomDTOEditor;

    /**
     * MeetingDTOEditor example to provide conversions from form select fields
     * to DTO.
     */
    @Autowired
    private MeetingDTOEditor meetingDTOEditor;

    /**
     * MeetingHistoryService example to provide opportunity to download
     * MeetingHistory.xml.
     */
    @Autowired
    private MeetingHistoryService meetingHistoryService;

    /**
     * Initialize binder for meeting model.
     *
     * @param binder
     *            a WebDataBinder example to initialize.
     */
    @InitBinder(MeetingControllerConst.MEETING_MODEL_ATTR)
    protected void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(MeetingDTO.class, meetingDTOEditor);
        binder.registerCustomEditor(SubjectDTO.class, subjectDTOEditor);
        binder.registerCustomEditor(LocalDate.class, dateEditor);
        binder.registerCustomEditor(LocalTime.class, timeEditor);
        binder.registerCustomEditor(UserDTO.class, userDTOEditor);
        binder.registerCustomEditor(RoomDTO.class, roomDTOEditor);
        binder.registerCustomEditor(UserGroupDTO.class, userGroupDTOEditor);
    }

    /**
     * Initialize binder for filter model.
     *
     * @param binder
     *            a WebDataBinder example to initialize.
     */
    @InitBinder(MeetingControllerConst.FILTER_MODEL_ATTR)
    protected void initBinderFilter(final WebDataBinder binder) {
        binder.registerCustomEditor(UserGroupDTO.class, userGroupDTOEditor);
        binder.registerCustomEditor(LocalDate.class, dateEditor);
        binder.registerCustomEditor(LocalTime.class, timeEditor);
    }

    /**
     * Provides meetings filter.
     *
     * @return new MeetingFilter object.
     */
    @ModelAttribute(MeetingControllerConst.FILTER_MODEL_ATTR)
    public MeetingFilter getFilter() {
        return new MeetingFilter();
    }

    /**
     * Provides meetings paginator.
     *
     * @return new MeetingPaginator object.
     */
    @ModelAttribute(MeetingControllerConst.MEETING_PAGINATOR_MODEL_ATTR)
    public Paginator getPaginator() {
        return new Paginator();
    }

    /**
     * Shows start page for Meetings.
     * 
     * @param model
     * @return
     */
    @RequestMapping
    public String showMeetingPage(final Model model,
            @ModelAttribute(MeetingControllerConst.FILTER_MODEL_ATTR) final MeetingFilter meetingFilter,
            @ModelAttribute(MeetingControllerConst.MEETING_PAGINATOR_MODEL_ATTR) final Paginator paginator) {
        model.addAttribute(MeetingControllerConst.MEETINGS_MODEL_ATTR, meetingService
                .getMeetingPageWithFilter(meetingFilter, paginator));
        model.addAttribute(MeetingControllerConst.USERS_MODEL_ATTR,
                userService.getAllManagers(userService.getAllActiveUsers()));
        model.addAttribute(MeetingControllerConst.SUBJECTS_MODEL_ATTR, subjectService.getAll());
        model.addAttribute(MeetingControllerConst.ROOMS_MODEL_ATTR, roomService.getAll());
        model.addAttribute(MeetingControllerConst.USERGROUPS_MODEL_ATTR, userGroupService.getAll());
        model.addAttribute(MeetingControllerConst.FILTER_MODEL_ATTR, meetingFilter);
        model.addAttribute(MeetingControllerConst.MEETING_PAGINATOR_MODEL_ATTR, paginator);
        model.addAttribute(MeetingControllerConst.MEETINGSTATUSES_MODEL_ATTR, MeetingStatus.values());
        return MeetingControllerConst.MEETING_LIST_URL;
    }

    /**
     * Creates new meetingDTO.
     * 
     * @param model
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MODERATOR')")
    @RequestMapping(value = MeetingControllerConst.MEETING_CREATE_MAPPING, method = RequestMethod.GET)
    public String createForm(final Model model) {
        model.addAttribute(MeetingControllerConst.MEETING_MODEL_ATTR, new MeetingDTO());
        model.addAttribute(MeetingControllerConst.SUBJECTS_MODEL_ATTR, subjectService.getAll());
        model.addAttribute(MeetingControllerConst.OWNERS_MODEL_ATTR,
                userService.getAllManagers(userService.getAllActiveUsers()));
        model.addAttribute(MeetingControllerConst.ROOMS_MODEL_ATTR, roomService.getAll());
        model.addAttribute(MeetingControllerConst.GROUPS_MODEL_ATTR, userGroupService.getAll());
        return MeetingControllerConst.MEETING_CREATE_URL;
    }

    /**
     * Creates new meetingDTO.
     * 
     * @param meetingDTO
     * @return
     */
    @RequestMapping(value = MeetingControllerConst.MEETING_CREATE_MAPPING, method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute(MeetingControllerConst.MEETING_MODEL_ATTR) final MeetingDTO meetingDTO,
            final BindingResult br, final Model model) {
        if (br.hasErrors()) {
            model.addAttribute(MeetingControllerConst.SUBJECTS_MODEL_ATTR, subjectService.getAll());
            model.addAttribute(MeetingControllerConst.OWNERS_MODEL_ATTR, userService
                    .getAllManagers(userService.getAllActiveUsers()));
            model.addAttribute(MeetingControllerConst.ROOMS_MODEL_ATTR, roomService.getAll());
            model.addAttribute(MeetingControllerConst.GROUPS_MODEL_ATTR, userGroupService.getAll());
            return MeetingControllerConst.MEETING_CREATE_URL;
        }
        meetingService.create(meetingDTO);
        return MeetingControllerConst.MEETING_REDIRECT_URL;
    }

    /**
     * Deletes meeting by given id.
     * 
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MODERATOR')")
    @RequestMapping(value = MeetingControllerConst.MEETING_DELETE_MAPPING, method = RequestMethod.GET)
    public String delete(@PathVariable final Long id) {
        meetingService.deleteById(id);
        return MeetingControllerConst.MEETING_REDIRECT_URL;
    }

    /**
     * Edits meeting by given id.
     * 
     * @param id
     * @param model
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MODERATOR')")
    @RequestMapping(value = MeetingControllerConst.MEETING_EDIT_MAPPING, method = RequestMethod.GET)
    public String editForm(@PathVariable(MeetingControllerConst.ID_URL) final Long id,
            final Model model) {
        model.addAttribute(MeetingControllerConst.MEETING_MODEL_ATTR, meetingService.getById(id));
        model.addAttribute(MeetingControllerConst.SUBJECTS_MODEL_ATTR, subjectService.getAll());
        model.addAttribute(MeetingControllerConst.OWNERS_MODEL_ATTR,
                userService.getAllManagers(userService.getAllActiveUsers()));
        model.addAttribute(MeetingControllerConst.ROOMS_MODEL_ATTR, roomService.getAll());
        model.addAttribute(MeetingControllerConst.GROUPS_MODEL_ATTR, userGroupService.getAll());
        model.addAttribute(MeetingControllerConst.MEETINGSTATUSES_MODEL_ATTR, MeetingStatus.values());
        return MeetingControllerConst.MEETING_EDIT_URL;
    }

    /**
     * Deletes meeting by given id.
     * 
     * @param meetingDTO
     * @return
     */
    @RequestMapping(value = MeetingControllerConst.MEETING_EDIT_MAPPING, method = RequestMethod.POST)
    public String edit(
            @ModelAttribute(MeetingControllerConst.MEETING_MODEL_ATTR) @Valid final MeetingDTO meetingDTO,
            final BindingResult br, final Model model) {
        if (br.hasErrors()) {
            model.addAttribute(MeetingControllerConst.SUBJECTS_MODEL_ATTR, subjectService.getAll());
            model.addAttribute(MeetingControllerConst.OWNERS_MODEL_ATTR, userService
                    .getAllManagers(userService.getAllActiveUsers()));
            model.addAttribute(MeetingControllerConst.ROOMS_MODEL_ATTR, roomService.getAll());
            model.addAttribute(MeetingControllerConst.GROUPS_MODEL_ATTR, userGroupService.getAll());
            model.addAttribute(MeetingControllerConst.MEETINGSTATUSES_MODEL_ATTR,
                    MeetingStatus.values());
            return MeetingControllerConst.MEETING_EDIT_URL;
        }
        meetingService.update(meetingDTO);
        return MeetingControllerConst.MEETING_REDIRECT_URL;
    }

    /**
     * Shows selected meeting details.
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = MeetingControllerConst.MEETINGID_URL, method = RequestMethod.GET)
    public String showMeeting(@PathVariable(MeetingControllerConst.ID_URL) final Long id,
            final Model model) {
        model.addAttribute(MeetingControllerConst.MEETING_MODEL_ATTR, meetingService.getById(id));
        model.addAttribute(MeetingControllerConst.ROOMS_MODEL_ATTR, roomService.getAll());
        return MeetingControllerConst.MEETING_SHOWMEETING_MAPPING;
    }

    /**
     * Handle request to download an Excel document
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MODERATOR')")
    @RequestMapping(value = MeetingControllerConst.DOWNLOAD_MAPPING, method = RequestMethod.GET)
    public ModelAndView downloadExcel(final ModelAndView model) {
        model.setView(
                new ExcelViewMeetingHistory(meetingHistoryService.getAll()));
        return model;
    }
}
