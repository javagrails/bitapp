package bitapp

import grails.transaction.Transactional
import org.springframework.dao.DataIntegrityViolationException

@Transactional
class QuestionnaireService {

    def serviceMethod() {

    }

    def createQuestionnaire(def params) {
        return new Questionnaire(params)
    }

    def findQuestionnaire(Long id) {

        return Questionnaire.get(id)
    }

    def buildQuestionsForView(def questions, boolean isSelected) {
        def list = []

        questions?.each { question ->

            def viewQuestion = new Expando()
            viewQuestion.id = question?.id
            viewQuestion.title = question?.title
            viewQuestion.type = question?.type
            viewQuestion.isSelected = isSelected

            list << viewQuestion
        }

        return list

    }
    def findAllQuestionsByQuestionnaire(Questionnaire questionnaire) {
        def list = []

        if(questionnaire?.questions != null){
            list = questionnaire?.questions
        }

        list = this.buildQuestionsForView(list,true)
        return list
    }

    def findAllFreeQuestions() {
        def list = []

        list = Question.findAllByQuestionnaireIsNull()

        if(list == null){
            list = []
        }

        list = this.buildQuestionsForView(list,false)
        return list
    }

    def findAllQuestionsForView(Questionnaire questionnaire) {
        def list = []

        if(questionnaire?.questions == null){
            list = this.findAllFreeQuestions()
        } else {
            list = this.findAllQuestionsByQuestionnaire(questionnaire) + this.findAllFreeQuestions()
        }

        return list
    }

    def saveQuestionnaire(def params) {
        def questionsIds = params?.list("questionList")?.collect {Long.parseLong(it)}
        def questions = Question.findAllByIdInList(questionsIds)

        def questionnaireInstance = new Questionnaire(params)
        questionnaireInstance.save(flush: true)

        if(questionnaireInstance?.id != null && questions?.size()>0){
            attachQuestionsWithQuestionnaire(questions, questionnaireInstance)
        }

        this.updateQuestionnaire(questionnaireInstance)
        return questionnaireInstance
    }

    private List<Question> attachQuestionsWithQuestionnaire(List<Question> questions, questionnaireInstance) {
        if(questions == null || questions?.size() < 1 ){
            return
        }
        questions?.each { question ->
            questionnaireInstance.addToQuestions(question)
            questionnaireInstance.merge()
        }
    }

    def updateQuestionnaireProcess(Questionnaire questionnaireInstance, def params) {

        def questionsIds = params?.list("questionList")?.collect {Long.parseLong(it)}
        def questions = Question.findAllByIdInList(questionsIds)

        removeQuestionFromQuestionnaire(questionnaireInstance)
        attachQuestionsWithQuestionnaire(questions, questionnaireInstance)
        this.updateQuestionnaire(questionnaireInstance)

        return questionnaireInstance

    }

    private Set<Question> removeQuestionFromQuestionnaire(Questionnaire questionnaireInstance) {
        def questions = []

        if(questionnaireInstance?.questions == null || questionnaireInstance?.questions?.size() < 1){
            return
        }

        questions = Question.findAllByIdInList(questionnaireInstance?.questions?.collect {it?.id})
        questions?.each { question ->
            questionnaireInstance.removeFromQuestions(question)
            questionnaireInstance.merge()
        }
    }

    def updateQuestionnaire(Questionnaire questionnaireInstance) {
        return questionnaireInstance.merge(flush: true)
    }

    def deleteQuestionnaire(Questionnaire questionnaireInstance) {
        try {
            questionnaireInstance.delete(flush: true)
        } catch (DataIntegrityViolationException  ex) {
            log.error("DataIntegrityViolationException => "+ex.getMessage())
        } catch (org.springframework.dao.OptimisticLockingFailureException olfEx) {
            log.error("OptimisticLockingFailureException => "+olfEx.getMessage())
        } catch (org.hibernate.StaleStateException ssEx) {
            log.error("StaleStateException => "+ssEx.getMessage())
        }
    }
}
