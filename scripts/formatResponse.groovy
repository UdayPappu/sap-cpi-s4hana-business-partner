import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def Message processData(Message message) {
    def data = new JsonSlurper().parseText(message.getBody(String))
    def partners = data.d.results.collect {
        [id: it.BusinessPartner, name: it.BusinessPartnerFullName, category: it.BusinessPartnerCategory]
    }
    message.setBody(JsonOutput.prettyPrint(JsonOutput.toJson([count: partners.size(), partners: partners])))
    message.setHeader("Content-Type", "application/json")
    return message
}