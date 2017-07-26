package bitapp

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AppController {
    //static allowedMethods = [index: "GET", create: "GET", edit: "GET", save: "POST", update: "POST", update: "PUT", delete: "POST", delete: "DELETE"]
    static allowedMethods = [dashboard: "GET"]

    def springSecurityService
    def securityService

    @Secured('ROLE_USER')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def list = [26, 69, 85, 96, 58, 59];
        def count = 20;
        render(view: "index", model: [list: list, count: count])
    }


    def dashboard() {
        // principal.authorities*.authority
        // springSecurityService.getPrincipal().getAuthorities()*.authority
        // session.getAttribute('SPRING_SECURITY_CONTEXT').authentication.principal.authorities.authority
        // session?.SPRING_SECURITY_CONTEXT?.authentication?.principal?.authorities?.authority

        def authorities = securityService.findAllRolesByUser(springSecurityService?.currentUser)
        def userType = springSecurityService?.currentUser?.type

        render(view: "dashboard", model: [userType: userType, authorities: authorities])
    }
}
