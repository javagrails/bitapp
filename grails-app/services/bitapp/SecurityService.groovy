package bitapp

import grails.transaction.Transactional

@Transactional
class SecurityService {

    def serviceMethod() {

    }

    def findAllRolesByUser(def user) {
        def userRole = []
        def roles = []
        def authorities = []

        userRole = UserRole.findAllByUser(user)
        roles = userRole.collect {it?.role}
        authorities = roles?.collect {it?.authority}

        return authorities;
    }


    def buildMenu(Long id) {
        def list = []

        String userType = User.findAllById(id)?.type[0]
        if(userType != null){
            list = Requestmap.findAllByConfigAttributeAndType("ROLE_"+userType, Tools.RM_TYPE_MENU)
        }

        return list;
    }
}
