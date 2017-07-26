package bitapp

import grails.transaction.Transactional
import org.springframework.dao.DataIntegrityViolationException

@Transactional
class QuestionService {

    def createQuestion(def params) {
        return new Question(params)
    }

    def findQuestion(Long id) {
        return Question.get(id)
    }

    def saveQuestion(def params) {
        def questionInstance = new Question(params)

        return questionInstance.save(flush: true)
    }

    def updateQuestion(Question questionInstance) {
        return questionInstance.merge(flush: true)
    }

    def deleteQuestion(Question questionInstance) {
        try {
            questionInstance.delete(flush: true)
        } catch (DataIntegrityViolationException  ex) {
            log.error("DataIntegrityViolationException => "+ex.getMessage())
        } catch (org.springframework.dao.OptimisticLockingFailureException olfEx) {
            log.error("OptimisticLockingFailureException => "+olfEx.getMessage())
        } catch (org.hibernate.StaleStateException ssEx) {
            log.error("StaleStateException => "+ssEx.getMessage())
        }
    }
    
    
    def serviceMethod() {

    }
}
