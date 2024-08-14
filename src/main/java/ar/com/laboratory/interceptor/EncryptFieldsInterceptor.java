package ar.com.laboratory.interceptor;

import ar.com.laboratory.util.AES256EncrypterUtil;
import ar.com.laboratory.util.annotations.EncryptFields;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Field;
import java.util.logging.Logger;

@Interceptor
@EncryptFields
@Priority(3)
public class EncryptFieldsInterceptor {
    private static final Logger LOGGER = Logger.getLogger(EncryptFieldsInterceptor.class.getName());

    @Inject
    AES256EncrypterUtil aes256EncrypterUtil;

    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        Object[] parameters = context.getParameters();

        for (Object parameter : parameters) {
            if (parameter != null) {
                encryptFields(parameter);
            }
        }

        return context.proceed();
    }

    public void encryptFields(Object object) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            // Verifica si el campo es de tipo String
            if (field.getType().equals(String.class)) {
                field.setAccessible(true);
                String originalValue = (String) field.get(object);

                // Encripta solo si el valor no es nulo ni vacío
                if (originalValue != null && !originalValue.isEmpty()) {
                    String encryptedValue = aes256EncrypterUtil.encrypt(originalValue);
                    field.set(object, encryptedValue);
                }
            }
        }
    }
}
