package bitapp

import grails.transaction.Transactional
import org.springframework.dao.DataIntegrityViolationException

class QuestionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def questionService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [questionInstanceList: Question.list(params), questionInstanceTotal: Question.count()]
    }

    def create() {
        def questionInstance = questionService.createQuestion(params)
        [questionInstance: questionInstance]
    }

    @Transactional
    def save() {
        if(params?.noOptions?.toString()?.equals("YES")){
            params?.options = ""
        }
        def questionInstance = questionService.saveQuestion(params)
        if (!questionInstance) {
            render(view: "create", model: [questionInstance: questionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), questionInstance.id])
        redirect(action: "show", id: questionInstance.id)
    }

    def show(Long id) {
        def questionInstance = questionService.findQuestion(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "index")
            return
        }

        [questionInstance: questionInstance]
    }

    def edit(Long id) {
        def questionInstance = questionService.findQuestion(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "index")
            return
        }

        [questionInstance: questionInstance]
    }

    @Transactional
    def update(Long id, Long version) {
        def questionInstance = questionService.findQuestion(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "index")
            return
        }

        if (version != null) {
            if (questionInstance.version > version) {
                questionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'question.label', default: 'Question')] as Object[],
                          "Another user has updated this Question while you were editing")
                render(view: "edit", model: [questionInstance: questionInstance])
                return
            }
        }

        questionInstance.properties = params
        if(params?.noOptions?.toString()?.equals("YES")){
            questionInstance?.options = ""
        }

        questionInstance = questionService.updateQuestion(questionInstance)
        if (!questionInstance) {
            render(view: "edit", model: [questionInstance: questionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'question.label', default: 'Question'), questionInstance.id])
        redirect(action: "show", id: questionInstance.id)
    }

    @Transactional
    def delete(Long id) {
        def questionInstance = questionService.findQuestion(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "index")
            return
        }

        try {
            questionService.deleteQuestion(questionInstance)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "index")
        } catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "show", id: id)
        }
    }
}
