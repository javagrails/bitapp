package bitapp

class BootStrap {

    def init = { servletContext ->
        int count = Requestmap.count()
        println("Requestmap.count() = " + count)
        if (count <= 0) initData();


        int size = Questionnaire.count()
        println("Questionnaire.count() = " + size)
        if (size <= 0) initBasicData();


    }
    def destroy = {
    }

    def initBasicData() {

        Answer answer1 = new Answer(answer: "Dahaka", questionType: "SELECTIVE")
        Answer answer2 = new Answer(answer: "Jack Fruit", questionType: "SELECTIVE")
        Answer answer3 = new Answer(answer: "Dahaka", questionType: "SELECTIVE")
        Answer answer4 = new Answer(answer: "Doel", questionType: "SELECTIVE")
        Question question1 = new Question(answer: answer1, type: "SELECTIVE", title: "What is the capital city of Bangladesh?", options: "Tangail,Dhaka,Sylhet,Rangpur").save()
        Question question2 = new Question(answer: answer2, type: "SELECTIVE", title: "What is the national fruit of Bangladesh?", options: "Banana,Jack Fruit,Apple,Mango").save()
        Question question3 = new Question(answer: answer3, type: "SELECTIVE", title: "What is the national animal of Bangladesh?", options: "Fox,Cat,Tiger,Elephant").save()
        Question question4 = new Question(answer: answer4, type: "SELECTIVE", title: "What is the national bird of Bangladesh?", options: "Babui,Doel,Crow,Parrot").save()


        Answer answer5 = new Answer(answer: "5", questionType: "SELECTIVE")
        Answer answer6 = new Answer(answer: "15", questionType: "SELECTIVE")
        Answer answer7 = new Answer(answer: "4", questionType: "SELECTIVE")
        Answer answer8 = new Answer(answer: "28", questionType: "SELECTIVE")
        Question question5 = new Question(answer: answer5, type: "SELECTIVE", title: "2+3=?", options: "6,3,4,5").save()
        Question question6 = new Question(answer: answer6, type: "SELECTIVE", title: "5x3=?", options: "24,15,21,11").save()
        Question question7 = new Question(answer: answer7, type: "SELECTIVE", title: "20/5=?", options: "1,3,4,5").save()
        Question question8 = new Question(answer: answer8, type: "SELECTIVE", title: "37-9=?", options: "27,28,26,29").save()

        Questionnaire generalKnowledge = new Questionnaire(title: "General Knowledge", questions: [question1, question2, question3]).save()
        Questionnaire mathematics = new Questionnaire(title: "Mathematics", questions: [question5, question6, question7]).save()

    }

    def initData() {
        User U1 = new User(username: "admin", password: "admin", enabled: true, type: "ADMIN").save()
        User U2 = new User(username: "boss", password: "boss", enabled: true, type: "BOSS").save()
        User U3 = new User(username: "normal", password: "normal", enabled: true, type: "NORMAL").save()

        Role R1 = new Role(authority: "ROLE_ADMIN").save()
        Role R2 = new Role(authority: "ROLE_BOSS").save()
        Role R3 = new Role(authority: "ROLE_NORMAL").save()

        UserRole.create(U1, R1)
        UserRole.create(U1, R2)
        UserRole.create(U1, R3)

        UserRole.create(U2, R2)
        UserRole.create(U2, R3)

        UserRole.create(U3, R3)

        new Requestmap(url: "/login/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY", featureName: "Release /login/**").save()
        new Requestmap(url: "/logout/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY", featureName: "Release /logout/**").save()
        new Requestmap(url: "/assets/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY", featureName: "Release /assets/**").save()
        new Requestmap(url: "/", configAttribute: "ROLE_ADMIN", featureName: "Release /").save()

        new Requestmap(url: "/user/show", configAttribute: "ROLE_ADMIN", featureName: "Show user").save()
        new Requestmap(url: "/user/create", configAttribute: "ROLE_ADMIN", featureName: "Create user").save()
        new Requestmap(url: "/user/update", configAttribute: "ROLE_ADMIN", featureName: "Update user").save()
        new Requestmap(url: "/user/delete", configAttribute: "ROLE_ADMIN", featureName: "Delete user").save()
        new Requestmap(url: "/user/list", configAttribute: "ROLE_ADMIN", featureName: "List user").save()
        new Requestmap(url: "/user/resetPassword", configAttribute: "ROLE_ADMIN", featureName: "Reset password").save()
        new Requestmap(url: "/user/reloadDropDown", configAttribute: "ROLE_ADMIN", featureName: "Reload user dropdown").save()

        new Requestmap(url: "/role/show", configAttribute: "ROLE_ADMIN", featureName: "Show role").save()
        new Requestmap(url: "/role/create", configAttribute: "ROLE_ADMIN", featureName: "Create role").save()
        new Requestmap(url: "/role/update", configAttribute: "ROLE_ADMIN", featureName: "Update role").save()
        new Requestmap(url: "/role/delete", configAttribute: "ROLE_ADMIN", featureName: "Delete role").save()
        new Requestmap(url: "/role/list", configAttribute: "ROLE_ADMIN", featureName: "List role").save()

        new Requestmap(url: "/userRole/show", configAttribute: "ROLE_ADMIN", featureName: "Show user role mapping").save()
        new Requestmap(url: "/userRole/create", configAttribute: "ROLE_ADMIN", featureName: "Create user role mapping").save()
        new Requestmap(url: "/userRole/update", configAttribute: "ROLE_ADMIN", featureName: "Update user role mapping").save()
        new Requestmap(url: "/userRole/delete", configAttribute: "ROLE_ADMIN", featureName: "Delete user role mapping").save()
        new Requestmap(url: "/userRole/list", configAttribute: "ROLE_ADMIN", featureName: "List user role mapping").save()
        new Requestmap(url: "/userRole/report", configAttribute: "ROLE_ADMIN", featureName: "Download user role mapping report").save()

        new Requestmap(url: "/app/dashboard", configAttribute: "ROLE_ADMIN", type: "LINK", featureName: "Dashboard").save()
        new Requestmap(url: "/app/dashboard", configAttribute: "ROLE_BOSS", type: "LINK", featureName: "Dashboard").save()
        new Requestmap(url: "/app/dashboard", configAttribute: "ROLE_NORMAL", type: "LINK", featureName: "Dashboard").save()

        println("=====================Start=================['LINK', 'MENU', 'BUTTON', 'FORM_ACTION']===========")

        ["questionnaire", "question", "answer"].each { ctrlId ->
            def label = ctrlId?.capitalize()
            ["ROLE_ADMIN", "ROLE_BOSS", "ROLE_NORMAL"].each { roleItem ->

                println("Combination ( ctrlId, roleItem ) = ( " + ctrlId + ", " + roleItem + " )")
                if (roleItem?.equals("ROLE_ADMIN")) {
                    println("BLOCK 01 :: ctrlId [ " + ctrlId + " ] |roleItem [ " + roleItem + " ]")
                    new Requestmap(url: "/${ctrlId}/index", configAttribute: "${roleItem}", type: "MENU", featureName: "${label} list").save()
                    new Requestmap(url: "/${ctrlId}/show/**", configAttribute: "${roleItem}", type: "BUTTON", featureName: "${label} show").save()
                    new Requestmap(url: "/${ctrlId}/create", configAttribute: "${roleItem}", type: "LINK", featureName: "${label} create").save()
                    new Requestmap(url: "/${ctrlId}/save", configAttribute: "${roleItem}", type: "FORM_ACTION", featureName: "${label} save").save()
                    new Requestmap(url: "/${ctrlId}/edit/**", configAttribute: "${roleItem}", type: "BUTTON", featureName: "${label} edit").save()
                    new Requestmap(url: "/${ctrlId}/update/**", configAttribute: "${roleItem}", type: "FORM_ACTION", featureName: "${label} update").save()
                    new Requestmap(url: "/${ctrlId}/delete/**", configAttribute: "${roleItem}", type: "BUTTON", featureName: "${label} delete").save()
                }

                if (roleItem?.equals("ROLE_BOSS")) {
                    println("BLOCK 02 :: ctrlId [ " + ctrlId + " ] |roleItem [ " + roleItem + " ]")
                    new Requestmap(url: "/${ctrlId}/index", configAttribute: "${roleItem}", type: "MENU", featureName: "${label} list").save()
                    new Requestmap(url: "/${ctrlId}/show/**", configAttribute: "${roleItem}", type: "BUTTON", featureName: "${label} show").save()
                    new Requestmap(url: "/${ctrlId}/create", configAttribute: "${roleItem}", type: "LINK", featureName: "${label} create").save()
                    new Requestmap(url: "/${ctrlId}/save", configAttribute: "${roleItem}", type: "FORM_ACTION", featureName: "${label} save").save()
                    new Requestmap(url: "/${ctrlId}/edit/**", configAttribute: "${roleItem}", type: "BUTTON", featureName: "${label} edit").save()
                    new Requestmap(url: "/${ctrlId}/update/**", configAttribute: "${roleItem}", type: "FORM_ACTION", featureName: "${label} update").save()
                }
                if (roleItem?.equals("ROLE_NORMAL")) {
                    println("BLOCK 03 :: ctrlId [ " + ctrlId + " ] |roleItem [ " + roleItem + " ]")
                    new Requestmap(url: "/${ctrlId}/index", configAttribute: "${roleItem}", type: "MENU", featureName: "${label} list").save()
                    new Requestmap(url: "/${ctrlId}/show/**", configAttribute: "${roleItem}", type: "BUTTON", featureName: "${label} show").save()
                }

            }

        }
        println("=====================End============================")
        println("****************************************************")
        /*new Requestmap(url: "/requestmap/show", configAttribute: "ROLE_ADMIN", featureName: "Show feature management").save()
        new Requestmap(url: "/requestmap/update", configAttribute: "ROLE_ADMIN", featureName: "Update feature management").save()
        new Requestmap(url: "/requestmap/listAvailableRole", configAttribute: "ROLE_ADMIN", featureName: "Available features").save()
        new Requestmap(url: "/requestmap/listAssignedRole", configAttribute: "ROLE_ADMIN", featureName: "Assigned features").save()*/
    }
}
