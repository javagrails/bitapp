package bitapp

class AccessTagLib {
    static namespace = "access"

    def print = { attrs, body ->
        String data = buildIndexLink("answer","index")
        println('data => '+data)
        out << data.toString()
    }

    def permissions = { attrs, body ->
        String allData = ""
        List<String> urls = attrs.remove('urls').split(',')


        Boolean hasAccess = Boolean.FALSE
        for (int i = 0; i < urls.size(); i++) {
            hasAccess = sec.access(url: urls.get(i).trim()) { Boolean.TRUE }
            //println(i+' => '+urls.get(i).trim()+' => '+hasAccess)
            if (hasAccess) {
                def splitInfo = urls.get(i).trim().split('/')
                def domain = splitInfo[1]
                def action = splitInfo[2]

                allData += buildLinks(domain, action)
            }
        }
        println('allData => '+allData)
        out << allData?.toString()
    }

    protected String buildLinks(String domain, String action){
        String result = ""
        switch (action) {
            case "index":
                result = buildIndexLink(domain, action)
                break
            case "create":
                result = buildCreateLink(domain, action)
                break
            case "save":
                result = buildSaveLink(domain, action)
                break
            case "show":
                result = buildShowLink(domain, action)
                break
            case "edit":
                result = buildEditLink(domain, action)
                break
            case "update":
                result = buildUpdateLink(domain, action)
                break
            case "delete":
                result = buildDeleteLink(domain, action)
                break
            default:
                result = ""
                break
        }
        return result
    }

    String buildIndexLink(String domain, String action) {
        String label = g.message(code: 'default.list.label', args: [domain?.capitalize()])
        String link = g.link(class: "list", label=label, action: action)
        //return """<li><g:link class="list" action="index">${label}</g:link></li>"""
        return link
    }

    String buildCreateLink(String domain, String action) {
        return "<li><g:link class=\"create\" action=\"create\"><g:message code=\"default.new.label\" args=\"[entityName]\" /></g:link></li>"
    }

    String buildSaveLink(String domain, String action) {
        return "<g:submitButton name=\"create\" class=\"save\" value=\"${message(code: 'default.button.create.label', default: 'Create')}\" />"
    }

    String buildShowLink(String domain, String action) {
        return ""
    }

    String buildEditLink(String domain, String action) {
        def idVal = "\${"+domain+"Instance?.id"+"}"
        return "<g:link class=\"edit\" action=\"edit\" id=\"${idVal}\"><g:message code=\"default.button.edit.label\" default=\"Edit\" /></g:link>"
    }

    String buildUpdateLink(String domain, String action) {
        return "<g:actionSubmit class=\"save\" action=\"update\" value=\"${message(code: 'default.button.update.label', default: 'Update')}\" />"
    }

    String buildDeleteLink(String domain, String action) {
        return "<g:actionSubmit class=\"delete\" action=\"delete\" value=\"${message(code: 'default.button.delete.label', default: 'Delete')}\" formnovalidate=\"\" onclick=\"return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');\" />"
    }


}

/*

<access:print  />
<access:permissions urls="/answer/index,/answer/edit,/answer/delete" />


*/