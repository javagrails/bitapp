package bitapp

import grails.transaction.Transactional
import org.springframework.dao.DataIntegrityViolationException

class AnswerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def answerService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [answerInstanceList: Answer.list(params), answerInstanceTotal: Answer.count()]
    }

    def create() {
        def answerInstance = answerService.createAnswer(params)
        def freeQuestions = answerService.findAllQuestion(answerInstance?.question)
        [answerInstance: answerInstance, freeQuestions: freeQuestions]
    }

    @Transactional
    def save() {

        def answerInstance = answerService.saveAnswer(params)
        if (!answerInstance) {
            render(view: "create", model: [answerInstance: answerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'answer.label', default: 'Answer'), answerInstance.id])
        redirect(action: "show", id: answerInstance.id)
    }

    def show(Long id) {
        def answerInstance = answerService.findAnswer(id)
        if (!answerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "index")
            return
        }

        [answerInstance: answerInstance]
    }


    def edit(Long id) {
        def answerInstance = answerService.findAnswer(id)

        if (!answerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "index")
            return
        }

        def freeQuestions = answerService.findAllQuestion(answerInstance?.question)
        [answerInstance: answerInstance, freeQuestions: freeQuestions]
    }

    @Transactional
    def update(Long id, Long version) {
        def answerInstance = answerService.findAnswer(id)
        if (!answerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "index")
            return
        }

        if (version != null) {
            if (answerInstance.version > version) {
                answerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'answer.label', default: 'Answer')] as Object[],
                          "Another user has updated this Answer while you were editing")
                render(view: "edit", model: [answerInstance: answerInstance])
                return
            }
        }

        answerInstance.properties = params
        answerInstance = answerService.updateAnswer(answerInstance)
        if (!answerInstance) {
            render(view: "edit", model: [answerInstance: answerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'answer.label', default: 'Answer'), answerInstance.id])
        redirect(action: "show", id: answerInstance.id)
    }

    @Transactional
    def delete(Long id) {
        def answerInstance = answerService.findAnswer(id)
        if (!answerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "index")
            return
        }

        try {
            answerService.deleteAnswer(answerInstance)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "index")
        } catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "show", id: id)
        }
    }
}
