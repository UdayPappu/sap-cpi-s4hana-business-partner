

import com.sap.gateway.ip.core.customdev.util.Message
import com.sap.it.api.ITApiFactory
import com.sap.it.api.securestore.SecureStoreService

def Message processData(Message message) {
    def store = ITApiFactory.getService(SecureStoreService.class, null)
    def cred = store.getUserCredential("S4_SANDBOX_APIKEY")
    message.setHeader("APIKey", new String(cred.getPassword()))
    message.setHeader("Accept", "application/json")
    return message
}