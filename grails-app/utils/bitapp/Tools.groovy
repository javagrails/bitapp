package bitapp

/**
 *
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName bitapp.Tools.java.jsp.groovy.php | bitapp | bitapp.Tools
 * @since Jul 24, 2017 22:34:34 AM PM
 *
 */

class Tools {
    public static final String USER_TYPE_ADMIN    = "ADMIN"
    public static final String USER_TYPE_BOSS     = "BOSS"
    public static final String USER_TYPE_NORMAL   = "NORMAL"
    public static final List<String> USER_TYPES   = ["ADMIN", "BOSS", "NORMAL"].sort()

    public static final String ROLE_ADMIN    = "ROLE_ADMIN"
    public static final String ROLE_BOSS     = "ROLE_BOSS"
    public static final String ROLE_NORMAL   = "ROLE_NORMAL"
    public static final List<String> ROLE_TYPES    = ["ROLE_ADMIN", "ROLE_BOSS", "ROLE_NORMAL"].sort()

    public static final String NARRATIVE        = "NARRATIVE"
    public static final String SELECTIVE        = "SELECTIVE"
    public static final List<String> QUESTION_TYPES   = ["NARRATIVE", "SELECTIVE"].sort()


    public static final String RM_TYPE_MENU         = "MENU"
    public static final String RM_TYPE_LINK         = "LINK"
    public static final String RM_TYPE_BUTTON       = "BUTTON"
    public static final String RM_TYPE_FORM_ACTION  = "FORM_ACTION"
    public static final List<String> RM__TYPES      = ["MENU", "LINK","BUTTON", "FORM_ACTION"].sort()


}
