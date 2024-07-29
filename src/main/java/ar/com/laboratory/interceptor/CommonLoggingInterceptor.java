package ar.com.laboratory.interceptor;

import ar.com.laboratory.config.SensitiveDataConfig;
import ar.com.laboratory.util.annotations.CommonLogging;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.logging.Logger;

@Interceptor
@CommonLogging
@Priority(2)
public class CommonLoggingInterceptor {
    private static final Logger LOGGER = Logger.getLogger(CommonLoggingInterceptor.class.getName());

    @ConfigProperty(name = "common.logging.active", defaultValue = "true")
    String active;

    @ConfigProperty(name = "common.logging.before.active", defaultValue = "true")
    String beforeLoggingActive;

    @ConfigProperty(name = "common.logging.after.active", defaultValue = "true")
    String afterLoggingActive;

    @ConfigProperty(name = "common.logging.exception.active", defaultValue = "true")
    String exceptionLoggingActive;

    @Inject
    SensitiveDataConfig sensitiveDataConfig;

    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        var sensitiveData = sensitiveDataConfig.getSensitiveDataList();
        long startTime = System.currentTimeMillis();
        if (Boolean.parseBoolean(active)) {
            String className = context.getMethod().getDeclaringClass().getName();
            Object[] params = context.getParameters();
            Parameter[] parameters = context.getMethod().getParameters();
            beforeLogging(context, className, params, parameters,sensitiveData);
            try {
                Object result = context.proceed();
                long endTime = System.currentTimeMillis();
                afterLogging(context, className, result, endTime - startTime, sensitiveData);
                return result;
            } catch (Exception e) {
                long endTime = System.currentTimeMillis();
                exceptionLogging(context, e, className, endTime - startTime);
            }
        }
        return context.proceed();
    }

    private void exceptionLogging(InvocationContext context, Exception e, String className, long duration) throws Exception {
        if (exceptionLoggingActive.equals("true")) {
            LOGGER.severe("------------- EXCEPTION ----------------");
            LOGGER.severe("Class: " + className);
            LOGGER.severe("Method: " + context.getMethod().getName());
            LOGGER.severe("Excepcion: " + e.getMessage());
            LOGGER.severe("Execution time is: " + duration + " ms");
            LOGGER.severe("------------- EXCEPTION-END ----------------");
        }
        throw e;
    }

    private void afterLogging(InvocationContext context, String className, Object result, long duration, List<String> sensitiveData ) {
        if (afterLoggingActive.equals("true")) {
            LOGGER.info("------------- RESPONSE ----------------");
            LOGGER.info("Class: " + className);
            LOGGER.info("Method: " + context.getMethod().getName());
            LOGGER.info("Execution time is: " + duration + " ms");
            if (result != null) {
                LOGGER.info("Response: " + result.toString());
            } else {
                LOGGER.info("El metodo no retorno ningun valor");
            }
            LOGGER.info("------------- RESPONSE-END ----------------");
        }
    }

    private void beforeLogging(InvocationContext context, String className, Object[] params, Parameter[] parameters, List<String> sensitiveData) {
        if (beforeLoggingActive.equals("true")) {
            LOGGER.info("------------- REQUEST ----------------");
            LOGGER.info("Class: " + className);
            LOGGER.info("Method: " + context.getMethod().getName());
            LOGGER.info("Arguments: ");
            logArguments(params, parameters, sensitiveData);
            LOGGER.info("------------- REQUEST-END ----------------");
        }
    }

    private static void logArguments(Object[] params, Parameter[] parameters, List<String> sensitiveData) {
        if (parameters.length == 0) {
            LOGGER.info("No parameters");
        } else {
            for (int i = 0; i < params.length; i++) {
                String paramName = parameters[i].getName();
                Object paramValue = params[i];

                if (paramValue != null && isCompositeObject(paramValue)) {
                    LOGGER.info(paramValue.getClass().getSimpleName());
                    logCompositeObjectFields(paramValue, sensitiveData);
                } else {
                    if (sensitiveData.contains(paramName)) {
                        LOGGER.info("- " + paramName + ": ***");
                    } else {
                        LOGGER.info("- " + paramName + ": " + paramValue);
                    }
                }
            }
        }
    }

    private static boolean isCompositeObject(Object obj) {
        return obj != null && !(obj instanceof String || obj instanceof Number || obj instanceof Boolean);
    }

    private static void logCompositeObjectFields(Object obj, List<String> sensitiveData) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (sensitiveData.contains(field.getName())) {
                    LOGGER.info("  - " + field.getName() + ": ***");
                } else {
                    LOGGER.info("  - " + field.getName() + ": " + value);
                }
            } catch (IllegalAccessException e) {
                LOGGER.warning("  - Error accessing field " + field.getName() + ": " + e.getMessage());
            }
        }
    }
}