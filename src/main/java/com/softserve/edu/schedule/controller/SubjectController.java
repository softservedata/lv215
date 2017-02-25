/*
 * SubjectController.java
 * 1.0
 * 24 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
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

import com.softserve.edu.schedule.dto.FileForSubjectDTO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.SubjectFilter;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.implementation.editor.UserForSubjectDTOEditor;

/**
 * A controller class of subjects pages.
 *
 * @version 1.0 15 January 2017
 *
 * @author Ped'ko Volodymyr
 *
 * @since 1.8
 */
@Controller
@SessionAttributes({ ControllerConst.SubjectControllerConst.FILTER_MODEL_ATTR,
        ControllerConst.SubjectControllerConst.SUBJECT_PAGINATOR_MODEL_ATTR })
public class SubjectController
        implements ControllerConst.SubjectControllerConst {

	/**
	 * SubjectService example to provide subjects list to the model.
	 */
	@Autowired
	private SubjectService subjectService;

	/**
	 * UserForSubjectDTOEditor example to provide conversions from form select
	 * fields to DTO.
	 */
	@Autowired
	private UserForSubjectDTOEditor userForSubjectDTOEditor;

	/**
	 * Method provides model attribute for filter.
	 * 
	 * @return new SubjectFilter object
	 */
	@ModelAttribute(FILTER_MODEL_ATTR)
	public SubjectFilter getFilter() {
		return new SubjectFilter();
	}

	/**
	 * Provides pagination object for rooms list page.
	 * 
	 * @return new Paginator object.
	 */
	@ModelAttribute(SUBJECT_PAGINATOR_MODEL_ATTR)
	public Paginator getPaginator() {
		return new Paginator();
	}

	/**
	 * Initialize binder for filter model.
	 *
	 * @param binder
	 *            a WebDataBinder example to initialize.
	 */
	@InitBinder(FILTER_MODEL_ATTR)
	protected void initBinderFilter(final WebDataBinder binder) {
		binder.registerCustomEditor(UserForSubjectDTO.class,
		        userForSubjectDTOEditor);
	}

	/**
	 * Initialize binder for subject model.
	 *
	 * @param binder
	 *            a WebDataBinder example to initialize.
	 */
	@InitBinder(SUBJECT_FORM_MODEL_ATTR)
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(UserForSubjectDTO.class,
		        userForSubjectDTOEditor);
	}

	/**
	 * Method controls view of subjects list page.
	 * 
	 * @param model
	 *            subjects list page model
	 * @param filter
	 *            the subject filtter to set
	 * @param paginator
	 *            the paginator to set
	 * @return subjects list page URL
	 */
	@RequestMapping(SUBJECTS_MAPPING)
	public String showSubjectPage(final Model model,
	        @ModelAttribute(FILTER_MODEL_ATTR) final SubjectFilter filter,
	        @ModelAttribute(SUBJECT_PAGINATOR_MODEL_ATTR) final Paginator paginator) {
		model.addAttribute(SUBJECTS_MODEL_ATTR,
		        subjectService.getSubjectsPageWithFilter(filter, paginator));
		model.addAttribute(USERS_MODEL_ATTR,
		        subjectService.getAllUserForSubjectDTO());
		return SUBJECTS_LIST_URL;
	}

	/**
	 * Method prepares form for creating subject
	 * 
	 * @param model
	 *            subjects create page model
	 * @return subjects create page URL
	 */
	@PreAuthorize(HAS_ANY_ROLE_EXEPT_USER)
	@RequestMapping(SUBJECT_CREATE_MAPPING)
	public String createForm(final Model model) {
		model.addAttribute(SUBJECT_FORM_MODEL_ATTR, new SubjectDTO());
		model.addAttribute(USERS_MODEL_ATTR,
		        subjectService.getAllUserForSubjectDTO());
		return SUBJECT_CREATE_URL;
	}

	/**
	 * Method creates new subject
	 * 
	 * @param subject
	 *            subjects create page model
	 * @return subjects list page URL (redirect)
	 */
	@RequestMapping(value = SUBJECT_CREATE_MAPPING, method = RequestMethod.POST)
	public String create(
	        @ModelAttribute(SUBJECT_FORM_MODEL_ATTR) @Valid final SubjectDTO subject,
	        final BindingResult result, final Model model) {
		if (result.hasErrors()) {
			model.addAttribute(USERS_MODEL_ATTR,
			        subjectService.getAllUserForSubjectDTO());
			return SUBJECT_CREATE_URL;
		}
		subjectService.create(subject);
		return SUBJECTS_REDIRECT_URL;
	}

	/**
	 * Method prepares form for editing subject
	 * 
	 * @param model
	 *            subjects edit page model
	 * @return subjects edit page URL
	 */
	@PreAuthorize(HAS_ANY_ROLE_EXEPT_USER)
	@RequestMapping(SUBJECT_EDIT_MAPPING + "{id}")
	public String editForm(@PathVariable final Long id, final Model model) {
		model.addAttribute(SUBJECT_FORM_MODEL_ATTR, subjectService.getById(id));
		model.addAttribute(USERS_MODEL_ATTR,
		        subjectService.getAllUserForSubjectDTO());
		return SUBJECTS_EDIT_URL;
	}

	/**
	 * Method edits current subject
	 * 
	 * @param subject
	 *            subjects edit page model
	 * @return subjects list page URL (redirect)
	 */
	@RequestMapping(value = SUBJECT_EDIT_MAPPING
	        + "{id}", method = RequestMethod.POST)
	public String edit(
	        @ModelAttribute(SUBJECT_FORM_MODEL_ATTR) @Valid final SubjectDTO subject,
	        final BindingResult result, final Model model) {
		if (result.hasErrors()) {
			model.addAttribute(USERS_MODEL_ATTR,
			        subjectService.getAllUserForSubjectDTO());
			return SUBJECTS_EDIT_URL;
		}
		subjectService.update(subject);
		return SUBJECTS_REDIRECT_URL;
	}

	/**
	 * Method provides deleting subject by id.
	 * 
	 * @param id
	 *            id subject to delete
	 * @return subjects list page URL (redirect)
	 */
	@PreAuthorize(HAS_ANY_ROLE_EXEPT_USER)
	@RequestMapping(SUBJECT_DELETE_MAPPING + "{id}")
	public String delete(@PathVariable final Long id) {
		subjectService.deleteById(id);
		return SUBJECTS_REDIRECT_URL;
	}

	/**
	 * Controls view of room details show page.
	 *
	 * @param id
	 *            subject id to show details.
	 *
	 * @param model
	 *            subject details show page view model.
	 *
	 * @return subject show detail page URL
	 */
	@RequestMapping(SUBJECTS_MAPPING_SHOW + "{id}")
	public String showSubjectDetails(@PathVariable final Long id,
	        final Model model) {
		model.addAttribute(SUBJECT_FILE_FORM, new FileForSubjectDTO());
		model.addAttribute(SUBJECT_MODEL_ATTR, subjectService.getById(id));
		model.addAttribute(SUBJECT_FILES, subjectService.showSubjectFiles(id));
		return SUBJECTS_SHOW_URL;
	}

	/**
	 * Method for upload file for Subject.
	 *
	 * @param id
	 *            subject id to show details.
	 *
	 * @param subjectFileDTO
	 *            subjectFileDTO details for storing file.
	 *
	 * @exception IOException
	 * 
	 * @return subject show detail page URL
	 */
	@PreAuthorize(HAS_ANY_ROLE_EXEPT_USER)
	@RequestMapping(value = SUBJECTS_MAPPING_SHOW
	        + "{id}", method = RequestMethod.POST)
	public String uploadFile(@PathVariable final Long id,
	        @ModelAttribute(SUBJECT_FILE_FORM) @Valid final FileForSubjectDTO subjectFileDTO,
	        final BindingResult result) throws IOException {
		if (result.hasErrors()) {
			return SUBJECT_DELETE_FILE_URL;
		}
		System.out.println(subjectFileDTO.getId());
		subjectService.uploadFile(subjectFileDTO, id);
		return SUBJECT_DELETE_FILE_URL;
	}

	/**
	 * Method for delete file from Subject.
	 *
	 * @param id
	 *            subject id to show details.
	 *
	 * @param fileName
	 *            subject file name.
	 *
	 * @return subject show detail page URL
	 */
	@PreAuthorize(HAS_ANY_ROLE_EXEPT_USER)
	@RequestMapping(SUBJECT_DELETE_FILE_MAPPING + "{fileName}/{id}")
	public String deleteFile(@PathVariable final Long id,
	        @PathVariable String fileName) {
		subjectService.deleteSubjectFileById(id, fileName);
		return SUBJECT_DELETE_FILE_URL;
	}

	/**
	 * Method for delete file from Subject.
	 *
	 * @param id
	 *            subject id to show details.
	 *
	 * @param fileName
	 *            subject file name.
	 * 
	 * @exception IOException
	 * 
	 * @param response
	 *            HttpServletResponse response for file download.
	 *
	 * @return subject show detail page URL
	 */
	@PreAuthorize(HAS_ANY_ROLE)
	@RequestMapping(SUBJECT_DOWNLOAD_FILE_MAPPING + "{fileName}/{id}")
	public void downloadFile(@PathVariable final Long id,
	        @PathVariable final String fileName, HttpServletResponse response)
	        throws IOException {
		subjectService.retriveSubjectFileById(id, fileName, response);
	}

}