package bitapp

import grails.transaction.Transactional
import org.springframework.dao.DataIntegrityViolationException

class QuestionnaireController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def questionnaireService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [questionnaireInstanceList: Questionnaire.list(params), questionnaireInstanceTotal: Questionnaire.count()]

    }

    def create() {
        def questions = []
        def questionnaireInstance = questionnaireService.createQuestionnaire(params)
        questions = questionnaireService.findAllQuestionsForView(questionnaireInstance)
        [questionnaireInstance: questionnaireInstance, questions: questions]
    }

    @Transactional
    def save() {
        def questionnaireInstance = questionnaireService.saveQuestionnaire(params)
        if (!questionnaireInstance) {
            render(view: "create", model: [questionnaireInstance: questionnaireInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'questionnaire.label', default: 'Questionnaire'), questionnaireInstance.id])
        redirect(action: "show", id: questionnaireInstance.id)
    }

    def show(Long id) {
        def questionnaireInstance = questionnaireService.findQuestionnaire(id)
        if (!questionnaireInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionnaire.label', default: 'Questionnaire'), id])
            redirect(action: "index")
            return
        }

        [questionnaireInstance: questionnaireInstance]
    }

    def edit(Long id) {
        def questions = []
        def questionnaireInstance = questionnaireService.findQuestionnaire(id)

        if (!questionnaireInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionnaire.label', default: 'Questionnaire'), id])
            redirect(action: "index")
            return
        }

        questions = questionnaireService.findAllQuestionsForView(questionnaireInstance)
        [questionnaireInstance: questionnaireInstance, questions: questions]

    }

    @Transactional
    def update(Long id, Long version) {
        def questionnaireInstance = questionnaireService.findQuestionnaire(id)
        if (!questionnaireInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionnaire.label', default: 'Questionnaire'), id])
            redirect(action: "index")
            return
        }

        if (version != null) {
            if (questionnaireInstance.version > version) {
                questionnaireInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'questionnaire.label', default: 'Questionnaire')] as Object[],
                          "Another user has updated this Questionnaire while you were editing")
                render(view: "edit", model: [questionnaireInstance: questionnaireInstance])
                return
            }
        }

        questionnaireInstance = questionnaireService.updateQuestionnaireProcess(questionnaireInstance,params)
        if (!questionnaireInstance) {
            render(view: "edit", model: [questionnaireInstance: questionnaireInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'questionnaire.label', default: 'Questionnaire'), questionnaireInstance.id])
        redirect(action: "show", id: questionnaireInstance.id)
    }

    @Transactional
    def delete(Long id) {
        def questionnaireInstance = questionnaireService.findQuestionnaire(id)
        if (!questionnaireInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionnaire.label', default: 'Questionnaire'), id])
            redirect(action: "index")
            return
        }

        try {
            questionnaireService.deleteQuestionnaire(questionnaireInstance)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'questionnaire.label', default: 'Questionnaire'), id])
            redirect(action: "index")
        } catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'questionnaire.label', default: 'Questionnaire'), id])
            redirect(action: "show", id: id)
        }
    }
}
