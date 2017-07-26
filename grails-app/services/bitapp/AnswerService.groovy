package bitapp

import grails.transaction.Transactional
import org.grails.datastore.mapping.validation.ValidationException
import org.springframework.dao.DataIntegrityViolationException

@Transactional
class AnswerService {

    def createAnswer(def params) {
        return new Answer(params)
    }

    def findAllQuestionsWithoutAnswer() {
        def list = []

        list = Question.findAll().findAll{it?.answer == null}

        return list
    }

    def findAllQuestion(def question) {
        def list = []

        list = this.findAllQuestionsWithoutAnswer()

        if(question?.id != null){
            list = list+[question]
        }

        return list
    }

    def findAnswer(Long id) {
        return Answer.get(id)
    }

    def saveAnswer(def params) {
        def answerInstance = new Answer(params)
        //answerInstance.questionType = answerInstance.question.type

        return answerInstance.save(flush: true)
    }

    def updateAnswer(Answer answerInstance) {
        return answerInstance.merge(flush: true)
    }

    def deleteAnswer(Answer answerInstance) {
        try {
            answerInstance.delete(flush: true)
        } catch (DataIntegrityViolationException  ex) {
            log.error("DataIntegrityViolationException => "+ex.getMessage())
        } catch (org.springframework.dao.OptimisticLockingFailureException olfEx) {
            log.error("OptimisticLockingFailureException => "+olfEx.getMessage())
        } catch (org.hibernate.StaleStateException ssEx) {
            log.error("StaleStateException => "+ssEx.getMessage())
        }
    }

    def serviceMethod4() {

    }

    def serviceMethod3() {

    }

    def serviceMethod2() {

    }
    def serviceMethod1() {

    }
}
