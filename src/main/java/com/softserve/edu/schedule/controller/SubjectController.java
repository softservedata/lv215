package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.editor.UserForSubjectDTOEditor;

@Controller
public class SubjectController implements ControllerConst.SubjectControllerConst {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private UserService userService;

	@ModelAttribute(SEARCH_MODEL_ATTR)
	public SubjectDTO getSubjectDTO() {
		return new SubjectDTO();
	}

	@ModelAttribute(SEARCH_BY_TUTOR_MODEL_ATTR)
	public UserForSubjectDTO getUserForSubjectDTO() {
		return new UserForSubjectDTO();
	}

	@InitBinder(SUBJECT_FORM_MODEL_ATTR)
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(UserForSubjectDTO.class, new UserForSubjectDTOEditor(userService));
	}

	@RequestMapping(SUBJECTS_MAPPING)
	public String showSubjectPage(Model model) {
		model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.getAll());
		return SUBJECTS_LIST_URL;
	}

	@RequestMapping(SUBJECT_CREATE_MAPPING)
	public String createForm(Model model) {
		model.addAttribute(SUBJECT_FORM_MODEL_ATTR, new SubjectDTO());
		model.addAttribute(USERS_MODEL_ATTR, userService.getAllForSubject());
		return SUBJECT_CREATE_URL;
	}

	@RequestMapping(value = SUBJECT_CREATE_MAPPING, method = RequestMethod.POST)
	public String create(@ModelAttribute(SUBJECT_FORM_MODEL_ATTR) SubjectDTO subject) {
		subjectService.create(subject);
		return SUBJECTS_REDIRECT_URL;
	}

	@RequestMapping(SUBJECT_EDIT_MAPPING + "{id}")
	public String editForm(@PathVariable Long id, Model model) {
		model.addAttribute(SUBJECT_FORM_MODEL_ATTR, subjectService.getById(id));
		model.addAttribute(USERS_MODEL_ATTR, userService.getAllForSubject());
		return SUBJECTS_EDIT_URL;
	}

	@RequestMapping(value = SUBJECT_EDIT_MAPPING + "{id}", method = RequestMethod.POST)
	public String edit(@ModelAttribute(SUBJECT_FORM_MODEL_ATTR) SubjectDTO subject) {
		subjectService.update(subject);
		return SUBJECTS_REDIRECT_URL;
	}

	@RequestMapping(SUBJECT_DELETE_MAPPING + "{id}")
	public String delete(@PathVariable Long id) {
		subjectService.deleteById(id);
		return SUBJECTS_REDIRECT_URL;
	}

	@RequestMapping(SUBJECTS_SORT_BY_NAME_ASC_MAPPING)
	public String sortByNameAsc(Model model) {
		model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.sortByName(Order.ASC));
		return SUBJECTS_LIST_URL;
	}

	@RequestMapping(SUBJECTS_SORT_BY_NAME_DESC_MAPPING)
	public String sortByNameDesc(Model model) {
		model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.sortByName(Order.DESC));
		return SUBJECTS_LIST_URL;
	}

	@RequestMapping(SUBJECTS_SORT_BY_DESCRIPTION_ASC_MAPPING)
	public String sortByDescrAsc(Model model) {
		model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.sortByDescription(Order.ASC));
		return SUBJECTS_LIST_URL;
	}

	@RequestMapping(SUBJECTS_SORT_BY_DESCRIPTION_DESC_MAPPING)
	public String sortByDescrDesc(Model model) {
		model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.sortByDescription(Order.DESC));
		return SUBJECTS_LIST_URL;
	}

	@RequestMapping(value = SUBJECTS_SEARCH_BY_NAME_MAPPING, method = RequestMethod.POST)
	public String searchByName(@ModelAttribute(SEARCH_MODEL_ATTR) SubjectDTO subject, Model model) {
		model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.searchByName(subject.getName()));
		return SUBJECTS_LIST_URL;
	}

	@RequestMapping(value = SUBJECTS_SEARCH_BY_DESCRIPTION_MAPPING, method = RequestMethod.POST)
	public String searchByDescription(@ModelAttribute(SEARCH_MODEL_ATTR) SubjectDTO subject, Model model) {
		model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.searchByDescription(subject.getDescription()));
		return SUBJECTS_LIST_URL;
	}

	@RequestMapping(value = SUBJECTS_SEARCH_BY_TUTOR_MAPPING, method = RequestMethod.POST)
	public String searchByTutor(@ModelAttribute(SEARCH_BY_TUTOR_MODEL_ATTR) UserForSubjectDTO user, Model model) {
		model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.searchByTutors(user.getLastName()));
		return SUBJECTS_LIST_URL;
	}
}
