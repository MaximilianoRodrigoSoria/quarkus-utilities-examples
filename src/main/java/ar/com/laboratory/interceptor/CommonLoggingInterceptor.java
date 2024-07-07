package ar.com.laboratory.interceptor;





import ar.com.laboratory.util.annotations.CommonLogging;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Parameter;
import java.util.logging.Logger;

@Interceptor
@CommonLogging
public class CommonLoggingInterceptor {

    private static final Logger LOGGER = Logger.getLogger(CommonLoggingInterceptor.class.getName());

    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        String className = context.getMethod().getDeclaringClass().getName();
        LOGGER.info("\n");
        LOGGER.info("------------- REQUEST ----------------");
        LOGGER.severe("Class: " + className);
        LOGGER.info("Method: " + context.getMethod().getName());
        LOGGER.info("Arguments: ");
        Object[] params = context.getParameters();
        Parameter[] parameters = context.getMethod().getParameters();
        for (int i = 0; i < params.length; i++) {
            String paramName = parameters[i].getName();
            LOGGER.info(paramName + " : " + params[i]);
        }
        LOGGER.info("------------- REQUEST-END ----------------");
        LOGGER.info("\n");
        try {
            // Ejecutamos el metodo
            Object result = context.proceed();
            LOGGER.info("\n");
            LOGGER.info("------------- RESPONSE ----------------");
            LOGGER.info("Class: " + className);
            LOGGER.info("Method: " + context.getMethod().getName());
            if (result != null) {
                LOGGER.info("Response: " + result.toString());
            } else {
                LOGGER.info("El metodo no retorno ningun valor");
            }
            LOGGER.info("------------- RESPONSE-END ----------------");
            LOGGER.info("\n");
            return result;
        } catch (Exception e) {
            LOGGER.severe("\n");
            LOGGER.severe("------------- EXCEPTION ----------------");
            LOGGER.severe("Class: " + className);
            LOGGER.severe("Method: " + context.getMethod().getName() );
            LOGGER.severe( "Excepcion: " + e.getMessage());
            LOGGER.severe("------------- EXCEPTION-END ----------------");
            LOGGER.severe("\n");
            throw e;
        }
    }
}
